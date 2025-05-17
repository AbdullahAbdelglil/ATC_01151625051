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
- [What You Need Before Starting](#what-you-need-before-starting)
- [Important Notes for Testing](#important-notes-for-testing)
- [Getting Started](#getting-started)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Accounts To Use](#accounts-to-use)
- [Project Structure](#project-structure)
- [Contact Me](#contact-me)
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

- User input validation for both admin and user 
- Dynamic routing with Next.js.
- State management using Context API.
- Caching In Session Storage
- Responsive design for all devices.
- Integration with backend APIs.
- **Real-time updates using Server-Sent Events (SSE) to notify users immediately when changes occur (e.g., admin actions).**

<a id="backend"></a>
### ⚙️ Backend

- RESTful API endpoints.
- User input validation for both admin and user 
- Security with Spring Security.
- Database integration with JPA/Hibernate.
- Remote database hosting and deployment managed via Railway, ensuring reliable and scalable cloud infrastructure. (Test it out: https://railway.com/invite/6huvbWAWktK)
- Session and authentication management.
- Role-based access control (RBAC) to differentiate permissions between users and admins.
- Authentication and authorization implemented using JSON Web Tokens (JWT).
- **Server-Sent Events (SSE) implementation to push real-time notifications to frontend clients, enabling synchronization between admin actions and user interfaces. For example, when an admin performs an update, connected users receive a dialog prompt notifying them of the update and suggesting to refresh the page.**

---

<a id="tech-stack"></a>
## 🛠️ Tech Stack

- Frontend: Next.js, TypeScript
- Backend: Spring Boot, Java
- Database: MySQL (hosted remotely on Railway)
- Authentication & Authorization: Spring Security
- API communication: RESTful APIs

---

<a id="what-you-need-before-starting"></a>
## 📋 What You Need Before Starting

- **Java JDK 17 or higher** installed and configured.
- **Node.js** (v16 or higher) and **npm** installed.
- A **MySQL database** setup and running, or you can use **JetBrains DataGrip** (Datasource URL, useranme, and password located in **application.properties** file in this path: src/mian/resources.
- A modern web browser (Chrome, Firefox, Edge) for testing frontend.
- Basic familiarity with command line/terminal.
- Recommended to use IDEs like IntelliJ IDEA for backend and VSCode for frontend.

---

<a id="important-notes-for-testing"></a>
## ⚠️ Important Notes for Testing

- This application uses JWT tokens stored in **localStorage** for authentication.
- To test both user and admin roles simultaneously, you need to open two separate browsers (or use incognito/private mode) — one for the user and one for the admin. This is because tokens stored in localStorage are scoped per browser and cannot coexist in the same browser session.

---

<a id="getting-started"></a>
## 🧪 Getting Started

<a id="backend-setup"></a>
### Backend Setup

1. Navigate to the backend directory:  
   `cd FullStackEventBookingSystem`
2. Open your terminal or CMD.
3. Build the project:  
   `mvnw clean install` (Windows) or `./mvnw clean install` (macOS/Linux)
4. Run the backend server:  
   `mvnw spring-boot:run` (Windows) or `./mvnw spring-boot:run` (macOS/Linux)
5. Backend will be running at: `http://localhost:8080`

<a id="frontend-setup"></a>
### Frontend Setup

1. Navigate to the frontend directory:  
   `cd frontend-Next JS`
2. Open your terminal or CMD.
3. Install dependencies:  
   `npm install`
4. Run the frontend development server:  
   `npm run dev`
5. Frontend will be running at: `http://localhost:3000`
---

<a id="accounts-to-use"></a>
## 🧪 Fake account to test:

**Admin**: 
- email: `abdullah@gmail.com`
- password: `Abdullah@2002`

**User**
1.
email: `ali@gmail.com`
password: `Ali@1234` 

2.
email: `doe@gmail.com`
password: `Doe@1234`

You can add a new account from the registration page also if u need.

---

<a id="project-structure"></a>
## 📂 Project Structure

ATC_01151625051/

├── FullStackEventBookingSystem/ # Backend (Spring Boot)

├── frontend-Next JS/ # Frontend (Next.js)

└── README.md # Project documentation

---

<a id="contact-me"></a>
## 📞 Contact Me

If you have any questions, suggestions, or want to collaborate, feel free to reach out:

- **Name:** Abdullah Abdelglil  
- **Email:** abdullah.abduljalil.zaky@gmail.com  
- **Phone:** 01151625051  
- **GitHub:** You are in 😄  
- **LinkedIn:** [linkedin.com/in/abdullahabdelglil](https://www.linkedin.com/in/abdullah-abdulgalil-aa583a285?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app)

---

<a id="fun-fact"></a>
## 😄 Fun Fact

All of the frontend was built with **vibe coding** using **ChatGPT, V0, GitHub Copilot** powered by strong coffee, and a sprinkle of magic ✨☕🎶.
