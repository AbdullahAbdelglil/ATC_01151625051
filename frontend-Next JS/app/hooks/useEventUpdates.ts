// src/hooks/useEventUpdates.ts
import { useEffect, useState } from 'react';

export function useEventUpdates() {
  const [dialogOpen, setDialogOpen] = useState(false);
  const [dialogMessage, setDialogMessage] = useState('');
  const [isRefreshing, setIsRefreshing] = useState(false);

  useEffect(() => {
    const eventSource = new EventSource('http://localhost:8080/api/v1/admin/events-updates');

    eventSource.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data);
        if (["eventCreated", "eventUpdated", "eventDeleted"].includes(data.type)) {
          setDialogMessage(data.message + ', Would you like to refresh Events List?');
          setDialogOpen(true);
        }
      } catch (err) {
        console.error('Invalid SSE data', err);
      }
    };

    eventSource.onerror = (err) => {
      console.error("❌ SSE error:", err);
    };

    return () => eventSource.close();
  }, []);

  return {
    dialogOpen,
    setDialogOpen,
    dialogMessage,
    isRefreshing,
    setIsRefreshing,
  };
}
