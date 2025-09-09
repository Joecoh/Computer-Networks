import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;

        try {
            // Start server on port 9000
            server = new ServerSocket(9000);
            System.out.println("Echo Server started. Waiting for client...");

            // Accept client
            client = server.accept();
            System.out.println("Client connected.");

            // Input from client
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // Output to client
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Received: " + line);
                out.println(line); // echo back
            }

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (client != null) client.close();
                if (server != null) server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
