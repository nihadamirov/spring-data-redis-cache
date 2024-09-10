# Spring Data Redis Cache

This project is a Spring Boot application demonstrating the integration of Redis caching with Spring Data JPA. It provides functionality for managing customer and account data, leveraging Redis for efficient caching and using Liquibase for database schema management.

## Features

- **Redis Caching**: Utilizes Redis for faster data retrieval.
- **Liquibase**: Automated database schema management and migrations.
- **MapStruct**: Simplifies mapping between DTOs and entities.
- **Lombok**: Reduces boilerplate code with annotations.
- **Swagger**: Provides API documentation (if required in the future).
- **Logging**: Aspect-Oriented Programming (AOP) for info and error level logging.
- **Spring Boot DevTools**: Enhances development experience.

## Technologies

- Java 17
- Spring Boot 3.3.3
- Spring Data JPA
- Spring Data Redis
- Liquibase
- MapStruct
- Lombok
- Redis
- Swagger (optional)
- Spring Boot DevTools

## Setup

1. **Clone the repository:**

   ```bash
   cd spring-data-redis-cache
   ```

2. **Build the project:**

   ```bash
   ./gradlew build
   ```

3. **Run the application:**

   ```bash
   ./gradlew bootRun
   ```

4. **Start Docker containers:**

   ```bash
   docker-compose up
   ```

## RedisInsight

To view and manage Redis cache data, use RedisInsight. Ensure RedisInsight is running and connected to your Redis instance.

## Logging

The application uses AOP for logging, capturing method execution details at info and error levels.

## Development Tools

- **Spring Boot DevTools**: Provides automatic restarts and live reload capabilities.

## Docker Setup

- **Redis**: Containerized Redis instance for caching.
- **RedisInsight**: Containerized RedisInsight for managing and visualizing Redis data.

To start Docker containers:

```bash
docker-compose up
```
