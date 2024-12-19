# University Management System

This project is a Java-based University Management System designed to streamline and automate various administrative and academic processes within a university setting. It provides functionalities for managing students, faculty, courses, and other university resources.

## Table of Contents

- [Overview](#overview)
- [Main Functionalities](#main-functionalities)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Project](#running-the-project)
- [Database Schema](#database-schema)
- [Testing](#testing)
- [License](#license)

## Overview

The University Management System is developed using Java Swing for the user interface and MySQL for the backend database. It follows a modular architecture with clear separation between the controller, model, view, and database layers, ensuring maintainability and scalability.

## Main Functionalities

- **Student Management**: Add, update, delete, and view student records.
- **Faculty Management**: Manage faculty information and assignments.
- **Course Management**: Create and manage course offerings and enrollments.
- **Scheduling**: Organize class schedules and timetables.
- **Grading System**: Input and manage student grades.
- **Reports**: Generate various academic and administrative reports.

## Prerequisites

Before running the project, ensure you have the following software installed:

- **Java Development Kit (JDK)**: Version 8 or higher. Download from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html).
- **MySQL Database Server**: Version 5.7 or higher. Download from [MySQL's official website](https://dev.mysql.com/downloads/installer/).
- **Integrated Development Environment (IDE)**: Such as [Eclipse](https://www.eclipse.org/downloads/) or [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/klsavaj/PDP5010-University-Management-System.git
   cd PDP5010-University-Management-System
   ```

2. **Set Up the Database**:

   - Start the MySQL server.
   - Create a new database named `university_db`.
   - Execute the SQL commands provided in the `Database_schema.txt` file to create the necessary tables and relationships.

3. **Configure Database Connection**:

   - Open the `db/DatabaseConnection.java` file.
   - Update the database URL, username, and password to match your MySQL configuration.

## Running the Project

1. **Open the Project in Your IDE**:

   - Import the project into your preferred Java IDE.

2. **Compile and Run**:

   - Locate the `Main.java` file in the `lib` directory.
   - Compile and execute the `Main.java` file to launch the application.

3. **Login**:

   - Use the default administrator credentials to log in:
     - **Username**: `admin`
     - **Password**: `admin123`

   *Note: It's recommended to change the default credentials after the first login for security purposes.*

## Database Schema

The database schema is defined in the `Database_schema.txt` file. It includes the structure for tables such as `students`, `faculty`, `courses`, `enrollments`, and others, along with their relationships and constraints.

## Testing

JUnit test cases are provided in the `JUnit Test Cases.pdf` file. These tests cover various functionalities of the system to ensure reliability and correctness.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
