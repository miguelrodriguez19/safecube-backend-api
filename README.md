# Safe Cube – Backend API

![Java](https://img.shields.io/badge/Java-21-red)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen)  
![License](https://img.shields.io/badge/License-Apache%202.0-blue)

> Secure, zero-knowledge password keeper for personal & family use.  
> Kotlin + Jetpack Compose mobile client (soon) • Java 21 + Spring Boot backend • Hexagonal + DDD • **AES-256-GCM** encryption.

---

## 🛠 Tech Stack

| Layer | Tech                                    |
|-------|-----------------------------------------|
|Language | **Java 21**                             |
|Framework | **Spring Boot 3.5.0**                   |
|Persistence | Spring Data JPA + **PostgreSQL 17**     |
|Migrations | **Flyway**                              |
|Mapping | **MapStruct 1.5**                       |
|Connection Pool | **HikariCP**                            |
|Build | **Maven 3.9** (+ wrapper)               |
|Testing | JUnit 5 · Mockito · H2 (profile `test`) |
|Documentation | springdoc-openapi (Swagger UI)          |

---

## ⚙️ Architecture

* Hexagonal / Ports & Adapters
* Domain-Driven Design terminology

---

## 🚀 Getting Started

### Prerequisites

| Tool | Version |
|------|---------|
| JDK  | 21 |
| Maven | 3.9.x |
| PostgreSQL | 16+ (local) |
