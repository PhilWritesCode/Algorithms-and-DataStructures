import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Phil on 8/15/2015.
 */
public class BreadthFirstSearch {

    boolean visited[];
    int distance[];
    int edgeTo[];

    int startingIndex;

    public BreadthFirstSearch(AdjListGraph graph, int startingIndex) {

        visited = new boolean[graph.numVertices()];
        distance = new int[graph.numVertices()];
        edgeTo = new int[graph.numVertices()];

        this.startingIndex = startingIndex;

        for (int i = 0; i < graph.numVertices(); i++) {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            edgeTo[i] = -1;
        }

        LinkedListQueue<Integer> queue = new LinkedListQueue();

        distance[startingIndex] = 0;
        visited[startingIndex] = true;
        queue.enqueue(startingIndex);
        int currentNodeIndex;
        while (!queue.isEmpty()) {
            currentNodeIndex = queue.dequeue();

            for (int childNodeIndex : graph.adj(currentNodeIndex)) {
                if (!visited[childNodeIndex]) {
                    visited[childNodeIndex] = true;
                    distance[childNodeIndex] = distance[currentNodeIndex] + 1;
                    edgeTo[childNodeIndex] = currentNodeIndex;
                    queue.enqueue(childNodeIndex);
                }
            }
        }
    }

    public boolean pathExists(int vertexIndex) {
        return visited[vertexIndex];
    }

    public int distanceTo(int vertexIndex) {
        return distance[vertexIndex];
    }

    public Iterable<Integer> shortestPathTo(int vertexIndex) {
        if(!pathExists(vertexIndex)) return null;
        LinkedList<Integer> stack = new LinkedList();

        for(int vertex = vertexIndex; vertex != startingIndex; vertex = edgeTo[vertex]) {
            stack.push(vertex);
        }

        stack.push(startingIndex);


        return stack;
    }

    public static void main(String[] args) {
        AdjListGraph graph = new AdjListGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2, 3);

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        System.out.println("Edge from 0 to 0? " + bfs.pathExists(0));
        System.out.println("Edge from 0 to 1? " + bfs.pathExists(1));
        System.out.println("Edge from 0 to 2? " + bfs.pathExists(2));
        System.out.println("Edge from 0 to 3? " + bfs.pathExists(3));
        System.out.println("Edge from 0 to 4? " + bfs.pathExists(4));
        System.out.println("Edge from 0 to 5? " + bfs.pathExists(5));

        System.out.println("Distance from 0 to 0: " + bfs.distanceTo(0));
        System.out.println("Distance from 0 to 1: " + bfs.distanceTo(1));
        System.out.println("Distance from 0 to 2: " + bfs.distanceTo(2));
        System.out.println("Distance from 0 to 3: " + bfs.distanceTo(3));
        System.out.println("Distance from 0 to 4: " + bfs.distanceTo(4));
        System.out.println("Distance from 0 to 5: " + bfs.distanceTo(5));

        System.out.print("Path to 4: ");
        for(int vertex : bfs.shortestPathTo(4)) {
            System.out.print(vertex + " ");
        }
        System.out.println();

        System.out.print("Path to 3: ");
        for(int vertex : bfs.shortestPathTo(3)) {
            System.out.print(vertex + " ");
        }
        System.out.println();

        System.out.print("Path to 2: ");
        for(int vertex : bfs.shortestPathTo(2)) {
            System.out.print(vertex + " ");
        }
        System.out.println();
    }
}
