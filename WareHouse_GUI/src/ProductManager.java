import java.util.Scanner;
import handler.ProductHandler;

public class ProductManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== PRODUCT MENU ===");
            System.out.println("1. Add Product (Auto ID)");
            System.out.println("2. Add Product (Custom ID)");
            System.out.println("3. View Product by ID");
            System.out.println("4. Update Product Quantity");
            System.out.println("5. Delete Product");
            System.out.println("6. List All Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    ProductHandler.addProductReturnId(name, qty);
                    break;

                case 2:
                    System.out.print("Enter custom product ID: ");
                    int customId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter product name: ");
                    String cname = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int cqty = sc.nextInt();
                    ProductHandler.addProductWithCustomId(customId, cname, cqty);
                    break;

                case 3:
                    System.out.print("Enter product ID to view: ");
                    int viewId = sc.nextInt();
                    ProductHandler.viewProduct(viewId);
                    break;

                case 4:
                    System.out.print("Enter product ID to update: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQty = sc.nextInt();
                    ProductHandler.updateProduct(uid, newQty);
                    break;

                case 5:
                    System.out.print("Enter product ID to delete: ");
                    int did = sc.nextInt();
                    ProductHandler.deleteProduct(did);
                    break;

                case 6:
                    ProductHandler.listAllProducts();
                    break;

                case 0:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}
