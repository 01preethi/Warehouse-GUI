package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import handler.ProductHandler;

public class ProductForm extends JFrame {

    private JTextField nameField, qtyField, idField;
    private JButton addButton;
    private JLabel qrLabel;

    public ProductForm() {
        setTitle("Add Product");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("Product ID (optional):"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        inputPanel.add(qtyField);

        addButton = new JButton("Add Product");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // QR label to display QR image
        qrLabel = new JLabel();
        qrLabel.setHorizontalAlignment(JLabel.CENTER);
        add(qrLabel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String qtyText = qtyField.getText().trim();
                String idText = idField.getText().trim();

                if (name.isEmpty() || qtyText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter name and quantity.");
                    return;
                }

                try {
                    int qty = Integer.parseInt(qtyText);
                    int resultId;

                    if (idText.isEmpty()) {
                        resultId = ProductHandler.addProductReturnId(name, qty);
                    } else {
                        int customId = Integer.parseInt(idText);
                        resultId = ProductHandler.addProductWithCustomId(customId, name, qty);
                    }

                    if (resultId != -1) {
                        String imagePath = "images/product_" + resultId + ".png";
                        File qrFile = new File(imagePath);

                        if (qrFile.exists()) {
                            ImageIcon icon = new ImageIcon(imagePath);
                            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                            qrLabel.setIcon(new ImageIcon(scaled));
                        }

                        JOptionPane.showMessageDialog(null, "Product added! QR generated. Scan with phone.");
                        nameField.setText("");
                        qtyField.setText("");
                        idField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add product.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantity and ID must be numbers.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ProductForm();
    }
}
