# University Library — Client/Server System

A Java-based software system for tracking and managing university library operations, including book lending, student management, and duty scheduling.  
The application follows a client/server architecture with socket communication and a MySQL database.

## Technologies
- Java (JDK 23)
- NetBeans IDE
- MySQL / SQLyog
- JDBC (MySQL Connector 9.5.0)
- Socket communication

## Design Pattern
**Template Method Pattern** — used for defining the skeleton of operations on the server side

## Project Structure
```
university_library/
├── 0_SEMINARSKI_KLIJENT/     # Client application
├── 0_SEMINARSKI_SERVER/      # Server application
├── 0_SEMINARSKI_ZAJEDNICKI/  # Shared classes
├── lib/                      # MySQL Connector JAR
├── baza.sql                  # Database script
└── docs/                     # Documentation
```

## Features
- User login
- Student management
- Book lending and returns
- Duty schedule management

## Setup
1. Clone the repository
2. Create a database named `0_seminarski_biblioteka` and import `baza.sql`
3. Copy `config.example.properties` → `config.properties` and fill in your database credentials
4. Open projects in NetBeans in this order: `0_SEMINARSKI_ZAJEDNICKI` → `0_SEMINARSKI_SERVER` → `0_SEMINARSKI_KLIJENT`
5. Start `0_SEMINARSKI_SERVER` first, then `0_SEMINARSKI_KLIJENT`
