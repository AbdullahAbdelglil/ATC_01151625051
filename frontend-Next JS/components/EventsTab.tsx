'use client';

import { useEffect, useRef, useCallback } from 'react';
import { EventsProvider, useEventsContext } from '@/context/eventsContext';
import EventCard from './EventCard';
import CategoryFilter from './CategoryFilter';
import { Loader2, RefreshCw, AlertCircle } from 'lucide-react';
import { useRouter } from 'next/navigation';
import { useEventUpdates } from '@/app/hooks/useEventUpdates';
import { Button } from './ui/button';
import { AlertDialog, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from './ui/alert-dialog';

export default function EventsTab() {
  const {
    dialogOpen,
    setDialogOpen,
    dialogMessage,
    isRefreshing,
    setIsRefreshing,
  } = useEventUpdates();

  const {
    events,
    categories,
    selectedCategoryId,
    setSelectedCategoryId,
    loadMore,
    loading,
    hasNewEvents,
    showNewEvents
  } = useEventsContext();

  const router = useRouter();
  const observerRef = useRef<HTMLDivElement | null>(null);

  const loadEvents = useCallback(() => {
    if (loading || !events.length) return;
    loadMore();
  }, [events, loading, loadMore]);

  useEffect(() => {
    if (!observerRef.current) return;
    const observer = new IntersectionObserver(
      (entries) => {
        if (entries[0].isIntersecting) {
          loadEvents();
        }
      },
      { threshold: 1 }
    );
    observer.observe(observerRef.current);
    return () => {
      if (observerRef.current) observer.unobserve(observerRef.current);
    };
  }, [loadEvents]);

  const handleConfirmRefresh = async () => {
    try {
      setIsRefreshing(true);
      await showNewEvents();
      setTimeout(() => window.location.reload(), 200);
    } finally {
      setIsRefreshing(false);
      setDialogOpen(false);
    }
  };

  return (
    <EventsProvider>
      <div className="space-y-10 max-w-screen-2xl mx-auto px-4 sm:px-8 md:px-12 lg:px-20 mb-10">
        <CategoryFilter
          categories={categories}
          selectedCategoryId={selectedCategoryId}
          onSelectCategory={setSelectedCategoryId}
        />

        {hasNewEvents && (
          <div className="flex justify-center mb-6">
            <button
              onClick={showNewEvents}
              className="flex items-center gap-2 px-5 py-3 rounded-xl bg-blue-600 text-white font-semibold shadow-md hover:bg-blue-700 transition-all animate-bounce"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                className="h-5 w-5 animate-pulse"
                viewBox="0 0 20 20"
                fill="currentColor"
              >
                <path d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-11V5a1 1 0 10-2 0v2a1 1 0 001 1h2a1 1 0 100-2h-1z" />
              </svg>
              Click To Refresh
            </button>
          </div>
        )}

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
          {events.map((event) => (
            <EventCard key={event.id} event={event} />
          ))}
        </div>

        {loading && (
          <div className="flex justify-center items-center py-4">
            <Loader2 className="w-8 h-8 text-blue-400 animate-spin" />
          </div>
        )}

        {hasNewEvents && (
          <div className="text-center">
            <button onClick={showNewEvents} className="text-blue-500 mt-4">
              New Events (Click to see)
            </button>
          </div>
        )}

        <div ref={observerRef} />

        {/* Alert Dialog for SSE Updates */}
        <AlertDialog open={dialogOpen} onOpenChange={setDialogOpen}>
          <AlertDialogContent
            className="bg-gradient-to-br from-[#0f172a] to-[#1e293b] 
               border border-[#334155] text-white 
               shadow-2xl shadow-blue-800/30 
               rounded-2xl backdrop-blur-md 
               transition-all duration-300 ease-in-out animate-fade-in"
          >
            <AlertDialogHeader>
              <div className="flex items-center gap-2 mb-1 group">
                <AlertCircle className="w-6 h-6 text-cyan-400 transition-all duration-300 ease-in-out group-hover:rotate-12 group-hover:scale-110" />
                <AlertDialogTitle className="text-xl font-semibold tracking-wide text-cyan-400 drop-shadow-md">
                  Update Detected
                </AlertDialogTitle>
              </div>
              <AlertDialogDescription className="text-sm text-gray-300 leading-relaxed mt-2 transition-opacity duration-300 ease-in-out">
                {dialogMessage}
              </AlertDialogDescription>
            </AlertDialogHeader>

            <AlertDialogFooter className="mt-6 flex justify-end gap-3">
              <AlertDialogCancel
                className="bg-gradient-to-r from-gray-700 via-gray-600 to-gray-700 
                   text-white border border-gray-500 
                   px-4 py-2 rounded-lg 
                   transition-all duration-300 ease-in-out 
                   hover:-translate-y-[2px] hover:brightness-110 hover:shadow-lg"
              >
                Cancel
              </AlertDialogCancel>

              <Button
                onClick={handleConfirmRefresh}
                disabled={isRefreshing}
                className="bg-gradient-to-r from-blue-600 to-blue-800 
                   text-white px-5 py-2 
                   rounded-lg font-semibold 
                   shadow-md 
                   transition-all duration-300 ease-in-out 
                   hover:shadow-xl hover:scale-[1.03] hover:brightness-110 
                   disabled:opacity-70 disabled:cursor-not-allowed 
                   flex items-center gap-2"
              >
                {isRefreshing ? (
                  <>
                    <Loader2 className="w-5 h-5 animate-spin" />
                    Refreshing...
                  </>
                ) : (
                  <>
                    <RefreshCw className="w-5 h-5 transition-transform duration-300 group-hover:rotate-180" />
                    Refresh
                  </>
                )}
              </Button>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>

      </div>
    </EventsProvider>
  );
}
