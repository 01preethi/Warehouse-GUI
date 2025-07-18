package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import db.DBConnect;

public class UpdateProductForm extends JFrame {

    private JTextField idField, qtyField;
    private JButton updateButton;

    public UpdateProductForm() {
        setTitle("Update Product Quantity");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Enter Product ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("New Quantity:"));
        qtyField = new JTextField();
        add(qtyField);

        updateButton = new JButton("Update");
        add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    int newQty = Integer.parseInt(qtyField.getText());

                    Connection conn = DBConnect.getConnection();
                    String sql = "UPDATE products SET quantity = ? WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, newQty);
                    stmt.setInt(2, id);

                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "Quantity updated.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Product not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateProductForm();
    }
}
