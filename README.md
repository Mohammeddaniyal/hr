# HR Management System (Java Desktop App)

## 📋 Project Overview

In this phase, the focus was on finalizing the **Employee Management** and **Designation Management** modules. The **Designation module** includes functionality to manage employee titles and roles, while the **Employee module** manages detailed employee information, such as personal details, designation, salary, and identification numbers.

The application integrates these features with a **MySQL database**, allowing users to manage employee and designation data efficiently.

## ✨ Features

- **CRUD Operations** for both **Designations** and **Employees**.
- **Search** functionality for both designations and employees.
- **Export to PDF** functionality for designation and employee data using **iText7**.
- **Data Persistence** through **MySQL** database for storing designations and employee data.
- **Employee Module** includes the following fields:
  - Employee ID
  - Name
  - Designation (Code and Title)
  - Date of Birth
  - Gender
  - Indian Status (isIndian)
  - Basic Salary
  - PAN Number
  - Aadhar Card Number

## 🏛️ Project Architecture

The architecture has been updated to accommodate the **Employee Management** and **Designation Management** features, with clear separation of concerns between data handling, business logic, and presentation.

- **Data Layer (DL):**  
  - Handles database interactions for both employees and designations.

- **Business Logic Layer (BL):**  
  - Contains logic for handling business rules related to employee and designation management.

- **Presentation Layer (PL):**  
  - Provides the user interface for performing CRUD operations for employees and designations.

- **Database Data Layer (DBDL):**  
  - Interacts with the database through **JDBC** to manage employee and designation data.

- **Networking Layers:**  
  - **NetServer, NetClient, NetCommon, ProxyBL**: Handles client-server communication for CRUD operations over the network.

## 🛠️ Technologies Used

- **Java SE** (Swing for GUI)
- **iText7** (for PDF export functionality)
- **MySQL** (for database integration)
- **JDBC** (for connecting and performing operations on MySQL)
- **Custom Networking** (Java socket programming)
- **File Handling** (for legacy support)

## 🧑‍💻 Development Journey

This phase focused on completing the **Designation Management** and **Employee Management** modules. The **Designation module** allows HR managers to define the role titles and associated codes for employees, while the **Employee module** includes essential details like name, employee ID, salary, and PAN.

The **Business Logic Layer (BL)** was updated to manage the CRUD operations for both employees and designations, and the **Presentation Layer (PL)** was enhanced to allow easy interaction with the data, including a new search bar and table views for employees and designations.

The **Database Data Layer (DBDL)** was extended to store both employee and designation information using **JDBC**, while custom networking capabilities were utilized to facilitate client-server communication.

