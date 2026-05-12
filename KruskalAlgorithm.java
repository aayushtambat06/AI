import java.util.*;

// Edge class
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    // Constructor
    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    // Sort edges according to weight
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}

public class KruskalAlgorithm {

    static int[] parent;

    // Find function
    static int find(int i) {
        if (parent[i] == i)
            return i;

        return parent[i] = find(parent[i]);
    }

    // Union function
    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        parent[xRoot] = yRoot;
    }

    // Kruskal Algorithm
    static void kruskal(int vertices, ArrayList<Edge> edges) {

        // Sort all edges
        Collections.sort(edges);

        // Initialize parent array
        parent = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int totalCost = 0;

        System.out.println("Edges in Minimum Spanning Tree:");

        // Process edges
        for (Edge e : edges) {

            int x = find(e.src);
            int y = find(e.dest);

            // If no cycle is formed
            if (x != y) {

                System.out.println(
                    e.src + " - " + e.dest +
                    " : " + e.weight
                );

                totalCost += e.weight;

                union(x, y);
            }
        }

        System.out.println("Minimum Cost = " + totalCost);
    }

    public static void main(String[] args) {

        int vertices = 4;

        ArrayList<Edge> edges = new ArrayList<>();

        // Add edges
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        kruskal(vertices, edges);
    }
}