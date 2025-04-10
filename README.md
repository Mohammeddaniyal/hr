# HR Management System (Java Desktop App)

## 📋 Project Overview

This is a Java-based desktop application designed to manage employee designations. In this phase, the **Designation Module** has been developed, allowing users to perform CRUD (Create, Read, Update, Delete) operations.

The application stores data in files, and the **GUI** allows for managing designations. It provides a search feature and the ability to export data to a PDF file.

## ✨ Features

- **CRUD Operations** for designations.
- **Search** functionality for designations.
- **Export to PDF** functionality using iText7.
- Data persistence through **file storage** (no database integration yet).

## 🏛️ Project Architecture

The application is structured with two core layers at this stage:

- **Data Layer (DL):**  
  Handles file-based storage of data.

- **Business Logic Layer (BL):**  
  Manages the logic for handling designations.

The application does not yet interact with a database, using simple file storage instead.

## 🛠️ Technologies Used

- **Java SE** (Swing for GUI)
- **iText7** (for PDF export functionality)
- **File Handling** (for data persistence)

## 🧑‍💻 Development Journey

The initial development focused on creating a **basic user interface** for managing designations and implementing **CRUD operations**. This simple setup ensures a solid foundation for adding database integration and networking in future phases.

## 🚀 Next Steps

The next phase involves transitioning to **MySQL database** integration and incorporating **JDBC** for data persistence. Networking features will also be introduced to enable client-server communication.
