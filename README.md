# M1 Database Engine

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**m1** is a file-based, lightweight, and embeddable SQL database engine written from scratch in Java, inspired by the H2 database. This project is being developed to learn and implement the fundamental principles of database systems, including data storage, query processing, and data integrity.

## ✨ Features

-   **JDBC Compliant Driver**: Supports the standard Java Database Connectivity API, allowing for easy integration into any Java application.
-   **File-Based Storage**: All data is stored in a single `.db` file, making the database portable and easy to manage.
-   **Write-Ahead Logging (WAL)**: Features a journaling mechanism to prevent data loss and corruption during sudden system crashes or power outages.
-   **Basic SQL Support**: Supports essential DDL and DML commands such as `CREATE TABLE`, `INSERT`, `SELECT`, `UPDATE`, and `DELETE`.
-   **Lightweight and Zero-Dependency**: Requires no external server or complex dependencies to run. Simply add the JAR file to your project.
-   **Interactive Console**: Includes a command-line console, similar to H2's, allowing you to execute SQL queries interactively.

## 🚀 Quick Start

### Prerequisites

-   Java Development Kit (JDK) 8 or higher
-   Apache Maven (to build the project)

### Building the Project

Clone the repository and build the project using Maven:

```bash
git clone https://github.com/hacimertgokhan/m1-database.git
cd m1-database
mvn clean package
```

This command will create a JAR file named `m1-jdbc-[version].jar` in the `target/` directory. This JAR file contains both the database engine and the JDBC driver.

### Running the Console

You can start the interactive SQL console using the compiled JAR file:

```bash
java -jar target/m1-jdbc-[version].jar
```

When the console starts, you will be greeted with a screen like this:

```
Welcome to the M1 Database Console!
...
Connection successful. Type 'exit' to quit.
m1-sql>
```

You can now start writing your SQL commands:

```sql
m1-sql> CREATE TABLE PLAYERS (UUID, NAME, LEVEL)
Query OK. Rows affected: 0

m1-sql> INSERT INTO PLAYERS VALUES ('abc-123', 'Notch', 99)
Query OK. Rows affected: 1

m1-sql> SELECT * FROM PLAYERS WHERE LEVEL = 99
UUID    | NAME  | LEVEL
-------------------------
abc-123 | Notch | 99

(1 row found)

m1-sql> exit
```

These commands will create `mydb.db` (the data file) and `mydb.db.wal` (the log file) in the directory where the program is run.

## ☕ Using in Java Projects (JDBC)

Using the `m1` database in your own Java application (e.g., a Minecraft plugin or a web app) is straightforward.

**1. Add the Dependency**

If the project is published on Maven Central or another repository, add it to your `pom.xml`:

```xml
<dependency>
    <groupId>com.github.hacimertgokhan</groupId>
    <artifactId>m1-jdbc</artifactId>
    <version>0.1.0-ALPHA</version>
</dependency>
```

Alternatively, manually add the compiled `m1-jdbc-[version].jar` to your project's build path.

**2. Connect with JDBC**

Follow the standard JDBC steps to connect to the database and execute your queries.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class M1Example {

    public static void main(String[] args) {
        
        String url = "jdbc:m1:file:./my_app_data.db";

        try {
            
            
            
            
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            
            statement.executeUpdate("CREATE TABLE USERS (ID, USERNAME)");
            statement.executeUpdate("INSERT INTO USERS VALUES (1, 'mert')");

            
            ResultSet rs = statement.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID") + ", Username: " + rs.getString("USERNAME"));
            }

            
            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## 🛠️ Roadmap and Future Plans

`m1` is under active development. The next planned steps include:

-   [ ] **Advanced SQL Parser**: ANTLR integration to support more complex SQL clauses like `JOIN`, `GROUP BY`, and `ORDER BY`.
-   [ ] **Indexing**: B-Tree based indexing support to improve query performance.
-   [ ] **Data Types and Constraints**: Support for standard SQL data types (`INTEGER`, `VARCHAR`, `BOOLEAN`) and constraints (`PRIMARY KEY`, `NOT NULL`).
-   [ ] **Transaction Management**: `COMMIT` and `ROLLBACK` support for full ACID compliance.
-   [ ] **Web-Based Console**: An interface accessible from a browser, similar to the H2 Console.
-   [ ] **More Efficient File Management**: Developing a page-based storage engine to avoid rewriting the entire file on every change.

## 🤝 Contributing

Contributions are welcome! For bug reports, feature requests, or code contributions, please open an issue or submit a pull request.

## 📜 License

This project is licensed under the [MIT License](LICENSE).