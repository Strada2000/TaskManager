# TaskManager

A Spring Boot REST API for managing tasks, with JWT authentication, PostgreSQL database, and Docker support.

## Features

- User registration and login with JWT authentication
- Task CRUD operations (create, read, update, delete)
- Task filtering, searching, and pagination
- Role-based access (admin/user)
- Dockerized for easy deployment

## Getting Started

### Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose

### Running with Docker

1. Build the project JAR:
    ```sh
    ./mvnw clean package
    ```
2. Start the app and database:
    ```sh
    docker-compose up --build
    ```
   - The API will be available at `http://localhost:8080`
   - PostgreSQL will run on port `5433`

### API Endpoints

- `POST /api/auth/register` — Register a new user
- `POST /api/auth/login` — Login and receive JWT token
- `GET /api/tasks/all` — Get all tasks (requires JWT)
- `GET /api/tasks` — Get filtered/paginated tasks (requires JWT)
- `POST /api/tasks` — Create a new task (requires JWT)
- `PUT /api/tasks/{id}` — Update a task (requires JWT)
- `DELETE /api/tasks/{id}` — Delete a task (requires JWT)
- `GET /api/admin/status` — Administrator-only endpoint

### Configuration

- Database connection settings are in [`src/main/resources/application.properties`](src/main/resources/application.properties)
- Docker Compose configures the app and PostgreSQL in [`docker-compose.yml`](docker-compose.yml)

### Running Tests

```sh
./mvnw test
```

## License

This project is for educational/demo purposes.
