package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public static Connection getConnection() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/warehouse"; 
            String user = "root";
            String password = "0105";

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");
            return conn;
        } catch (Exception e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}

