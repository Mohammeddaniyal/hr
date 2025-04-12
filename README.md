# HR Management System (Java Desktop App)

## 📋 Project Overview

In this phase, the application was enhanced by introducing **custom networking** for client-server communication. Custom Java socket programming was implemented to allow the system to exchange data between the client and server. This step involved the creation of multiple network-related packages, enabling remote interactions with the HR management system.

The architecture has been extended to include networking layers and a custom server-client communication mechanism.

## ✨ Features

- **CRUD Operations** for designations backed by MySQL database.
- **Search** functionality for designations.
- **Export to PDF** functionality using iText7.
- **Data Persistence** through MySQL database.
- **Networking** for client-server communication using custom Java sockets.
- Server-side handling with custom **HR Server** to manage incoming requests.

## 🏛️ Project Architecture

The architecture has been updated to introduce networking layers for communication between the client and server. The updated architecture now includes:

- **Data Layer (DL):**  
  - Handles file storage and database interactions.

- **Business Logic Layer (BL):**  
  - Manages business rules and logic for designations.

- **Presentation Layer (PL):**  
  - Provides the graphical user interface for CRUD operations.

- **Database Data Layer (DBDL):**  
  - Handles database interactions through **JDBC**.

- **Networking Layers:**
  - **NetServer:**  
    Handles server-side logic and communication with clients.
  - **NetClient:**  
    Handles the client-side communication with the server.
  - **NetCommon:**  
    Contains shared utilities and protocols for both server and client.
  - **ProxyBL:**  
    Acts as a mediator between the client and business logic layer for network communication.

## 🛠️ Technologies Used

- **Java SE** (Swing for GUI)
- **iText7** (for PDF export functionality)
- **MySQL** (for database integration)
- **JDBC** (for connecting and performing operations on MySQL)
- **Custom Networking** (Java socket programming)
- **File Handling** (for legacy support)

## 🧑‍💻 Development Journey

The focus of this phase was to implement **client-server communication** using **custom Java socket programming**. This was done to enable remote interactions with the HR Management System. By implementing networking from scratch, the system can now accept requests from remote clients and process them on the server side. The **HR Server** now manages incoming client requests, forwarding the necessary operations to the relevant business logic layers.

Additionally, a **ProxyBL** layer was introduced to mediate communication between the client and the business logic, ensuring smooth data handling across the network.

## 🚀 Next Steps

The next steps include refining the server-client interaction, improving error handling, and enhancing the user interface. Future updates will also include adding employee management features and optimizing the overall system performance.

