# Student Management System

A console-based Student Management System built in **Core Java**, using **JDBC** and **MySQL** with a layered architecture (Model → DAO → Service → Main).

🚧 **Status:** Backend (JDBC + MySQL) complete. Currently being extended with **Spring Boot** and a **REST API**. Full setup instructions and documentation will be added once that migration is complete.

## Tech Stack (so far)
- **Language:** Java (Core Java, OOP)
- **Database:** MySQL
- **Connectivity:** JDBC (PreparedStatements, DAO pattern)
- **Build Tool:** Maven
- **Architecture:** Layered (Model, DAO, Service, Util, Exception)

## Features Implemented
- Role-based login (Admin / Student)
- Student CRUD operations
- Attendance tracking with percentage calculation
- Marks management with subject-wise analytics (aggregate SQL queries)
- Custom exception handling
- Input validation
- Credentials externalized via `.properties` file (not committed to version control)

## Coming Soon
- Spring Boot REST API
- Hibernate ORM layer
- Frontend integration
