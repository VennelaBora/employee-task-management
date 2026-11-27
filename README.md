# Employee Task Management

A small Java backend project for managing employees and their assigned tasks.

## Stack

Java 21, Spring Boot, Spring Data JPA, Hibernate, H2, Bean Validation and JUnit.

## What it does

- Employee CRUD
- Task CRUD
- Assign tasks to employees
- Filter tasks by status or employee
- Validation for API requests
- Centralized error responses
- JPA relationship between employees and tasks
- Transactional service methods
- H2 file database for local development

## Run

Java 21 is required.

If Maven is installed:

```bash
mvn clean test
mvn spring-boot:run
```

On Windows, the included `mvnw.cmd` downloads Maven 3.9.9 into the user's Maven folder when needed:

```powershell
.\mvnw.cmd clean test
.\mvnw.cmd spring-boot:run
```

Application: http://localhost:8080

H2 console: http://localhost:8080/h2-console

JDBC URL: `jdbc:h2:file:./data/employee-task-management`

User: `sa`

Password: empty

## Example

Create an employee:

```json
{
  "name": "Ravi Kumar",
  "email": "ravi@example.com",
  "department": "Engineering"
}
```

Create a task:

```json
{
  "title": "Update employee profile API",
  "description": "Add validation for profile changes",
  "status": "TODO",
  "employeeId": 1
}
```

## Endpoints

`GET /api/employees`

`POST /api/employees`

`GET /api/employees/{id}`

`PUT /api/employees/{id}`

`DELETE /api/employees/{id}`

`GET /api/tasks`

`GET /api/tasks?status=TODO`

`GET /api/tasks?employeeId=1`

`POST /api/tasks`

`PUT /api/tasks/{id}`

`DELETE /api/tasks/{id}`
