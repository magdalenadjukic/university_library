\# University Library — Client/Server Application



A Java desktop application built as a project for the Software Design course.  

The application follows a client-server architecture with socket communication and a MySQL database.



\## Technologies

\- Java (JDK 23)

\- NetBeans IDE

\- MySQL / SQLyog

\- JDBC (MySQL Connector 9.5.0)

\- Socket communication

\## Design Patterns
**Template Method Pattern** — used for defining the skeleton of operations on the server side

\## Project Structure

```

university\_library/

├── 0\_SEMINARSKI\_KLIJENT/     # Client application

├── 0\_SEMINARSKI\_SERVER/      # Server application

├── 0\_SEMINARSKI\_ZAJEDNICKI/  # Shared classes

├── lib/                      # MySQL Connector JAR

├── baza.sql                  # Database script

└── docs/                     # Documentation

```



\## Features

\- User login

\- Student management

\- Book lending and returns

\- Duty schedule management



\## Getting Started



\### Prerequisites

\- JDK 23

\- NetBeans IDE

\- MySQL server



\### Steps



\*\*1. Clone the repository\*\*

```bash

git clone https://github.com/magdalenadjukic/university\_library.git

```



\*\*2. Set up the database\*\*

\- Create a database named `0\_seminarski\_biblioteka`

\- Import `baza.sql` via SQLyog or MySQL Workbench



\*\*3. Configure the server\*\*

\- Navigate to `0\_SEMINARSKI\_SERVER/config/`

\- Copy `config.example.properties` → `config.properties`

\- Fill in your database credentials



\*\*4. Open in NetBeans\*\*

\- Open all 3 projects in this order:

&#x20; 1. `0\_SEMINARSKI\_ZAJEDNICKI`

&#x20; 2. `0\_SEMINARSKI\_SERVER`

&#x20; 3. `0\_SEMINARSKI\_KLIJENT`

\- Add `lib/mysql-connector-j-9.5.0.jar` to Libraries if needed



\*\*5. Run\*\*

\- Start `0\_SEMINARSKI\_SERVER` first

\- Then start `0\_SEMINARSKI\_KLIJENT`

