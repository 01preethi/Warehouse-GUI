# WarehouseORSystem

A Java-based application for managing warehouse operations. 
This project uses **Java Swing** for the graphical user interface and connects to a **MySQL database** for backend storage. It also supports PDF generation for reports using
the iText library.

---

## 📁 Project Structure

WarehouseORSystem/
├── src/ # Java source code files
├── lib/ # External JAR dependencies
├── images/ # Image resources used in GUI (optional)
├── .classpath # Eclipse project classpath
├── .project # Eclipse project metadata


---

## 🚀 How to Run the Project

1. **Open the Project** in [Eclipse IDE](https://www.eclipse.org/).
2. **Add JARs to Build Path**:
   - Right-click the project → Build Path → Configure Build Path → Libraries → Add JARs
   - Select all files in the `lib/` folder:
     - `core-3.4.1.jar`
     - `javase-3.4.1.jar`
     - `mysql-connector-j-9.3.0.jar`
3. **Configure MySQL Database** (details below).
4. **Run the Application** from your `Main.java` or equivalent entry point.

---

## 📦 Required Libraries

The following libraries are located in the `lib/` folder:

| Library Name               | Purpose                               |
|---------------------------|----------------------------------------|
| core-3.4.1.jar            | iText core (for PDF generation)       |
| javase-3.4.1.jar          | iText Java SE support extensions      |
| mysql-connector-j-9.3.0.jar | MySQL JDBC Driver                    |

---

##  Database Setup

You need to set up a **MySQL database** and create the required tables.

###  Step 1: Create the Database
```sql
CREATE DATABASE warehouse;
USE warehouse;

 Step 2: Create Tables 

CREATE DATABASE warehouse;
USE warehouse;

CREATE TABLE products(
   id INT PRIMARY KEY,
   name VARCHAR(100),
   quantity INT,
   qr_code VARCHAR(200),
   created_at DATETIME
);
select*from products;

✅ Feel free to expand or modify tables based on your application features.

 Step 3: JDBC Connection Setup in Code

String url = "jdbc:mysql://localhost:3306/warehouse";
String user = "your_mysql_username";
String password = "your_mysql_password";

Connection conn = DriverManager.getConnection(url, user, password);

🧾 Notes
Recommended Java version: 8 or higher

Make sure MySQL service is running when launching the application

Add the .jar files from lib/ to the classpath before compiling or running

Ensure correct JDBC URL, username, and password in your code

