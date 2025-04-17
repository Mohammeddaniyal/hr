# HR Management System (Java Desktop App)

An employee management desktop application built using Java with a multi-layered architecture, custom networking, and MySQL database integration.

## 📋 Project Overview

This application helps manage employee designations with a user-friendly GUI. It was developed in multiple phases as new technologies were learned, including JDBC and Java Socket Programming.

Initially, data was handled via file storage. Later, it was migrated to a MySQL database with complete client-server communication using custom networking code.

## ✨ Features

- GUI for **Designation Module** and **Employee Module**.
- Perform **CRUD operations** on both designations and employees.
- **Search** functionality for designations and employees.
- **Export to PDF** functionality.
- Data persistence through **MySQL Database**.
- **Custom client-server communication** using Java Sockets.
- Multi-layered clean architecture (Separation of concerns).

## 🏛️ Project Architecture

- **DL (Data Layer):**  
  - Handles file-based storage initially.
- **BL (Business Logic Layer):**  
  - Business rules for managing designations and employees.
- **PL (Presentation Layer):**  
  - Java Swing GUI for interacting with users.
- **DBDL (Database Data Layer):**  
  - JDBC-based database operations (MySQL).
- **Networking Layer:**  
  - Custom Socket Programming with:
    - netserver
    - netclient
    - netcommon
    - proxybl
    - hrserver

> Presentation Layer (PL) → ProxyBL → Business Logic (BL) → Database Layer (DBDL) → MySQL

## 🛠️ Technologies Used

- **Java SE** (Swing, AWT)
- **JDBC** (Database Connectivity)
- **MySQL** (Backend Database)
- **Java Socket Programming** (Networking)
- **Libraries:**
  - Gson (for JSON)
  - iText7 (for PDF export)
- **Tools:**
  - Notepad and Command Line (No IDE used!)

## 📂 Repository

- [GitHub Repository Link](https://github.com/Mohammeddaniyal/hr)

## 🎯 Why This Architecture? (Behind the Scenes)

Instead of building a simple direct JDBC GUI application, this project was intentionally developed in **progressive phases**:

- **Phase 1:** File handling for understanding basic persistence.
- **Phase 2:** Database integration with JDBC for real-world storage.
- **Phase 3:** Custom client-server communication using Java Sockets to simulate enterprise-level distributed systems.

This approach provided hands-on learning about:

- Multi-layered architecture
- Decoupled and scalable system design
- Real-world networking concepts
- Database interaction best practices

It may seem redundant at first glance, but this structure reflects a deep learning journey and real-world application development practices.

## 💬 Why Take the Hard Way? (Learning Approach)

- **No IDEs or heavy frameworks were used.**  
- **No pre-built tools or generators were relied upon.**

Every line of code was manually written using Notepad and executed via Command Line to gain complete understanding and control over:

- How Java programs work internally
- Manual classpath management
- Pure fundamentals of networking, file handling, and JDBC
- Debugging and problem-solving without IDE assistance

This method was chosen intentionally to build **strong core programming skills** instead of relying on shortcuts. It ensures real confidence in handling any scale of project in the future — whether small apps or large distributed systems.

---

## 🚀 Future Updates

- 📸 Add screenshots of GUI
- 🛠️ Add detailed Setup & Run instructions
- 🧩 Bundle dependencies (Gson, MySQL Connector, iText7) internally
- 📜 Add batch files for easier execution
- 👥 Implement GUI for Employee Module

---

## 🙌 Contributions

This project is a solo learning journey to master real-world application development, software architecture, database management, and networking in Java from scratch.

---

### Employee Properties

The **Employee** module manages the following fields:

- **Employee ID:** Unique identifier for each employee.
- **Name:** Full name of the employee.
- **Designation Code:** Code representing the employee's designation.
- **Date of Birth:** The employee's birthdate.
- **Gender:** Employee's gender.
- **Is Indian:** Boolean flag indicating if the employee is Indian.
- **Basic Salary:** The employee's basic salary (BigDecimal).
- **PAN Number:** Permanent Account Number for taxation purposes.
- **Aadhar Card Number:** Aadhar card number for identification.

### Designation Properties

The **Designation** module manages the following fields:

- **Code:** Unique code representing the designation.
- **Title:** The title of the designation (e.g., "Software Engineer", "HR Manager").

