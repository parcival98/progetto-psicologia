# WebApp per Psicologi - Software Architecture Design

Progetto per il corso di Software Architecture Design (A.A. 2025/2026).
Il sistema è una piattaforma distribuita a **microservizi** progettata per la digitalizzazione dell'agenda di uno studio psicologico, garantendo conformità GDPR e isolamento dei dati.

## 🏗️ Architettura
Il sistema adotta un'architettura a microservizi basata su:
- **API Gateway**: Punto di ingresso unico per tutte le richieste.
- **Microservizi**: Patient Service, Booking Service, Email Service.
- **Database**: PostgreSQL (con pattern *Database-per-Service*).
- **Comunicazione**: REST/JSON, JDBC.

## 🛠️ Stack Tecnologico
- **Backend**: Java, Spring Boot
- **Database**: PostgreSQL
- **Containerizzazione**: Docker, Docker Compose
- **Documentazione**: UML 2.0 (PlantUML)

## 🚀 Guida all'avvio (Getting Started)

### Prerequisiti
- Docker Desktop installato e in esecuzione.
- Java JDK 17+

### Installazione
1. Clona il repository:
   ```bash
   git clone https://github.com/parcival98/progetto-psicologia
   cd psicologa-microservizi
