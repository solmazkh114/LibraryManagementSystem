# Library Management System

This project is a basic library management system implemented in Java. The system allows users to borrow and return books. It is designed to manage student information, books in the library, and the borrowing and returning of books. 

We used a Microsoft SQL Server database to store and handle users' and books' info. In the sequel, after providing a general overview of the classes in this project, we will explain how to set up an MS. SQL Server Java driver.


## Classes Overview

### 1. `Library`
This is the main class that runs the library system. It interacts with users, accepts input, and calls methods from the `DataStore` and `Student` classes to borrow and return books.

#### Key Methods:
- **`run()`**: This method drives user interaction by displaying options, accepting input, and calling appropriate methods for borrowing, returning, and managing books.
- **`main(String[] args)`**: Entry point of the application where the library instance is created and the `run()` method is executed.

### 2. `Book`
The `Book` class represents a book in the library. Each book has a title, author, ID, number of copies available, and number of all copies.

### 3. `User`
The User class represents all types of users in the system, including students and faculty members. Each user has a name, ID, and a list of borrowed books.

### 4. `ConnectToDatabase`
This class is responsible for establishing the connection to the SQL Server database. It retrieves database credentials from the `config.properties` file and connects to the database using the JDBC driver. So, you need to create a `config.properties` file and save your SQL Server username and password in this format:

 - db.user= Your_User_Name
 - db.password= Your_Password

### 5. `DataFetcher`
This class handles all the `SELECT` queries in the system. It retrieves data from the database, such as user information, book details, and borrowing records.

### 6. `DataModifier`
This class handles all the `UPDATE` queries in the system. It manages borrowing and returning operations and updates the database accordingly.

### 7. `LoanManagment`
This class handles the borrow and return processes.

## Database Setup

Since the project connects to a SQL Server database, you'll need to download and configure the Microsoft SQL Server JDBC Driver (you need to have SQL Server installed on your local system). To this aim:

1. Download the [SQL Server driver](https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server)
2. Add the downloaded jre11.jar file to your project's library path to enable database connectivity. To do this, follow these steps if you are an Eclipse user:
   - Right-click your project in the Project Explorer
   - Go to Build Path > Configure Build Path
   - Under the Libraries tab, click Add External JARs (add the .jar file to your project's **classpath**)
   - Browse and select the jre11.jar file

### Create the `Library` database and insert data

The project uses a SQL Server database named Library. You can create and populate the database using the provided `CreateDatabase.sql` file which contains all the necessary SQL commands to create the database, including the tables and initial data. 

### Database Structure 

The database contains two schemas:

- users Schema: Contains the students and faculties tables.
- catalog Schema: Contains the books and copies tables.

See the following database diagram to better understand the relationships and structure of the database.

![image](https://github.com/user-attachments/assets/a46532ce-4399-41b3-a0ea-876beaeac749)

## Config SQL Server
If you cannot connect to the SQL Server and receive an error regarding connection to the database, you may need to manage network protocols and ports on SQL Server Configuration Manager. In fact, to allow remote connections, you need to ensure that TCP/IP is enabled. To open SQL Server Configuration Manager, press `Windows + R` and type the following command based on your SQL Server version:

- For SQL Server 2019: SQLServerManager15.msc
- For SQL Server 2017: SQLServerManager14.msc
- For SQL Server 2016: SQLServerManager13.msc

and then press Enter to launch the SQL Server Configuration Manager. Now, you need to enable TCP/IP protocol through the following steps:

 1. In SQL Server Configuration Manager, expand SQL Server Network Configuration.
 2. Click Protocols for your instance.
 3. In the right pane, ensure that TCP/IP is set to Enabled. If it's disabled, right-click it and select Enable







