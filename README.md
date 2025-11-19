# 📚 Hexagonal Library API

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-green?style=flat-square)
![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-blue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)

A robust REST API for a Library Management System, built to demonstrate **Hexagonal Architecture (Ports & Adapters)**, **Domain-Driven Design (DDD)** principles, and **Clean Code** practices using Java 21 and Spring Boot 3.

---

## 🚀 Project Goal

The main goal of this project is not just to manage books, but to showcase how to **decouple business logic from the framework**.

In this architecture, the **Domain** is the core. It has no dependencies on Spring Boot, Databases, or external APIs. The "Outside World" (Infrastructure) adapts to the Domain through Ports.

## 🛠️ Tech Stack

* **Core:** Java 21 (LTS)
* **Framework:** Spring Boot 3.3
* **Database:** H2 In-Memory (Production-ready for PostgreSQL via JPA)
* **Documentation:** OpenAPI / Swagger UI
* **Testing:** JUnit 5, Mockito
* **Tools:** Maven, Lombok, Docker (Support)

---

## 📂 Project Structure (Hexagonal)

The codebase follows a strict separation of concerns:

```text
src/main/java/com/manuhcuartas/library
├── domain                 # 🟢 THE CORE (Pure Java, No Spring)
│   ├── model              # Entities with Rich Business Logic
│   ├── ports              # Interfaces (Input/Output)
│   └── service            # Domain Services (Implementation of use cases)
├── application            # 🟠 THE ORCHESTRATOR
│   ├── usecases           # Use Case Interfaces
│   └── dtos               # Data Transfer Objects
└── infrastructure         # 🔴 THE FRAMEWORK (Spring Boot lives here)
    ├── adapters           
    │   ├── input          # REST Controllers
    │   └── output         # JPA Repositories & Persistence Adapters
    └── config             # Spring Configuration (Beans, Swagger)