## About this Project:

A clean and extensible Spring Boot REST API designed for managing to-do tasks. The application supports a complete CRUD (Create, Read, Update, Delete) workflow using Spring Data JPA/Hibernate and PostgreSQL. It follows a standard n-tier architecture, ensuring clear separation of concerns and maintainable code structure

## Features

- Create Task: Add new to-do items with title, description, and status 
- View Tasks: Retrieve a single task or list all tasks 
- Update Task: Edit task details such as content or completion status 
- Delete Task: Remove tasks from the system 
- Status Management: Mark tasks as completed or pending

## Tech Stack

**Backend:** [![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white)](#)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)](#)

**Database:** [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-ffffff?style=flat-square&logo=postgresql&logoColor=336791)](#)

**Build Tool:** [![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-ffffff.svg?style=flat-square&logo=intellij-idea&logoColor=black)](#)


## File Structure

```
├───.idea
├───.mvn
│   └───wrapper
├───sql-scripts
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───crudapi
│   │   │           └───springboot
│   │   │               └───cruddemo
│   │   │                   ├───controller
│   │   │                   ├───dao
│   │   │                   ├───entity
│   │   │                   └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   │           └───employees
│   └───test
│       └───java
│           └───com
│               └───crudapi
│                   └───springboot
│                       └───cruddemo
└───target
    ├───classes
    │   ├───com
    │   │   └───crudapi
    │   │       └───springboot
    │   │           └───cruddemo
    │   │               ├───controller
    │   │               ├───dao
    │   │               ├───entity
    │   │               └───service
    │   ├───static
    │   └───templates
    │       └───employees
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    └───test-classes
        └───com
            └───crudapi
                └───springboot
                    └───cruddemo
```

## Installation

1. Clone the repo
   ```sh
   git clone https://github.com/your_username_/Project-Name.git
   ```
2. Initialize the Database (Docker)
   ```sh
   docker run --name todo \
   -e POSTGRES_DB=tododb \
   -e POSTGRES_USER=root \
   -e POSTGRES_PASSWORD=root \
   -p 3307:5432 \
   -d postgres
   ```
3. Navigate to Project Directory
   ```sh
   cd <project-name>
   ```
4. Open VS Code from terminal
   ```sh
   code .
   ```
5. Start up Live server
6. Create a Postman
   ```sh
   - Workspace Open Postman
   - Click Workspaces → Create Workspace
   ```
7. Register First User (Initialize Data)
   ```sh
   POST http://localhost:8080/api/auth/register
   ```
   ```sh
   Content-Type: application/json
   ```
   ```sh
   {
       "username": "admin",
       "password": "admin123",
       "role": "ADMIN"
   }
8. Authenticate and Get JWT Token
   ```sh
   POST http://localhost:8080/api/auth/login
   ```
   ```sh
   Content-Type: application/json
   ```
   ```sh
   {
       "username": "admin",
       "password": "admin123"
   }
   ```
    Response Example
   ```sh
   {
       "token": "eyJhbGciOiJIUzI1NiJ9..."
   }

9. Configure Authorization in Postman
   ```sh
   - Open Postman
   - Go to Authorization tab 
   - Select Bearer Token
   - Paste the token:

## License

Distributed under the MIT License. See `LICENSE` for more information.