import java.util.*;

public class ARP_RARP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Predefined ARP Table (IP -> MAC)
        Map<String, String> arpTable = new HashMap<>();
        arpTable.put("192.168.1.1", "AA:BB:CC:DD:EE:01");
        arpTable.put("192.168.1.2", "AA:BB:CC:DD:EE:02");
        arpTable.put("192.168.1.3", "AA:BB:CC:DD:EE:03");

        System.out.println("1. ARP (Find MAC from IP)");
        System.out.println("2. RARP (Find IP from MAC)");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        if (choice == 1) {
            System.out.print("Enter IP Address: ");
            String ip = sc.nextLine();
            String mac = arpTable.get(ip);
            System.out.println(mac != null ? "MAC Address: " + mac : "IP not found!");
        } 
        else if (choice == 2) {
            System.out.print("Enter MAC Address: ");
            String mac = sc.nextLine();
            String ip = null;
            for (Map.Entry<String, String> entry : arpTable.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(mac)) {
                    ip = entry.getKey();
                    break;
                }
            }
            System.out.println(ip != null ? "IP Address: " + ip : "MAC not found!");
        } 
        else {
            System.out.println("Invalid choice!");
        }

        sc.close();
    }
}
