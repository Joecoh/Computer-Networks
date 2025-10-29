import java.util.*;

public class LinkStateRouting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];
        System.out.println("Enter cost matrix (999 for infinity):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();

        System.out.print("Enter source node: ");
        int src = sc.nextInt();

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, 999);
        dist[src] = 0;

        // Dijkstra's Algorithm
        for (int count = 0; count < n - 1; count++) {
            int u = -1, min = 999;
            for (int i = 0; i < n; i++)
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            visited[u] = true;

            for (int v = 0; v < n; v++)
                if (!visited[v] && cost[u][v] != 999 && dist[v] > dist[u] + cost[u][v])
                    dist[v] = dist[u] + cost[u][v];
        }

        System.out.println("\nShortest distance from node " + src + ":");
        for (int i = 0; i < n; i++)
            System.out.println("To node " + i + " = " + dist[i]);
    }
}
