import javax.swing.*;
import java.net.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            // Connect to server
            socket = new Socket("localhost", 4000);
            System.out.println("Client is running.");

            // Read image from disk
            System.out.println("Reading image from disk...");
            BufferedImage img = ImageIO.read(new File("digital_image_processing.jpg"));

            // Convert image to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            byte[] bytes = baos.toByteArray();
            baos.close();

            // Send image to server
            System.out.println("Sending image to server...");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);
            dos.close();

            System.out.println("Image sent to server.");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
