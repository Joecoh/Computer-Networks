import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        System.out.print("Enter file name: ");
        String fname = kb.readLine();
        out.println(fname);

        String line = in.readLine();
        if (line.equals("FOUND")) {
            FileWriter fw = new FileWriter("received_" + fname);
            while ((line = in.readLine()) != null)
                fw.write(line + "\n");
            fw.close();
            System.out.println("File downloaded successfully!");
        } else {
            System.out.println(line);
        }

        s.close();
    }
}
