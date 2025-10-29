import java.util.*;

public class DistanceVectorRouting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];
        int[][] dist = new int[n][n];
        int[][] nextHop = new int[n][n];

        System.out.println("Enter cost matrix (999 for infinity):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
                dist[i][j] = cost[i][j];
                nextHop[i][j] = j;
            }

        // Distance Vector Algorithm
        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (dist[i][j] > cost[i][k] + dist[k][j]) {
                            dist[i][j] = cost[i][k] + dist[k][j];
                            nextHop[i][j] = nextHop[i][k];
                            updated = true;
                        }
                    }
                }
            }
        } while (updated);

        // Display final routing table
        for (int i = 0; i < n; i++) {
            System.out.println("\nRouting table for node " + i + ":");
            System.out.println("Dest\tNextHop\tDist");
            for (int j = 0; j < n; j++)
                System.out.println(j + "\t" + nextHop[i][j] + "\t" + dist[i][j]);
        }
    }
}
