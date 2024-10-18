# Learning Management System (LMS) using Java Spring Boot | NOUAPP

This repository contains the source code for a **Learning Management System (LMS)** built with **Java Spring Boot**. The LMS is designed to streamline academic processes like student management, course organization, feedback collection, and SMS notifications. It is specifically tailored for **Nalanda Open University** (NOUAPP) but can be customized for any educational institution.

## Key Features

- **Student Information System**: Manage student data including registration, profile management, and academic records.
- **Course Management**: Create and manage courses, assignments, and assessments.
- **Feedback Management**: Collect feedback from students for courses and faculty.
- **Complaint Management**: Handle and resolve student complaints in a structured manner.
- **Study Material Management**: Upload and manage course materials for students to access online.
- **Login Management System**: Secure login for students and admin with role-based access control.
- **SMS API Integration**: Send notifications and updates to students through SMS.

## Technologies Used

- **Java**: Backend programming language.
- **Spring Boot**: Framework for building the backend and REST APIs.
- **MySQL**: Database for storing student, course, and feedback data.
- **Thymeleaf**: Template engine for building dynamic web pages.
- **Spring Security**: For authentication and authorization.
- **Twilio API**: For SMS notifications.

## How to Deploy

To deploy this project, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/helloujjwall/learning-management-system-java-spring-boot
    ```
2. Configure the database in `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/noudb
    spring.datasource.username=root
    spring.datasource.password=""
    ```
3. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```
4. Open your browser and navigate to `http://localhost:8088` to access the application.

## Project Modules

1. **Student Information System**: Manages student data.
2. **Login Management System**: Secure login for students and admin.
3. **Enquiry Management**: Handles queries and information requests.
4. **Feedback Management**: Collects feedback from students.
5. **Complaint Management**: Manages student complaints.
6. **Course Management**: Administers courses and materials.
7. **Study Material Management**: Provides study resources.
8. **SMS API Integration**: Sends SMS alerts.

## Watch Demo Video
[Click Here](https://youtu.be/9iM0vEKQtDE?si=cjlqB7reNVvDw4ln)
