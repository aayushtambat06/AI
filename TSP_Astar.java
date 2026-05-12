import java.util.*;

class Node {

    int city;              // Current city
    int cost;              // g(n)
    @SuppressWarnings("unused")
    int heuristic;         // h(n)
    int totalCost;         // f(n) = g(n) + h(n)

    boolean[] visited;     // Visited cities

    List<Integer> path;    // Path travelled

    Node(int city, int cost, int heuristic,
         boolean[] visited, List<Integer> path) {

        this.city = city;
        this.cost = cost;
        this.heuristic = heuristic;
        this.totalCost = cost + heuristic;

        this.visited = visited.clone();

        this.path = new ArrayList<>(path);
    }
}

public class TSP_Astar {

    static int n = 4;

    // Simple heuristic function
    static int heuristic(int[][] graph, boolean[] visited) {

        int minEdge = Integer.MAX_VALUE;

        // Find minimum edge in graph
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i != j) {
                    minEdge = Math.min(minEdge, graph[i][j]);
                }
            }
        }

        // Count unvisited cities
        int remaining = 0;

        for (boolean v : visited) {

            if (!v) {
                remaining++;
            }
        }

        return minEdge * remaining;
    }

    public static void main(String[] args) {

        int[][] graph = {

            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        // Priority Queue for A*
        PriorityQueue<Node> pq =
                new PriorityQueue<>(
                        Comparator.comparingInt(a -> a.totalCost));

        boolean[] visited = new boolean[n];

        visited[0] = true;

        List<Integer> startPath = new ArrayList<>();

        startPath.add(0);

        // Add starting node
        pq.add(new Node(
                0,
                0,
                heuristic(graph, visited),
                visited,
                startPath));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            // If all cities visited
            if (current.path.size() == n) {

                int finalCost =
                        current.cost +
                        graph[current.city][0];

                current.path.add(0);

                System.out.println("Path: " + current.path);

                System.out.println(
                        "Minimum Cost: " + finalCost);

                return;
            }

            // Explore neighboring cities
            for (int city = 0; city < n; city++) {

                if (!current.visited[city]) {

                    boolean[] newVisited =
                            current.visited.clone();

                    newVisited[city] = true;

                    List<Integer> newPath =
                            new ArrayList<>(current.path);

                    newPath.add(city);

                    int newCost =
                            current.cost +
                            graph[current.city][city];

                    int h =
                            heuristic(graph, newVisited);

                    pq.add(new Node(
                            city,
                            newCost,
                            h,
                            newVisited,
                            newPath));
                }
            }
        }
    }
}