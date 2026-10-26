# TaskFlow API

TaskFlow API is a simple RESTful task management backend developed using Spring Boot.

This project was completed as part of the **NTU SCTP Advanced Professional Certificate in AI Engineering – Module 3 Backend Development** assignment.

The application demonstrates core backend development concepts including layered architecture, REST API development, dependency injection, Spring Data JPA, object-relational mapping, and persistence using an H2 in-memory database.

---

## Features

The API supports the following operations:

- Create a new task
- Retrieve all tasks
- Retrieve a task by ID
- Store task data using Spring Data JPA
- Automatically generate task IDs
- Persist task data using an H2 in-memory database

---

## Technology Stack

- Java 21
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Lombok
- Maven

---

## Application Architecture

The application follows a layered architecture:

```text
Client
   |
   v
Controller
   |
   v
Service
   |
   v
Repository
   |
   v
H2 Database
```

### Controller Layer

The controller handles incoming HTTP requests and returns HTTP responses.

```java
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    ...
}
```

### Service Layer

The service layer contains the application logic and communicates with the repository.

Constructor injection is used to provide the required dependencies.

```java
public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
}
```

When a Spring-managed class has only one constructor, `@Autowired` is not required on that constructor.

### Repository Layer

The repository uses Spring Data JPA for database access.

```java
public interface TaskRepository extends JpaRepository<Task, Long> {
}
```

By extending `JpaRepository`, common persistence operations are provided automatically, including:

```text
findAll()
findById()
save()
```

### Entity Layer

The `Task` class is a JPA entity mapped to the `tasks` database table.

Each task contains:

- `id`
- `title`
- `description`
- `priority`
- `dueDate`
- `completed`

The primary key is automatically generated using JPA.

---

## REST API Endpoints

| HTTP Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/tasks` | Retrieve all tasks |
| `GET` | `/api/tasks/{id}` | Retrieve a task by ID |
| `POST` | `/api/tasks` | Create a new task |

---

## Create a Task

### Endpoint

```http
POST /api/tasks
```

### Example Request

```json
{
  "title": "Complete Module 3",
  "description": "Finish assignment",
  "priority": "HIGH",
  "dueDate": "2026-05-20",
  "completed": false
}
```

### Example Response

```json
{
  "id": 1,
  "title": "Complete Module 3",
  "description": "Finish assignment",
  "priority": "HIGH",
  "dueDate": "2026-05-20",
  "completed": false
}
```

A successfully created task returns:

```text
HTTP 201 Created
```

### Postman Test

The following screenshot shows a task being created using Postman.

![Postman API Test](docs/02%20postman.png)

The API successfully returns an HTTP **201 Created** response together with the newly created task.

---

## Retrieve All Tasks

### Endpoint

```http
GET /api/tasks
```

### Example cURL Request

```bash
curl http://localhost:8080/api/tasks
```

The API returns a JSON array containing all tasks currently stored in the H2 database.

Example:

```json
[
  {
    "id": 1,
    "title": "Complete Module 3",
    "description": "Finish assignment",
    "priority": "HIGH",
    "dueDate": "2026-05-20",
    "completed": false
  }
]
```

### cURL Test

The following screenshot shows the REST API being tested from the command line using cURL.

![cURL API Test](docs/01%20curl.png)

---

## Retrieve a Task by ID

### Endpoint

```http
GET /api/tasks/{id}
```

### Example

```bash
curl http://localhost:8080/api/tasks/1
```

Example response:
![cURL API Test](docs/04%20postman%20getOneTask202.png)

---

If the requested task cannot be found, the service throws a runtime exception indicating that the task does not exist.

Example response:
![cURL API Test](docs/05%20postman%20getOneTaskNotfound.png)

---

## H2 Database

The application uses an **H2 in-memory relational database**.

The database configuration is defined in:

```text
src/main/resources/application.properties
```

Because the application uses:

```text
jdbc:h2:mem:taskflowdb
```

the database is stored in memory and its data is reset when the application stops.

---

## Project Structure

```text
taskflow-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── taskflow/
│   │   │           └── taskflow_api/
│   │   │               ├── controller/
│   │   │               │   └── TaskController.java
│   │   │               ├── entity/
│   │   │               │   └── Task.java
│   │   │               ├── repository/
│   │   │               │   └── TaskRepository.java
│   │   │               ├── service/
│   │   │               │   └── TaskService.java
│   │   │               └── TaskflowApiApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── docs/
│   ├── 01 curl.png
│   └── 02 postman.png
│   └── 03 postman allTasks.png
│   └── 04 postman getOneTask202.png
│   └── 05 postman getOneTaskNotfound.png
│
├── pom.xml
└── README.md
```

---

## Running the Application

### Prerequisites

Ensure the following are installed:

- Java 21
- Maven

Check the Java version:

```bash
java --version
```

Check the Maven version:

```bash
mvn --version
```

### Run the Application

From the project root directory:

```bash
mvn spring-boot:run
```

Once started, the application is available at:

```text
http://localhost:8080
```

The Task API is available at:

```text
http://localhost:8080/api/tasks
```

---

## Example cURL Commands

### Create a Task

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete Module 3",
    "description": "Finish assignment",
    "priority": "HIGH",
    "dueDate": "2026-05-20",
    "completed": false
  }'
```

### Retrieve All Tasks

```bash
curl http://localhost:8080/api/tasks
```

### Retrieve Task by ID

```bash
curl http://localhost:8080/api/tasks/1
```

---

## Key Concepts Demonstrated

This project demonstrates several Java and Spring Boot concepts covered in Module 3:

- RESTful API development
- Layered architecture
- Spring Boot
- Spring MVC
- `@RestController`
- `@RequestMapping`
- `@GetMapping`
- `@PostMapping`
- `@RequestBody`
- `@PathVariable`
- `ResponseEntity`
- Dependency Injection
- Constructor Injection
- Inversion of Control
- JPA Entities
- `@Entity`
- `@Id`
- `@GeneratedValue`
- Spring Data JPA
- `JpaRepository`
- Object-Relational Mapping
- H2 Database
- JSON request and response handling
- HTTP status codes
- Maven dependency management

---

## Author

**Raihan**

---

## Assignment

**NTU SCTP Advanced Professional Certificate in AI Engineering**  
**Module 3 – Backend Development**

This repository contains the completed TaskFlow API assignment.
