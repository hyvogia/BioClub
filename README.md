# BioClub

Repository: BioClub — web application (Spring Boot + Thymeleaf)

## Visual

<img src="screencapture.png">

## System Overview

Purpose: Quiz Platform for NCEA Level 2 Biology.
Primary users: students, teachers, admins.
Key technologies: Java 21, Spring Boot, Spring Data JPA, Thymeleaf, MySQL.

## System Context

External systems:
- Browser clients (desktop / mobile)
- Optional YouTube embeds for video content
- Persistent RDBMS (MySQL)


## Architecture Overview

Architectural style: layered monolith (MVC)
- Presentation: Thymeleaf templates + static assets
- Controllers: page controllers and JSON endpoints
- Services: domain logic and orchestration
- Repositories: Spring Data JPA
Rationale: small scope, single deployable unit, easy CI/CD and testing.

## Project Structure

Tree (concise):
```text
.
├─ pom.xml
├─ src/
│  ├─ main/
│  │  ├─ java/com/hy/BioClub/
│  │  │  ├─ BioClubApplication.java
│  │  │  ├─ config/
│  │  │  ├─ controller/
│  │  │  ├─ model/
│  │  │  ├─ repository/
│  │  │  └─ service/
│  │  └─ resources/
│  │     ├─ templates/
│  │     └─ static/
│  └─ test/
└─ README.md
```

Main packages:

| Package | Responsibility |
|---|---|
| `controller` | HTTP endpoints (pages & API) |
| `model` | JPA entities and domain objects |
| `repository` | Data access via Spring Data |
| `service` | Business logic, orchestration |
| `config` | Application configuration & data loader |

## Development Setup

Prereqs: JDK 21, Maven, Git.

Quick start:
```bash
git clone <repo>
cd BioClub
./mvnw spring-boot:run
# or build
./mvnw clean package
java -jar target/BioClub-0.0.1-SNAPSHOT.jar
```
