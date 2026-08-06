# FairHold

FairHold is a backend-only reservation system built with Spring Boot. It allows users to view resources, create time slots, temporarily hold a slot, confirm a booking before the hold expires, and prevent double booking.

The project is designed as an interview-friendly backend MVP focused on reservation workflows, Redis-based temporary holds, clean layered architecture, and practical backend development concepts.

---

## Features

* User signup
* User login
* Password hashing using BCrypt
* JWT token generation on successful login
* Global exception handling
* Validation error handling
* Resource creation and listing
* Slot creation and listing
* Available slots API
* Temporary slot hold using Redis TTL
* First-come-first-serve hold behavior using Redis `setIfAbsent`
* Booking confirmation from temporary holds
* Booking cancellation
* User booking history
* All bookings API
* Swagger/OpenAPI documentation
* Docker-based Redis setup

---

## Tech Stack

* Java 21
* Spring Boot 4
* Spring Web MVC
* Spring Data JPA
* Spring Security
* PostgreSQL
* Redis
* Docker
* Maven
* Lombok
* Bean Validation
* Swagger/OpenAPI

---

## Architecture

FairHold follows a simple layered architecture:

```text
Controller
↓
Service
↓
Repository
↓
Database / Redis
```

### Controller Layer

The controller layer receives HTTP requests, validates request bodies, calls service methods, and returns HTTP responses.

### Service Layer

The service layer contains the main business logic, such as user registration, login, slot creation, temporary holds, booking confirmation, and booking cancellation.

### Repository Layer

The repository layer communicates with PostgreSQL using Spring Data JPA.

### Redis Layer

Redis is used for temporary hold storage with automatic expiry.

---

## Core Reservation Flow

```text
Create Resource
↓
Create Slot
↓
User places temporary hold on slot
↓
Redis stores hold with TTL
↓
User confirms booking before hold expires
↓
Booking is saved in PostgreSQL
↓
Slot becomes unavailable
```

---
## API Endpoints

### Auth APIs

```http
POST /api/auth/signup
POST /api/auth/login
```

### Resource APIs

```http
POST /api/resources
GET  /api/resources
```

### Slot APIs

```http
POST /api/slots
GET  /api/slots
GET  /api/slots/available
GET  /api/slots/resource/{resourceId}
```

### Hold APIs

```http
POST /api/holds
```

### Booking APIs

```http
POST  /api/bookings/confirm
GET   /api/bookings
GET   /api/bookings/user/{userId}
PATCH /api/bookings/{bookingId}/cancel
```

---

## Running Locally

### 1. Start PostgreSQL

Create a PostgreSQL database named:

```text
fairhold
```

Update database credentials in:

```text
src/main/resources/application.yml
```

Example:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fairhold
    username: postgres
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

### 2. Start Redis using Docker

Create and start Redis container:

```bash
docker run --name fairhold-redis -p 6379:6379 -d redis
```

If the container already exists:

```bash
docker start fairhold-redis
```

Check Redis:

```bash
docker exec -it fairhold-redis redis-cli ping
```

Expected output:

```text
PONG
```

---

### 3. Run Spring Boot App

For Linux/macOS:

```bash
./mvnw spring-boot:run
```

For Windows:

```bash
.\mvnw.cmd spring-boot:run
```

The application runs at:

```text
http://localhost:8080
```

---

## Swagger UI

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger can be used to view and test all APIs.

---

## Project Structure

```text
com.fairhold
│
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── repository
├── security
├── service
│   └── impl
├── exception
├── util
└── FairholdApplication
```
## Main Entities
### User
### Resource
### Slot
### Booking




