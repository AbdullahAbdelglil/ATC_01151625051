'use client';

import { useCallback, useEffect, useRef, useState } from 'react';
import { useRouter } from 'next/navigation';
import toast from 'react-hot-toast';
import { getAdminDashboard, deleteEvent } from '@/services/adminApi';
import { AdminViewEventDTO } from '@/app/types/AdminEvent';
import { Button } from '@/components/ui/button';
import {
    AlertDialog,
    AlertDialogCancel,
    AlertDialogContent,
    AlertDialogDescription,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogTitle,
    AlertDialogTrigger,
} from '@/components/ui/alert-dialog';
import { Loader2, Eye, Pencil, Trash } from 'lucide-react';
import { AnimatePresence, motion } from 'framer-motion';

export default function AdminDashboard() {
    const [events, setEvents] = useState<AdminViewEventDTO[]>([]);
    const [loading, setLoading] = useState(false);
    const [initialLoading, setInitialLoading] = useState(true);
    const [viewNavigatingId, setViewNavigatingId] = useState<number | null>(null);
    const [editNavigatingId, setEditNavigatingId] = useState<number | null>(null);
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);
    const [deletingId, setDeletingId] = useState<number | null>(null);
    const [confirmDeleteId, setConfirmDeleteId] = useState<number | null>(null);
    const eventIdsRef = useRef<Set<number>>(new Set());
    const observerRef = useRef<HTMLDivElement | null>(null);
    const router = useRouter();
    const [creating, setCreating] = useState(false);

    // Fetch Events with caching logic
    const fetchEvents = useCallback(
        async (pageToFetch: number) => {
            if (loading || !hasMore) return;
            setLoading(true);

            try {
                const data = await getAdminDashboard(pageToFetch, 10);

                // Filter new events not in cache
                const newEvents = data.filter((event: { id: number }) => !eventIdsRef.current.has(event.id));

                if (newEvents.length > 0) {
                    setEvents((prev) => [...prev, ...newEvents]);
                    newEvents.forEach((event: { id: number }) => eventIdsRef.current.add(event.id));
                }

                setHasMore(data.length === 10);
                if (data.length === 10) setPage(pageToFetch);
            } catch (err) {
                console.error('Failed to fetch admin events', err);
                toast.error('Failed to load events');
            } finally {
                setLoading(false);
                setInitialLoading(false);
            }
        },
        [hasMore, loading]
    );

    useEffect(() => {
        fetchEvents(0);
    }, []);

    useEffect(() => {
        if (!hasMore || loading) return;

        const observer = new IntersectionObserver(
            (entries) => {
                if (entries[0].isIntersecting) {
                    fetchEvents(page + 1);
                }
            },
            { threshold: 1 }
        );

        const ref = observerRef.current;
        if (ref) observer.observe(ref);

        return () => {
            if (ref) observer.unobserve(ref);
        };
    }, [hasMore, loading, page, fetchEvents]);

    const handleViewNavigate = (path: string, eventId: number) => {
        setViewNavigatingId(eventId);
        router.push(path);
    };

    const handleEditNavigate = (path: string, eventId: number) => {
        setEditNavigatingId(eventId);
        router.push(path);
    };

    const handleDelete = async (id: number) => {
        setDeletingId(id);
        try {
            await deleteEvent(id.toString());
            toast.success('Event deleted successfully');
            setEvents((prev) => prev.filter((e) => e.id !== id));
            eventIdsRef.current.delete(id);
        } catch (err) {
            toast.error('Failed to delete event');
        } finally {
            setDeletingId(null);
            setConfirmDeleteId(null);
        }
    };

    return (
        <div
            className="min-h-screen w-full px-4 sm:px-8 md:px-12 lg:px-20 pt-10 bg-black text-white"
            style={{
                backgroundImage: 'radial-gradient(circle at center, rgba(30,64,175,0.3) 0%, black 70%)',
                backgroundRepeat: 'no-repeat',
                backgroundSize: 'cover',
            }}
        >
            {initialLoading ? (
                <div className="flex items-center justify-center h-screen">
                    <Loader2 className="w-10 h-10 text-blue-400 animate-spin" />
                </div>
            ) : (
                <>
                    <h1 className="text-4xl font-extrabold mb-8 text-center text-white tracking-wide">Admin Dashboard</h1>

                    <div className="flex justify-end mb-4">
                        <Button
                            onClick={() => {
                                setCreating(true);
                                router.push('/admin/event/create');
                            }}
                            disabled={creating}
                            className="bg-blue-700 hover:bg-blue-800 text-white px-4 py-2 rounded-lg font-medium shadow-md transition-all flex items-center gap-2"
                        >
                            {creating ? <Loader2 className="w-30 h-5 animate-spin" /> : '+ Create New Event'}
                        </Button>
                    </div>

                    <div className="overflow-x-auto rounded-xl border border-[#334155] shadow-xl backdrop-blur-md bg-white/5">
                        <table className="min-w-full text-base text-left text-white">
                            <thead className="bg-[#1E293B] text-[#CBD5E1] uppercase tracking-wide">
                                <tr>
                                    <th className="px-6 py-4">Event</th>
                                    <th className="px-6 py-4">Bookings</th>
                                    <th className="px-6 py-4">Price</th>
                                    <th className="px-6 py-4">Date</th>
                                    <th className="px-6 py-4 text-center">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <AnimatePresence>
                                    {events.map((event) => (
                                        <motion.tr
                                            key={event.id}
                                            layout
                                            initial={{ opacity: 1 }}
                                            exit={{ opacity: 0, x: 100, scale: 0.95 }}
                                            transition={{ duration: 0.4 }}
                                            className="border-t border-[#334155] hover:bg-[#0f172a] transition duration-300"
                                        >
                                            <td className="px-6 py-5 font-semibold text-[#E2E8F0]">{event.name}</td>
                                            <td className="px-6 py-5 text-[#E2E8F0]">{event.numberOfBookings}</td>
                                            <td className="px-6 py-5 text-[#E2E8F0]">{event.price > 0 ? `$${event.price.toFixed(2)}` : 'Free'}</td>
                                            <td className="px-6 py-5 text-[#E2E8F0]">{new Date(event.date).toLocaleDateString()}</td>
                                            <td className="px-10 py-6 flex justify-center gap-6">
                                                {/* View */}
                                                <Button
                                                    onClick={() => handleViewNavigate(`/admin/event/${event.id}`, event.id)}
                                                    size="sm"
                                                    variant="ghost"
                                                    disabled={viewNavigatingId === event.id}
                                                    className="group relative text-blue-400 hover:text-blue-100 hover:bg-blue-800/30 rounded-lg transition-all duration-300 transform hover:scale-105 shadow hover:shadow-blue-600"
                                                >
                                                    {viewNavigatingId === event.id ? (
                                                        <Loader2 className="w-6 h-6 animate-spin" />
                                                    ) : (
                                                        <Eye className="w-6 h-6 group-hover:drop-shadow-[0_0_8px_rgba(96,165,250,0.8)]" />
                                                    )}
                                                    <span className="absolute -top-6 left-1/2 -translate-x-1/2 opacity-0 group-hover:opacity-100 text-xs bg-blue-800/80 px-2 py-0.5 rounded-md transition-opacity duration-300">
                                                        View
                                                    </span>
                                                </Button>

                                                {/* Edit */}
                                                <Button
                                                    onClick={() => handleEditNavigate(`/admin/event/update/${event.id}`, event.id)}
                                                    size="sm"
                                                    variant="ghost"
                                                    disabled={editNavigatingId === event.id}
                                                    className="group relative text-purple-400 hover:text-purple-100 hover:bg-purple-800/30 rounded-lg transition-all duration-300 transform hover:scale-105 shadow hover:shadow-purple-600"
                                                >
                                                    {editNavigatingId === event.id ? (
                                                        <Loader2 className="w-6 h-6 animate-spin" />
                                                    ) : (
                                                        <Pencil className="w-6 h-6 group-hover:drop-shadow-[0_0_8px_rgba(192,132,252,0.9)]" />
                                                    )}
                                                    <span className="absolute -top-6 left-1/2 -translate-x-1/2 opacity-0 group-hover:opacity-100 text-xs bg-purple-900/90 px-2 py-0.5 rounded-md transition-opacity duration-300">
                                                        Edit
                                                    </span>
                                                </Button>

                                                {/* Delete with confirmation */}
                                                <AlertDialog open={confirmDeleteId === event.id} onOpenChange={(open) => setConfirmDeleteId(open ? event.id : null)}>
                                                    <AlertDialogTrigger asChild>
                                                        <div className="relative group">
                                                            <Button
                                                                size="sm"
                                                                className="text-red-400 hover:text-red-100 hover:bg-red-800/30 rounded-lg transition-all duration-300 transform hover:scale-105 shadow hover:shadow-red-600"
                                                                variant="ghost"
                                                            >
                                                                <Trash className="w-6 h-6 group-hover:drop-shadow-[0_0_8px_rgba(248,113,113,0.9)]" />
                                                            </Button>
                                                            <span className="absolute -top-6 left-1/2 -translate-x-1/2 opacity-0 group-hover:opacity-100 text-xs bg-red-900/90 px-2 py-0.5 rounded-md transition-opacity duration-300 pointer-events-none select-none">
                                                                Delete
                                                            </span>
                                                        </div>
                                                    </AlertDialogTrigger>

                                                    <AlertDialogContent className="bg-[#1B263B] border border-[#415A77] text-white">
                                                        <AlertDialogHeader>
                                                            <AlertDialogTitle>Delete Event</AlertDialogTitle>
                                                            <AlertDialogDescription>
                                                                Are you sure you want to delete this event? This action cannot be undone.
                                                            </AlertDialogDescription>
                                                        </AlertDialogHeader>
                                                        <AlertDialogFooter>
                                                            <AlertDialogCancel className="bg-gray-600 text-white hover:bg-gray-700">
                                                                Cancel
                                                            </AlertDialogCancel>
                                                            <Button
                                                                className="bg-red-600 hover:bg-red-700 text-white"
                                                                type="submit"
                                                                variant="destructive"
                                                                onClick={() => handleDelete(event.id)}
                                                                disabled={deletingId === event.id}
                                                            >
                                                                {deletingId === event.id ? (
                                                                    <Loader2 className="w-5 h-5 animate-spin" />
                                                                ) : (
                                                                    'Delete'
                                                                )}
                                                            </Button>
                                                        </AlertDialogFooter>
                                                    </AlertDialogContent>
                                                </AlertDialog>
                                            </td>
                                        </motion.tr>
                                    ))}
                                </AnimatePresence>
                                {!loading && events.length === 0 && (
                                    <tr>
                                        <td colSpan={5} className="text-center p-10 text-[#94a3b8]">
                                            No events found.
                                        </td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </div>

                    {/* Loader or End message */}
                    <div ref={observerRef} className="my-6 flex justify-center">
                        {loading ? (
                            <Loader2 className="w-8 h-8 animate-spin text-blue-500" />
                        ) : !hasMore ? (
                            <p className="text-gray-400">No more events to load.</p>
                        ) : null}
                    </div>
                </>
            )}
        </div>
    );
}
