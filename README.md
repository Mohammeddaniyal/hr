# HR Management System (Java Desktop App)

## 📋 Project Overview

In this phase, the main focus was to complete the **Employee Management Module** by developing the **GUI interface** for managing employee records. This includes adding functionality to allow users to easily create, read, update, and delete employee records through an intuitive user interface.

No additional modules or features were added during this phase. The primary goal was to refine the **Employee module's GUI** for better usability and user experience.

## ✨ Features

- **CRUD Operations** for **Employees** (Create, Read, Update, Delete).
- **Search** functionality for employees.
- **Export to PDF** functionality for employee data using **iText7**.
- **Employee Management** includes the following fields:
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

The architecture remains the same, with the following structure:

- **Data Layer (DL):**  
  - Handles database interactions for employee data.

- **Business Logic Layer (BL):**  
  - Manages the logic for CRUD operations on employee records.

- **Presentation Layer (PL):**  
  - The **Employee module's GUI** was developed during this phase to provide a user-friendly interface for managing employee data.

- **Database Data Layer (DBDL):**  
  - Stores employee data in the MySQL database using **JDBC**.

- **Networking Layers:**  
  - **NetServer, NetClient, NetCommon, ProxyBL** continue to handle network communication.

## 🛠️ Technologies Used

- **Java SE** (Swing for GUI)
- **iText7** (for PDF export functionality)
- **MySQL** (for database integration)
- **JDBC** (for connecting and performing operations on MySQL)
- **Custom Networking** (Java socket programming)
- **File Handling** (for legacy support)

## 🧑‍💻 Development Journey

The primary development focus of this phase was to create the **GUI for the Employee module**. The GUI now allows users to perform the following actions:

- Add new employee records.
- Update existing employee information.
- Delete employee records.
- Search for employees based on various criteria.
- Export employee data to PDF for documentation or reporting purposes.

The **Presentation Layer (PL)** was updated to include the employee management interface, which consists of forms for input, a table for displaying employee data, and buttons for various actions like **Add**, **Update**, **Delete**, and **Export**.

The **Business Logic Layer (BL)** was also updated to connect the GUI with the underlying **MySQL database**, using **JDBC** to ensure that changes made in the UI are reflected in the database.

## 🚀 Next Steps

The next steps will be to **test the GUI thoroughly** and ensure that all operations perform smoothly, without any bugs or issues. No new modules or features are planned for the time being; the focus will remain on refining the existing functionality and making sure the system is stable and reliable.
