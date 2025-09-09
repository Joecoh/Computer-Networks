import java.net.*;
import java.io.*;

public class EchoClient {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            // Connect to server
            InetAddress host = InetAddress.getLocalHost();
            socket = new Socket(host, 9000);
            System.out.println("Connected to Echo Server.");

            // Output to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Input from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Input from keyboard
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while (true) {
                System.out.print("Client: ");
                line = userInput.readLine();

                if (line.equalsIgnoreCase("exit")) {
                    System.out.println("Closing connection...");
                    break;
                }

                out.println(line); // send to server
                System.out.println("Server: " + in.readLine());
            }

        } catch (IOException e) {
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
