# 🎟️ Fullstack Event Booking System

A comprehensive full-stack event booking system that allows users to browse and book events, with an admin panel to manage events.

---

## 📌 Table of Contents

- [Overview](#overview)
- [Features](#features)
  - [User Features](#user-features)
  - [Admin Features](#admin-features)
- [Advanced Features](#advanced-features)
  - [Frontend](#frontend)
  - [Backend](#backend)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Project Structure](#project-structure)
- [Fun Fact](#fun-fact)
---

<a id="overview"></a>
## 🧾 Overview

This project is a full-stack event booking system to facilitate event browsing, booking, and management. It consists of:

- **Frontend**: Built with Next.js and TypeScript, providing a responsive and dynamic user interface.
- **Backend**: Developed using Spring Boot (Java), offering robust RESTful APIs and secure data management.

---

<a id="features"></a>
## ✨ Features

<a id="user-features"></a>
### 👤 User Features

- User registration and login.
- Browse available events with detailed information.
- Book events easily.
- Manage bookings: view and cancel.

<a id="admin-features"></a>
### 🛠️ Admin Features

- Create, update, and delete events.
- Monitor and manage bookings and users.
- Access to a comprehensive admin dashboard.

---

<a id="advanced-features"></a>
## 🚀 Advanced Features

<a id="frontend"></a>
### 🖥️ Frontend

- Dynamic routing with Next.js.
- State management using Context API or Redux.
- Responsive design for all devices.
- Integration with backend APIs.
- **Real-time updates using Server-Sent Events (SSE) to notify users immediately when changes occur (e.g., admin actions).**

<a id="backend"></a>
### ⚙️ Backend

- RESTful API endpoints.
- Security with Spring Security.
- Database integration with JPA/Hibernate.
- Remote Database Deployment on Railway
- Session and authentication management.  
- Role-based access control (RBAC) to differentiate permissions between users and admins.  
- Authentication and authorization implemented using JSON Web Tokens (JWT).  
- **Server-Sent Events (SSE) implementation to push real-time notifications to frontend clients, enabling synchronization between admin actions and user interfaces. For example, when an admin performs an update, connected users receive a dialog prompt notifying them of the update and suggesting to refresh the page.**

---

<a id="tech-stack"></a>
## 🛠️ Tech Stack

- Frontend: Next.js, TypeScript
- Backend: Spring Boot, Java
- DataBase: MySQL (hosted remotely on Railway)  
- Authentication & Authorization: Spring Security
- API communication: RESTful APIs

---

<a id="getting-started"></a>
## 🧪 Getting Started

<a id="backend-setup"></a>
### Backend Setup

1. Navigate to the backend directory: `cd FullStackEventBookingSystem`
2. Open CMD
3. Build the project using this command: `mvnw clean install`
4. Run the backend server using this command: `mvnw spring-boot:run`
5. Backend will run at `http://localhost:8080`

<a id="frontend-setup"></a>
### Frontend Setup

1. Navigate to the frontend directory: `cd frontend-Next JS`
2. Open CMD
3. Install dependencies using this command: `npm install`
4. Run the frontend development server using this command: `npm run dev`
5. Frontend will run at `http://localhost:3000`

---

<a id="project-structure"></a>
## 📂 Project Structure

ATC_01151625051/

├── FullStackEventBookingSystem/    # Backend (Spring Boot)

├── frontend-Next JS/               # Frontend (Next.js)

└── README.md                       # Project documentation

---

## 📞 Contact Me

If you have any questions, suggestions, or want to collaborate, feel free to reach out:

- **Name:** Abdullah Abdelglil  
- **Email:** abdullah.abduljalil.zaky@gmail.com
- **Phone:** 01151625051
- **GitHub:** You are in 😄  
- **LinkedIn:** [linkedin.com/in/abdullahabdelglil](https://www.linkedin.com/in/abdullah-abdelgalil-aa583a285)

---

<a id="fun-fact"></a>
## 😄 Fun Fact

All of the frontend was built with **vibe coding** — powered by strong coffee, good music, and a sprinkle of magic ✨☕🎶.
