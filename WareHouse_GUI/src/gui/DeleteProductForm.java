package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import db.DBConnect;
public class DeleteProductForm extends JFrame {

    private JTextField idField;
    private JButton deleteButton;

    public DeleteProductForm() {
        setTitle("Delete Product");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Product ID to Delete:"));
        idField = new JTextField(10);
        add(idField);

        deleteButton = new JButton("Delete");
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());

                    Connection conn = DBConnect.getConnection();
                    String sql = "DELETE FROM products WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);

                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "Product deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Product not found.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a number.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new DeleteProductForm();
    }
}
