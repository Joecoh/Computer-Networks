import java.net.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            // Start server
            server = new ServerSocket(4000);
            System.out.println("Server waiting for image...");

            // Accept client connection
            socket = server.accept();
            System.out.println("Client connected.");

            // Receive image data
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int len = dis.readInt();
            System.out.println("Image Size: " + (len / 1024) + " KB");

            byte[] data = new byte[len];
            dis.readFully(data);
            dis.close();

            // Convert byte array back to image
            InputStream ian = new ByteArrayInputStream(data);
            BufferedImage bImage = ImageIO.read(ian);

            // Display image in JFrame
            JFrame f = new JFrame("Server - Received Image");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(new JLabel(new ImageIcon(bImage)));
            f.pack();
            f.setVisible(true);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (socket != null) socket.close();
                if (server != null) server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
