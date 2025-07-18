package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import db.DBConnect;
public class ListProductsForm extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ListProductsForm() {
        setTitle("All Products");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table columns
        String[] columnNames = { "ID", "Name", "Quantity", "QR Code", "Created At" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }

    private void loadData() {
        Connection conn = DBConnect.getConnection();
        if (conn == null) return;

        try {
            String sql = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String qr = rs.getString("qr_code");
                Timestamp created = rs.getTimestamp("created_at");

                model.addRow(new Object[] { id, name, quantity, qr, created });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ListProductsForm();
    }
}
