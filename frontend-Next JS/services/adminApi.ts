import { api } from "./api";

export const getAdminDashboard = async (page: number, size: number) => {
  const res = await api.get('/admin/dashboard', {
    params: { page, size },
  });
  return res.data;
};

export async function getAdminEventDetails(id: string | string[]) {
  const res = await api.get(`/admin/event/${id}`);
  if (!res) throw new Error('Failed to fetch event');
  return res.data;
}

export async function deleteEventById(id: number) {
  const res = await api.delete(`/admin/control-panel/${id}`);
  if (!res) throw new Error('Failed to delete event');
}

export const createEvent = async (formData: FormData) => {
  const res = await api.post('/admin/control-panel', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });

  if (!res) {
    throw new Error('Failed to create event');
  }

  return res.data;
};


export const updateEvent = async (id: string, formData: FormData) => {
  const res = await api.put(`/admin/control-panel/${id}`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });

  if (!res) {
    throw new Error('Failed to update event');
  }

  return res.data;
};

export const deleteEvent = async (id: string | string[]) => {
  const res = await api.delete(`/admin/control-panel/${id}`);
  return res.data;
};