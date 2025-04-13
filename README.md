# HR Management System (Java Desktop App)

## 📋 Project Overview

In this phase, the HR Management System underwent major improvements in functionality and structure. The main focus was on finalizing the **Employee Management Module**, where users can manage employee details such as ID, name, designation, gender, PAN, and other personal information. The system now also supports handling and storing employee-related data in the MySQL database.

With this enhancement, the application is now capable of handling both **designation** and **employee** data through the database and provides a more complete HR management system.

## ✨ Features

- **CRUD Operations** for both designations and employees.
- **Search** functionality for both designations and employees.
- **Export to PDF** functionality for employee and designation data using iText7.
- **Data Persistence** through MySQL database.
- **Employee Management Module** added with fields like:
  - ID
  - Name
  - Designation
  - Date of Birth
  - Basic Salary
  - Gender
  - PAN
  - Aadhar

## 🏛️ Project Architecture

The architecture has been further expanded to accommodate the new **Employee Management Module**. The updated architecture now includes:

- **Data Layer (DL):**  
  - Handles database interactions for both designations and employee data.

- **Business Logic Layer (BL):**  
  - Manages business rules and logic for employee and designation management.

- **Presentation Layer (PL):**  
  - Provides the graphical user interface for CRUD operations on both designations and employees.

- **Database Data Layer (DBDL):**  
  - Handles all database interactions using **JDBC** for designations and employee data.

- **Networking Layers:**  
  - **NetServer, NetClient, NetCommon, ProxyBL** remain the same, facilitating network communication for CRUD operations over the client-server setup.

## 🛠️ Technologies Used

- **Java SE** (Swing for GUI)
- **iText7** (for PDF export functionality)
- **MySQL** (for database integration)
- **JDBC** (for connecting and performing operations on MySQL)
- **Custom Networking** (Java socket programming)
- **File Handling** (for legacy support)

## 🧑‍💻 Development Journey

In this phase, the application was enhanced by implementing the **Employee Management Module**, which included adding employee data fields such as name, designation, gender, and salary. The data is now stored in the **MySQL database**.

The **Employee CRUD Operations** were added, enabling users to create, read, update, and delete employee records within the application. Additionally, the **GUI** was updated to accommodate employee management, with new forms and tables to display employee data.

The **Database Data Layer (DBDL)** was extended to include employee data management, and the **Business Logic Layer (BL)** was updated to include the logic for handling employee-specific operations.

## 🚀 Next Steps

The next phase will focus on refining the **user interface** and improving the **client-server interaction** to handle more complex operations. We will also work on improving the performance of the system and adding features such as **employee attendance management** and **salary management**.

