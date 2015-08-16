/**
 * Created by Phil on 8/15/2015.
 */
public class DepthFirstSearch {

    boolean[] visited;
    int[] distance;

    public DepthFirstSearch(AdjListGraph graph, int startingVertex) {

        visited = new boolean[graph.numVertices()];
        distance = new int[graph.numVertices()];

        for(int i = 0; i < visited.length; i++) {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }
        distance[startingVertex] = 0;
        dfs(graph, startingVertex);
    }

    private void dfs(AdjListGraph graph, int vertex) {
        visited[vertex] = true;
        for(int childVertex : graph.adj(vertex)) {
            if(visited[childVertex] == false) {
                distance[childVertex] = distance[vertex] + 1;
                dfs(graph, childVertex);
            }
        }
    }

    public boolean edgeTo(int vertexIndex) {
        return visited[vertexIndex];
    }

    public int distanceTo(int vertexIndex) {
        return distance[vertexIndex];
    }

    public static void main(String[] args) {
        AdjListGraph graph = new AdjListGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        //graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        //graph.addEdge(3, 4);
        //graph.addEdge(2, 3);

        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);

        System.out.println("Edge from 0 to 0? " + dfs.edgeTo(0));
        System.out.println("Edge from 0 to 1? " + dfs.edgeTo(1));
        System.out.println("Edge from 0 to 2? " + dfs.edgeTo(2));
        System.out.println("Edge from 0 to 3? " + dfs.edgeTo(3));
        System.out.println("Edge from 0 to 4? " + dfs.edgeTo(4));
        System.out.println("Edge from 0 to 5? " + dfs.edgeTo(5));

        System.out.println("Distance from 0 to 0: " + dfs.distanceTo(0));
        System.out.println("Distance from 0 to 1: " + dfs.distanceTo(1));
        System.out.println("Distance from 0 to 2: " + dfs.distanceTo(2));
        System.out.println("Distance from 0 to 3: " + dfs.distanceTo(3));
        System.out.println("Distance from 0 to 4: " + dfs.distanceTo(4));
        System.out.println("Distance from 0 to 5: " + dfs.distanceTo(5));
    }
}
