import java.util.*;

// Class for DFS Traversal
public class DFSGraph {

    // Number of vertices
    private int vertices;

    // Adjacency List
    private LinkedList<Integer>[] adjList;

    // Constructor
    DFSGraph(int v) {
        vertices = v;

        // Create adjacency list
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Function to add an edge in undirected graph
    void addEdge(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
    }

    // Recursive DFS function
    void DFS(int vertex, boolean[] visited) {

        // Mark current node as visited
        visited[vertex] = true;

        // Print the visited vertex
        System.out.print(vertex + " ");

        // Visit all adjacent vertices
        for (int adjacent : adjList[vertex]) {

            // If adjacent vertex is not visited
            if (!visited[adjacent]) {
                DFS(adjacent, visited);
            }
        }
    }

    public static void main(String[] args) {

        // Create graph with 6 vertices
        DFSGraph graph = new DFSGraph(6);

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        // Array to track visited vertices
        boolean[] visited = new boolean[6];

        System.out.println("Depth First Search Traversal:");

        // Start DFS from vertex 0
        graph.DFS(0, visited);
    }
}