# University Management System

This project is a Java-based University Management System designed to streamline and automate various administrative and academic processes within a university setting. It follows the **Model-View-Controller (MVC)** architecture for a clean and maintainable code structure.

## Table of Contents

- [Overview](#overview)
- [Main Functionalities](#main-functionalities)
- [Focus on MVC Architecture](#focus-on-mvc-architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Project](#running-the-project)
- [License](#license)

## Overview

The University Management System is developed using Java Swing for the user interface and MySQL for the backend database. The project is modular, ensuring that the user interface (View), business logic (Model), and control flow (Controller) are separated to enhance maintainability and scalability.

## Main Functionalities

- **Student Management**: Add, update, delete, and view student details, including personal information, enrolled courses, and attendance records.
- **Faculty Management**: Manage faculty information, including assignments to specific courses.
- **Course Management**: Add and manage course details, enroll students in courses, and assign faculty.
- **Attendance Management**: Record and view attendance for students in various courses.
- **Grading System**: Input, update, and view grades for students by course.
- **Fee Calculation**: Calculate and manage tuition fees based on the courses a student is enrolled in.

## Focus on MVC Architecture

This project adheres to the MVC design pattern, which is structured as follows:

1. **Model**:
   - Represents the data and business logic of the application.
   - Interacts with the MySQL database for CRUD operations.
   - Key files:
     - `StudentModel.java`: Handles all student-related data operations.
     - `FacultyModel.java`: Manages faculty data.
     - `CourseModel.java`: Operates on course data.
     - `AttendanceModel.java`: Manages attendance records.
     - `FeeModel.java`: Handles fee calculations.

2. **View**:
   - Provides the graphical user interface (GUI) for the application.
   - Developed using Java Swing.
   - Key files:
     - `StudentView.java`: Displays student-related information and actions.
     - `FacultyView.java`: GUI for managing faculty details.
     - `CourseView.java`: Interface for course-related operations.
     - `MainView.java`: Entry point and navigation.

3. **Controller**:
   - Acts as a bridge between the Model and View.
   - Processes user inputs, interacts with the Model, and updates the View accordingly.
   - Key files:
     - `StudentController.java`: Manages user actions related to students.
     - `FacultyController.java`: Handles faculty-related operations.
     - `CourseController.java`: Controls course functionality.

## Prerequisites

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

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
