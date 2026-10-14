# Student Management System

A Java-based Student Management System built with **Spring Boot** and **MySQL**. The project organizes student-related operations into controllers, services, repositories, and model classes.

## Features

Based on the current project structure, the application includes modules for:

- Student management
- Student attendance
- Student marks
- Authentication
- Global exception handling

> Note: The exact operations and permissions depend on the implementation of each endpoint and service.

## Tech Stack

- **Java**
- **Spring Boot**
- **Maven**
- **MySQL**
- **JDBC / Spring data-access components** — confirm the exact persistence approach against the current implementation
- **Postman** for API testing

## Project Structure

```text
StudentManagementSystem/
├── .mvn/
├── postman/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── swamini/
│   │   │           ├── controller/
│   │   │           ├── exception/
│   │   │           ├── model/
│   │   │           ├── service/
│   │   │           ├── util/
│   │   │           └── StudentManagementApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

The repository layer is also present in the source tree; ensure its package and directory structure match the Java package declarations.

## Prerequisites

Install the following before running the project:

- JDK compatible with the Java version configured in `pom.xml`
- Maven (or use the included Maven Wrapper)
- MySQL Server
- An API client such as Postman (optional)

## Setup and Run

### 1. Clone the repository

```bash
git clone https://github.com/nswamini18/-student-management-system.git
cd -student-management-system
```

### 2. Configure the database

1. Start MySQL.
2. Create a database for the application.
3. Open `src/main/resources/application.properties`.
4. Set the database URL, username, password, and any other required properties to match your local MySQL setup.

**Security:** Do not commit real database passwords, API keys, or other secrets. Use environment variables or a local, untracked configuration file for sensitive values.

### 3. Build the project

```bash
mvn clean compile
```

On Windows, you can also use the Maven Wrapper if it is available:

```powershell
.\mvnw.cmd clean compile
```

### 4. Run the application

```bash
mvn spring-boot:run
```

Or, if the Maven Wrapper is available on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The server port and base URL depend on the application configuration. If no custom port is configured, Spring Boot commonly uses port `8080`.

## API Testing

The repository contains Postman collection files under `postman/`. Import the available collection into Postman, check the request URLs and request bodies, and run the requests against your locally running application.

Review the controller classes to confirm the current endpoints, HTTP methods, required parameters, and request/response formats.

## Troubleshooting

- **Database connection fails:** Check that MySQL is running and that the database name and credentials in your configuration are correct.
- **Port already in use:** Stop the other process using the configured port or change the application port.
- **Maven build fails:** Read the first actual error in the Maven output. A failure during `clean` can occur when Windows locks files in the `target/` directory; it does not by itself prove that the Java source code has compilation errors.
- **Package or import errors:** Check that each Java file's `package` declaration matches its directory structure.

## Contributing

1. Create a branch for your changes.
2. Make and test your changes.
3. Commit with a descriptive message.
4. Push the branch and open a pull request if appropriate.

## License

No license information is specified here. Add a license section if you choose to publish the project under a particular license.
