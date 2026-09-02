# Microservices-Based School Management System

A backend system for managing school and student records, built using a **Spring Boot microservices architecture** with service discovery, centralized API routing, and JWT-based authentication.

## Overview

This project demonstrates how a monolithic school management application can be decomposed into independent, communicating services — a common pattern in real-world backend systems. Each service owns its own responsibility and can be built, run, and scaled independently.

## Architecture

```
                        ┌─────────────────────┐
                        │   service-registry   │
                        │   (Eureka Server)     │
                        └──────────▲───────────┘
                                   │ registers with
              ┌────────────────────┼────────────────────┐
              │                    │                    │
   ┌──────────┴─────────┐ ┌────────┴──────────┐          │
   │  Student-service    │ │  School-service    │          │
   │  (business logic)   │ │  (business logic)  │          │
   └──────────▲──────────┘ └─────────▲──────────┘          │
              │                      │                      │
              └───────────┬──────────┘                      │
                           │ routes through                  │
                  ┌────────┴─────────┐                       │
                  │   cloud-server    │◄──────────────────────┘
                  │   (API Gateway)   │   discovers services via Eureka
                  └────────▲─────────┘
                           │
                     client requests
```

| Service | Role |
|---|---|
| **service-registry** | Eureka Server — every other service registers itself here, enabling dynamic discovery instead of hardcoded URLs |
| **cloud-server** | Spring Cloud API Gateway — the single entry point for all client requests; routes traffic to the correct downstream service |
| **Student-service** | Owns student data and authentication (JWT issuing/validation) |
| **School-service** | Owns school data |

Services communicate with each other over HTTP using `RestTemplate`.

## Tech Stack

- **Java, Spring Boot**
- **Spring Cloud** — Eureka (service discovery), Spring Cloud Gateway (routing)
- **Spring Security + JWT** — token-based authentication
- **REST APIs**
- **Maven**
- **Postman** — API testing

## Key Concepts Demonstrated

- **Service discovery** — services register with Eureka instead of relying on fixed hostnames/ports, so the system tolerates services moving or scaling.
- **Centralized routing** — the API Gateway is the only service exposed to clients; it looks up the right downstream service through Eureka and forwards the request.
- **JWT authentication** — a login endpoint issues a signed token, which is required on protected endpoints across services.
- **Inter-service communication** — one service can call another (e.g. Student-service fetching related School-service data) over internal HTTP calls.

## Running Locally

Each service is a standalone Spring Boot application. Start them in this order so that dependent services can register/discover correctly:

```bash
# 1. Start the Eureka server first
cd service-registry
./mvnw spring-boot:run

# 2. Start the business services
cd Student-service
./mvnw spring-boot:run

cd School-service
./mvnw spring-boot:run

# 3. Start the API Gateway last
cd cloud-server
./mvnw spring-boot:run
```

Once running, the Eureka dashboard (default `localhost:8761`) will show all registered services, and API requests can be sent through the gateway rather than directly to individual services.

## Project Status

This was an early architecture-focused project used to learn microservices fundamentals (service discovery, gateway routing, and inter-service auth) before building [EventSphere](#), a more complete full-stack, production-deployed booking platform.

## Author

**Darshit Parmar**
Java Full Stack Developer
[GitHub](https://github.com/Dar-shit) · [LinkedIn](https://linkedin.com/in/darshit-parmar)
