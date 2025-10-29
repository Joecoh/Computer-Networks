import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server ready...");
        Socket s = ss.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        String fileName = in.readLine();
        File file = new File(fileName);

        if (file.exists()) {
            out.println("FOUND");
            BufferedReader fr = new BufferedReader(new FileReader(file));
            String line;
            while ((line = fr.readLine()) != null)
                out.println(line);
            fr.close();
        } else {
            out.println("ERROR: File not found!");
        }

        s.close();
        ss.close();
    }
}
