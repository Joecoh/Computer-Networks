import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        String msg;
        while (true) {
            System.out.print("You: ");
            out.println(kb.readLine());
            msg = in.readLine();
            if (msg == null || msg.equalsIgnoreCase("bye")) break;
            System.out.println("Server: " + msg);
        }
        s.close();
    }
}
