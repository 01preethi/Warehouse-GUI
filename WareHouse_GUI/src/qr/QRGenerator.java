package qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRGenerator {

    public static void generateQR(String productDetails, String fileName) {
        int width = 300;
        int height = 300;

        try {
            //Create "images" directory if it doesn't exist
            File directory = new File("images");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //Generate file path
            String filePath = "images/" + fileName + ".png";
            Path path = Paths.get(filePath);

            //Generate QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(productDetails, BarcodeFormat.QR_CODE, width, height);

            //Write QR code to image file
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            System.out.println("QR Code saved at: " + filePath);

        } catch (WriterException | IOException e) {
            System.out.println("Error generating QR code:");
            e.printStackTrace();
        }
    }

    
//    public static void main(String[] args) {
//        String sampleData = "ID: 1\nName: Sample Pen\nQuantity: 25\nCreated At: 2025-06-09 12:00:00";
//        generateQR(sampleData, "sample_product");
//    }
}
