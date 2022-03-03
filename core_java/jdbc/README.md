# Introduction
This app is part of the core_java project and it is designed to exercise JDBC techniques in a connected PostgreSQL database. The java application uses Maven to build the projects using required dependencies. To connect to the database, JDBC implements a Data Access Object and a Data Transfer Object to allow us to create customized funcitons that process SQL queries to manage tables in the database such as update, delete, and create etc. This project contains two DAO implemented classes CustomerDAO and OrderDAO, with CRUD funcitonality. This project was done with sql scripts that were provided by Linkedin Learning.

Technologies used:
- Java
- JDBC
- PSQL
- Maven
- DBeaver
- IntelliJ IDEA

# Implementaiton
## ER Diagram
![image](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/blob/feature/README/core_java/jdbc/assets/JDBC%20ER%20Diagram.png)

## Design Patterns
DAO essentially allows us to seperate the application logic from the business logic, usually through a relational database. When using a DAO as a pure abstraction layer, it is recommended to include a DTO which provide a single set of fully encapsulated objects such as tables. DAOs typically process multiple DTOs, hence having fewer statements. On the other hand, repository patterns focus on single-table access per class. They are more suited for distributed systems as they also perform join operations for the code itself rather than joining them from the database. If the application is constrained by the database, repository patterns have the advantage since they do not require the database to do joins. Otherwise, DAOs work better for highly normalized systems because their joins are simplified.

# Test
Testing on this application is done manually by simply running the JDBCExecuter class and then using bash commands to connect to the PSQL database and executing SQL queries to verify the results. Java code debugging was done through logger by importing from the slf4j and log4j packages, which allow us to log table access in the DAO functions.
