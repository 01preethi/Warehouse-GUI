package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductDashboard extends JFrame {

    public ProductDashboard() {
        setTitle("Warehouse Product Dashboard");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton addBtn = new JButton("➕ Add Product");
        JButton viewBtn = new JButton("🔍 View Product");
        JButton updateBtn = new JButton("🔁 Update Product");
        JButton deleteBtn = new JButton("🗑️ Delete Product");
        JButton listBtn = new JButton("📋 List All Products");
        JButton exitBtn = new JButton("Exit");

        add(addBtn);
        add(viewBtn);
        add(updateBtn);
        add(deleteBtn);
        add(listBtn);
        add(exitBtn);

        // Button actions
        addBtn.addActionListener(e -> new ProductForm());
        viewBtn.addActionListener(e -> new ViewProductForm());
        updateBtn.addActionListener(e -> new UpdateProductForm());
        deleteBtn.addActionListener(e -> new DeleteProductForm());
        listBtn.addActionListener(e -> new ListProductsForm());
        exitBtn.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        new ProductDashboard();
    }
}
