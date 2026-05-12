import java.util.*;

class Solution{
    public int[] dijkstra(int V, List<List<int[]>> adj, int src){
       int[] dist = new int[V];
       Arrays.fill(dist, (int)1e9);
       
       PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);

       dist[src] = 0;
       pq.add(new int[] {0, src});

       while(!pq.isEmpty()){
        int[] curr = pq.poll();
        int d = curr[0];
        int node = curr[1];

        if(d > dist[node]) continue;

        for(int[] edge : adj.get(node)){
            int next = edge[0];
            int wt = edge[1];

            if(dist[node] + wt < dist[next]) {
                dist[next] = dist[node] + wt;
                pq.add(new int[] {dist[next], next});
            }
        }
       }
       return dist;

       
    }
}

 public class DijkstraAlgorithm {
    public static void main(String[] args) {
        int V = 6;
        List<List<int[]>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(new int[]{1, 4});
        adj.get(0).add(new int[]{2, 4});

        adj.get(1).add(new int[]{2, 2});

        adj.get(2).add(new int[]{3, 3});
        adj.get(2).add(new int[]{4, 1});
        adj.get(2).add(new int[]{5, 6});

        adj.get(3).add(new int[]{5, 2});

        adj.get(4).add(new int[]{5, 3});

        Solution obj = new Solution();
        int[] dist = obj.dijkstra(V, adj, 0);

        for(int i=0; i<V; i++){
            System.out.println("Distance from source " + i + " = "  + dist[i]);
        }

    

    }

}
