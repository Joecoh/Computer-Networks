import java.io.*;
import java.net.*;
import java.util.*;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);
        byte[] buf = new byte[1024];

        // Domain → IP mapping
        Map<String, String> dnsTable = new HashMap<>();
        dnsTable.put("google.com", "142.250.190.14");
        dnsTable.put("yahoo.com", "98.137.11.163");
        dnsTable.put("example.com", "93.184.216.34");

        System.out.println("DNS Server running...");
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String domain = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Query: " + domain);

            String ip = dnsTable.getOrDefault(domain, "ERROR: Domain not found!");
            byte[] response = ip.getBytes();

            DatagramPacket reply = new DatagramPacket(
                response, response.length, packet.getAddress(), packet.getPort());
            socket.send(reply);
        }
    }
}
