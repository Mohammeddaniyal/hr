# HR Management System (Java Desktop App)

## 📋 Project Overview

In this phase, the **HR Management System** evolves by introducing **MySQL database integration**. The data handling previously done through file storage is now migrated to a **relational database** using **JDBC**. The application now allows for persistent storage of designation data within MySQL, ensuring that data is retained across sessions.

The architecture has been expanded to include more layers for better separation of concerns and scalability.

## ✨ Features

- **CRUD Operations** for designations (now backed by MySQL database).
- **Search** functionality for designations.
- **Export to PDF** functionality using iText7.
- **Data Persistence** through MySQL database.
- Updated **User Interface** reflecting new database-driven operations.
  
## 🏛️ Project Architecture

The updated architecture introduces the following layers:

- **Data Layer (DL):**  
  - Responsible for file storage (for legacy purposes) and interactions with the database.
  
- **Business Logic Layer (BL):**  
  - Manages the business rules and logic for designations.
  
- **Presentation Layer (PL):**  
  - Provides the graphical user interface (GUI) for CRUD operations.
  
- **Database Data Layer (DBDL):**  
  - Handles all MySQL database interactions using **JDBC** to persist designations in a relational database.

The **MySQL database** now stores the data, replacing the previous file-based approach.

## 🛠️ Technologies Used

- **Java SE** (Swing for GUI)
- **iText7** (for PDF export functionality)
- **MySQL** (for database integration)
- **JDBC** (for connecting and performing operations on MySQL)
- **File Handling** (for legacy support)

## 🧑‍💻 Development Journey

The main goal of this phase was to transition from **file storage** to a more robust **MySQL database** system. This was achieved through **JDBC** integration, allowing for data persistence across sessions. The design of the application was also updated to introduce the **DBDL** layer for database interaction, which improves scalability and performance.

With the database now integrated, the application can handle larger datasets more efficiently and persist data across restarts.

## 🚀 Next Steps

The next steps involve enhancing the application with **networking features** to allow client-server communication, enabling the application to handle requests from remote clients. Additionally, work will begin on adding more modules, including the Employee Management feature.
