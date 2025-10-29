import java.io.*;
import java.net.*;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter domain name: ");
        String domain = kb.readLine();

        byte[] sendData = domain.getBytes();
        InetAddress serverAddr = InetAddress.getByName("localhost");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, 5000);
        socket.send(sendPacket);

        byte[] recvData = new byte[1024];
        DatagramPacket recvPacket = new DatagramPacket(recvData, recvData.length);
        socket.receive(recvPacket);

        String response = new String(recvPacket.getData(), 0, recvPacket.getLength());
        System.out.println("Response: " + response);

        socket.close();
    }
}
