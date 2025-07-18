package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import db.DBConnect;

public class ViewProductForm extends JFrame {

    private JTextField idField;
    private JTextArea resultArea;
    private JButton viewButton;
    private JLabel qrLabel;

    public ViewProductForm() {
        setTitle("View Product");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Product ID:"));
        idField = new JTextField(10);
        add(idField);

        viewButton = new JButton("View");
        add(viewButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        qrLabel = new JLabel();
        add(qrLabel);

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(idField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID!");
                    return;
                }

                Connection conn = DBConnect.getConnection();
                try {
                    String sql = "SELECT * FROM products WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String name = rs.getString("name");
                        int qty = rs.getInt("quantity");
                        String qr = rs.getString("qr_code");

                        resultArea.setText("Product Found:\n");
                        resultArea.append("ID: " + id + "\n");
                        resultArea.append("Name: " + name + "\n");
                        resultArea.append("Quantity: " + qty + "\n");
                        resultArea.append("QR Code Path: " + qr + "\n");

                        // Load QR code image
                        try {
                            ImageIcon qrImage = new ImageIcon(qr);
                            Image scaledImage = qrImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                            qrLabel.setIcon(new ImageIcon(scaledImage));
                        } catch (Exception ex) {
                            qrLabel.setText("QR image not found.");
                        }

                    } else {
                        resultArea.setText("Product not found.");
                        qrLabel.setIcon(null);
                        qrLabel.setText("");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewProductForm();
    }
}
