import java.io.*;
import java.net.*;

public class SimpleHttpClient {
    public static void main(String[] args) {
        String host = "example.com"; // Change this to any website
        String fileName = "downloaded.html";

        try (Socket socket = new Socket(host, 80);
             PrintWriter out = new PrintWriter(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             FileWriter file = new FileWriter(fileName)) {

            // Send basic HTTP GET request
            out.print("GET / HTTP/1.1\r\n");
            out.print("Host: " + host + "\r\n");
            out.print("Connection: close\r\n\r\n");
            out.flush();

            // Read response and write to file
            String line;
            while ((line = in.readLine()) != null)
                file.write(line + "\n");

            System.out.println("Page saved as " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
