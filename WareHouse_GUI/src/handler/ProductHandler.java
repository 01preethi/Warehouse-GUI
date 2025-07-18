package handler;

import db.DBConnect;
import qr.QRGenerator;

import java.sql.*;

public class ProductHandler {

    //Add product with auto-generated ID
    public static int addProductReturnId(String name, int quantity) {
        Connection conn = DBConnect.getConnection();
        if (conn == null) return -1;

        try {
            String sql = "INSERT INTO products (name, quantity, created_at) VALUES (?, ?, NOW())";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setInt(2, quantity);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);

                String createdAt = fetchCreatedAt(conn, id);
                String qrText = generateQRText(id, name, quantity, createdAt);
                String fileName = "product_" + id;

                QRGenerator.generateQR(qrText, fileName);

                String qrPath = "images/" + fileName + ".png";
                PreparedStatement updateStmt = conn.prepareStatement("UPDATE products SET qr_code = ? WHERE id = ?");
                updateStmt.setString(1, qrPath);
                updateStmt.setInt(2, id);
                updateStmt.executeUpdate();

                return id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //Add product with custom ID
    public static int addProductWithCustomId(int id, String name, int quantity) {
        Connection conn = DBConnect.getConnection();
        if (conn == null) return -1;

        try {
            String sql = "INSERT INTO products (id, name, quantity, created_at) VALUES (?, ?, ?, NOW())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();

            String createdAt = fetchCreatedAt(conn, id);
            String qrText = generateQRText(id, name, quantity, createdAt);
            String fileName = "product_" + id;

            QRGenerator.generateQR(qrText, fileName);

            String qrPath = "images/" + fileName + ".png";
            PreparedStatement updateStmt = conn.prepareStatement("UPDATE products SET qr_code = ? WHERE id = ?");
            updateStmt.setString(1, qrPath);
            updateStmt.setInt(2, id);
            updateStmt.executeUpdate();

            return id;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //Generate full QR text
    private static String generateQRText(int id, String name, int qty, String createdAt) {
        return "ID: " + id + "\nName: " + name + "\nQuantity: " + qty + "\nCreated At: " + createdAt;
    }

    //Fetch created_at by ID
    public static String fetchCreatedAt(Connection conn, int id) {
        try {
            String sql = "SELECT created_at FROM products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp("created_at").toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //View product
    public static void viewProduct(int id) {
        Connection conn = DBConnect.getConnection();
        if (conn == null) return;

        try {
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Product Found:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Created At: " + rs.getTimestamp("created_at"));
                System.out.println("QR Code: " + rs.getString("qr_code"));
            } else {
                System.out.println("Product not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Update quantity
    public static void updateProduct(int id, int newQuantity) {
        Connection conn = DBConnect.getConnection();
        if (conn == null) return;

        try {
            String sql = "UPDATE products SET quantity = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Product quantity updated.");
            } else {
                System.out.println("Product not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Delete product
    public static void deleteProduct(int id) {
        Connection conn = DBConnect.getConnection();
        if (conn == null) return;

        try {
            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Product deleted.");
            } else {
                System.out.println("Product not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //List all products
    public static void listAllProducts() {
        Connection conn = DBConnect.getConnection();
        if (conn == null) return;

        try {
            String sql = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("All Products:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + " | Name: " + rs.getString("name")
                        + " | Qty: " + rs.getInt("quantity")
                        + " | Created At: " + rs.getTimestamp("created_at")
                        + " | QR: " + rs.getString("qr_code"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
