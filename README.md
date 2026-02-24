<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Angular-16-DD0031?logo=angular&logoColor=white" alt="Angular">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Spring%20Cloud-2023.0.3-6DB33F?logo=spring&logoColor=white" alt="Spring Cloud">
</p>

# 🎓 Plateforme de Mobilité Universitaire

> A full-stack university student mobility platform enabling students to discover mobility programs, apply through dynamic forms, and track their applications — built with a microservices architecture using Spring Boot, Spring Cloud, and Angular.

---

## 📋 Table of Contents

- [Architecture Overview](#-architecture-overview)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Database Setup](#-database-setup)
- [Getting Started](#-getting-started)
- [Port Reference](#-port-reference)
- [API Routes & Gateway](#-api-routes--gateway)
- [Authentication Flow](#-authentication-flow)
- [Features](#-features)
- [Environment Variables](#-environment-variables)
- [Troubleshooting](#-troubleshooting)
- [Team](#-team)

---

## 🏗 Architecture Overview

```
┌─────────────────────────────────────────────────────────────────┐
│                        Angular Frontend                         │
│                     http://localhost:4200                        │
└──────────────────────────┬──────────────────────────────────────┘
                           │ All HTTP via Gateway
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Spring Cloud Gateway                         │
│                     http://localhost:8888                        │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │ /mobility/**  →  Authentication Service (:8885)         │    │
│  │ /**           →  Mobility Monolith (:8090)              │    │
│  └─────────────────────────────────────────────────────────┘    │
└──────────┬───────────────────────────────────┬──────────────────┘
           │                                   │
           ▼                                   ▼
┌─────────────────────┐           ┌─────────────────────────────┐
│ Authentication Svc  │           │    Mobility Monolith        │
│ :8885               │           │    :8090                    │
│ ─────────────────── │           │    ───────────────────────  │
│ • Login / Register  │           │    • Universities           │
│ • JWT Generation    │           │    • Dynamic Forms          │
│ • User Management   │           │    • Applications           │
│ • Role Management   │           │    • Ratings / Moyennes     │
│ • Password Reset    │           │    • Email Notifications    │
│                     │           │    • File Uploads           │
│ DB: db_users_       │           │    DB: mobilityy            │
│     mobilityy       │           │                             │
└────────┬────────────┘           └──────────┬──────────────────┘
         │                                   │
         └──────────┬────────────────────────┘
                    ▼
         ┌──────────────────┐
         │  Eureka Server   │
         │  :8761           │
         │  Service Registry│
         └──────────────────┘
```

---

## 🛠 Tech Stack

### Backend
| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Language runtime |
| **Spring Boot** | 3.3.4 | Application framework |
| **Spring Cloud** | 2023.0.3 | Microservices orchestration |
| **Spring Security** | 6.x | Authentication & authorization |
| **Spring Cloud Gateway** | — | API gateway & routing |
| **Netflix Eureka** | — | Service discovery |
| **Spring Data JPA** | — | ORM / data access |
| **OpenFeign** | — | Inter-service HTTP clients |
| **Auth0 java-jwt** | — | JWT token creation & validation |
| **Lombok** | — | Boilerplate reduction |
| **MySQL** | 8.0+ | Relational database |
| **Maven** | 3.9+ | Build tool & dependency management |

### Frontend
| Technology | Version | Purpose |
|------------|---------|---------|
| **Angular** | 16.2 | SPA framework |
| **RxJS** | 7.8 | Reactive programming |
| **Bootstrap** | 5.3 | Responsive CSS framework |
| **Angular Material** | 16.2 | Material Design components |
| **AdminLTE** | 3.2 | Admin dashboard template |
| **SweetAlert2** | 11.x | Beautiful alert modals |
| **TypeScript** | 4.9 | Type-safe JavaScript |

---

## 📁 Project Structure

```
pidev4eme/
├── backend/
│   ├── infrastructure/
│   │   ├── eureka-server/          # Service registry (port 8761)
│   │   └── gateway/                # API gateway (port 8888)
│   │
│   ├── microservices/
│   │   └── authentication-service/ # Auth, JWT, users (port 8885)
│   │
│   └── migration/
│       └── mobility-monolith/      # Core business logic (port 8090)
│
├── frontend/
│   └── angular-app/                # Angular 16 SPA (port 4200)
│       └── src/app/
│           ├── _services/          # API & auth services
│           ├── _helpers/           # HTTP interceptor
│           ├── login/              # Login page
│           ├── register/           # Registration page
│           ├── boards/             # Role-based dashboards
│           ├── CreateForms/        # Form & university management
│           ├── FrontComponents/    # Navbar, hero, shared UI
│           ├── dynamicform/        # Dynamic form rendering
│           ├── dto/                # TypeScript data models
│           └── ...                 # Feature modules
│
├── database/                       # DB scripts & migrations
├── docs/                           # Documentation
└── README.md
```

---

## ✅ Prerequisites

| Requirement | Version | Check Command |
|-------------|---------|---------------|
| **Java JDK** | 21+ | `java -version` |
| **Maven** | 3.9+ | `mvn -version` |
| **Node.js** | 18+ | `node -v` |
| **npm** | 9+ | `npm -v` |
| **Angular CLI** | 16.x | `ng version` |
| **MySQL** | 8.0+ | `mysql --version` |

> **Note:** Each Spring Boot module includes a Maven Wrapper (`mvnw` / `mvnw.cmd`), so a global Maven installation is optional.

---

## 🗄 Database Setup

The application uses **two MySQL databases** — both are auto-created on first run via `createDatabaseIfNotExist=true`:

| Database | Used By | Contents |
|----------|---------|----------|
| `db_users_mobilityy` | Authentication Service | Users, roles, credentials |
| `mobilityy` | Mobility Monolith | Universities, forms, applications, ratings |

**Default credentials:** `root` with no password.  
To customize, set environment variables (see [Environment Variables](#-environment-variables)).

Both databases use `spring.jpa.hibernate.ddl-auto=update`, so tables are created/updated automatically on startup. **No manual SQL scripts are needed.**

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/khaledchihab/pidev4eme.git
cd pidev4eme
```

### 2. Start MySQL

Ensure MySQL is running on `localhost:3306` with user `root` and no password (or set env vars).

### 3. Start the backend services (in order)

> ⚠️ **Order matters.** Eureka must be up before other services register with it.

```powershell
# Terminal 1 — Eureka Server (wait until "Started EurekaServerApplication" appears)
cd backend/infrastructure/eureka-server
./mvnw spring-boot:run

# Terminal 2 — API Gateway
cd backend/infrastructure/gateway
./mvnw spring-boot:run

# Terminal 3 — Authentication Service
cd backend/microservices/authentication-service
./mvnw spring-boot:run

# Terminal 4 — Mobility Monolith
cd backend/migration/mobility-monolith
./mvnw spring-boot:run
```

### 4. Start the frontend

```powershell
# Terminal 5 — Angular
cd frontend/angular-app
npm install
ng serve
```

### 5. Open the app

Navigate to **http://localhost:4200** in your browser.

---

## 🔌 Port Reference

| Service | Port | Health Check |
|---------|------|-------------|
| **Eureka Server** | `8761` | http://localhost:8761 |
| **API Gateway** | `8888` | http://localhost:8888/actuator/health |
| **Authentication Service** | `8885` | http://localhost:8888/mobility/actuator/health |
| **Mobility Monolith** | `8090` | http://localhost:8090/actuator/health |
| **Angular Frontend** | `4200` | http://localhost:4200 |
| **MySQL** | `3306` | `mysql -u root -e "SELECT 1"` |

---

## 🌐 API Routes & Gateway

All frontend requests go through the **Gateway** at `localhost:8888`. The gateway routes based on path prefixes:

| Path Pattern | Target Service | Examples |
|-------------|----------------|----------|
| `/mobility/**` | Authentication Service (`:8885`) | `/mobility/authentication/login`, `/mobility/users/create` |
| `/**` (everything else) | Mobility Monolith (`:8090`) | `/getUniversities`, `/sendEmails/{id}` |

### CORS Policy
- **Allowed Origin:** `http://localhost:4200`
- **Allowed Methods:** `GET, POST, PUT, DELETE, PATCH, OPTIONS`
- **Allowed Headers:** `*` (all)
- **Credentials:** Enabled

---

## 🔐 Authentication Flow

```
1. User submits login form
   └─→ Angular POST /mobility/authentication/login
       └─→ Gateway forwards to Auth Service (:8885)
           └─→ Auth Service validates credentials
               └─→ Returns JWT + roles + username

2. Angular stores JWT in localStorage (key: "Token")

3. HTTP Interceptor attaches "Authorization: Bearer <JWT>" to all subsequent requests

4. Monolith validates JWT locally
   └─→ Extracts roles from JWT claims (no DB lookup needed)
   └─→ Sets Spring Security context
   └─→ Processes request

5. If JWT expires → AuthGuard detects → redirects to /login
```

### JWT Details
| Property | Value |
|----------|-------|
| **Algorithm** | HMAC256 |
| **Expiry** | 7 days (604,800,000 ms) |
| **Claims** | `sub` (username), `roles` (string array), `id` (user ID) |
| **Secret** | Shared across auth-service and monolith (`application.security.jwt.secret`) |

### Built-in Resilience
- **Login retry:** 3 attempts with 2-second delay on server/network errors (won't retry on wrong password)
- **Token persistence:** If a valid (non-expired) token exists in localStorage, login is skipped entirely
- **Graceful errors:** Human-readable messages for network errors vs. invalid credentials vs. server errors

---

## ✨ Features

### 🎓 For Students (CANDIDAT / STUDENT)
- Browse available university mobility programs
- Fill dynamic application forms with configurable fields
- Track application status (PENDING → ACCEPTED / REJECTED)
- View application history
- Rate universities

### 🏛 For Universities (UNIVERSITY)
- Create and manage mobility program forms
- Configure dynamic form fields (text, number, select, etc.)
- Set application equations and coefficients for auto-ranking
- Review and manage incoming applications
- Send email notifications to accepted/rejected applicants

### 👤 For Administrators (ADMIN / SUPER_ADMIN)
- Full user management (CRUD, enable/disable accounts)
- Role assignment and removal
- System-wide oversight of all forms and applications
- Access all university dashboards

### 🔧 Platform Capabilities
- **Dynamic Forms:** Configurable form fields with custom types and coefficients
- **Auto-Ranking:** Math equation-based automatic candidate scoring (Moyenne)
- **File Upload:** Up to 10MB support for application attachments
- **Email Notifications:** SMTP-based notifications for application decisions
- **Service Discovery:** Eureka-based automatic service registration
- **API Gateway:** Centralized routing, CORS, and entry point

---

## 🔑 Environment Variables

All variables have sensible defaults for local development. Override as needed:

### Authentication Service
| Variable | Default | Description |
|----------|---------|-------------|
| `JWT_SECRET` | `AaZzBbCcYyDdXxEeWwFf` | JWT signing secret |
| `JWT_EXPIRATION` | `604800000` | Token TTL in milliseconds (7 days) |
| `MYSQL_USER` | `root` | Database username |
| `MYSQL_PWD` | *(empty)* | Database password |
| `MYSQL_HOST` | `localhost` | Database host |
| `MYSQL_PORT` | `3306` | Database port |
| `MYSQL_DATABASE` | `db_users_mobilityy` | Auth database name |
| `DISCOVERY_SERVICE` | `http://localhost:8761/eureka/` | Eureka URL |

### Mobility Monolith
| Variable | Default | Description |
|----------|---------|-------------|
| `JWT_SECRET` | `AaZzBbCcYyDdXxEeWwFf` | Must match auth-service |
| `MAIL_USERNAME` | *(configured)* | SMTP email sender |
| `MAIL_PASSWORD` | *(configured)* | SMTP app password |
| `DISCOVERY_SERVICE` | `http://localhost:8761/eureka/` | Eureka URL |

> ⚠️ **Important:** `JWT_SECRET` must be identical across both authentication-service and mobility-monolith. A mismatch will cause all authenticated requests to fail with 401.

---

## 🔧 Troubleshooting

### 🔴 "Cannot reach the server" on login
- **Cause:** Authentication Service is not running or hasn't registered with Eureka yet.
- **Fix:** Ensure all 4 backend services are running in order. Wait 30 seconds after starting auth-service for Eureka registration.

### 🔴 401 Unauthorized on monolith endpoints
- **Cause:** JWT secret mismatch between auth-service and monolith.
- **Fix:** Verify both `application.properties` files use the same `application.security.jwt.secret`.

### 🔴 CORS errors in browser console
- **Cause:** Frontend is not running on `http://localhost:4200` or gateway CORS config mismatch.
- **Fix:** Ensure Angular runs on port 4200. All requests must go through the gateway (`:8888`), not directly to services.

### 🔴 "Table doesn't exist" or Hibernate errors
- **Cause:** MySQL is not running or databases haven't been auto-created.
- **Fix:** Start MySQL. The `createDatabaseIfNotExist=true` flag handles database creation. Tables are auto-generated by `ddl-auto=update`.

### 🔴 Eureka dashboard shows no registered services
- **Cause:** Services started before Eureka was ready.
- **Fix:** Restart services after Eureka is fully up (visible at http://localhost:8761).

### 🟡 `npm install` fails
- **Cause:** Node.js version mismatch.
- **Fix:** Use Node.js 18.x (LTS). Angular 16 doesn't support Node 20+.

---

## 🧪 Quick Verification

After all services are running, verify with:

```bash
# 1. Check Eureka registry
curl http://localhost:8761/eureka/apps

# 2. Gateway health
curl http://localhost:8888/actuator/health

# 3. Register a test user
curl -X POST http://localhost:8888/mobility/users/create \
  -H "Content-Type: application/json" \
  -d '{"firstname":"Test","lastname":"User","username":"testdemo","password":"pass123","email":"test@demo.com","cin":"99999999","nationality":"TN","gender":"MALE","placeOfBirth":"Tunis","dateOfBirth":"2000-01-01"}'

# 4. Login
curl -X POST http://localhost:8888/mobility/authentication/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testdemo","password":"pass123"}'

# 5. Use the returned JWT to hit the monolith
curl http://localhost:8888/getUniversities \
  -H "Authorization: Bearer <PASTE_JWT_HERE>"

```

## 📄 License

This project was developed as part of an academic curriculum at [ESPRIT](https://esprit.tn) — École Supérieure Privée d'Ingénierie et de Technologies.

---

<p align="center">
  Built with ❤️ using Spring Boot, Angular, and Spring Cloud
</p>
