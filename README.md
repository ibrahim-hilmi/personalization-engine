# Sweeter Backend

A Spring Boot REST API for personalized image content delivery. Analyzes user interactions to serve relevant images based on preferences.

## Features

- Image management with tagging system
- Personalized image recommendations
- User interaction tracking and analysis
- RESTful API endpoints

## Tech Stack

- Java 17
- Spring Boot 3.0.7
- MySQL Database
- Spring Data JPA
- MapStruct
- Lombok

## Requirements

- Java 17+
- Maven
- MySQL 8.0+

## Setup

1. Configure database in `src/main/resources/application.yml`
2. Build the project:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The API will be available at `http://localhost:8085`

## API Endpoints

- `GET /image/detail` - Get next personalized image
- `GET /image/{path}` - Serve image file
