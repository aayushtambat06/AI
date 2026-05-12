import java.util.*;

// Class for BFS Traversal
public class BFSGraph {

    // Number of vertices
    private int vertices;

    // Adjacency List
    private LinkedList<Integer>[] adjList;

    // Constructor
    BFSGraph(int v) {
        vertices = v;

        // Create adjacency list
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Function to add edge in undirected graph
    void addEdge(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
    }

    // Recursive BFS function
    void BFS(Queue<Integer> queue, boolean[] visited) {

        // Base condition
        if (queue.isEmpty()) {
            return;
        }

        // Remove front vertex from queue
        int vertex = queue.poll();

        // Print vertex
        System.out.print(vertex + " ");

        // Visit all adjacent vertices
        for (int adjacent : adjList[vertex]) {

            // If not visited
            if (!visited[adjacent]) {

                // Mark as visited
                visited[adjacent] = true;

                // Add to queue
                queue.add(adjacent);
            }
        }

        // Recursive call
        BFS(queue, visited);
    }

    public static void main(String[] args) {

        // Create graph with 6 vertices
        BFSGraph graph = new BFSGraph(6);

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        // Visited array
        boolean[] visited = new boolean[6];

        // Queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Start from vertex 0
        visited[0] = true;
        queue.add(0);

        System.out.println("Breadth First Search Traversal:");

        // Call BFS
        graph.BFS(queue, visited);
    }
}