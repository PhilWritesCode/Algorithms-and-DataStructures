import java.util.HashSet;
import java.util.Set;

/**
 * Created by Phil on 8/14/2015.
 */
public class AdjListGraph {

    private int edges;
    private int vertices;
    private Set<Integer>[] adj;

    public AdjListGraph(int numVertices) {
        edges = 0;
        vertices = numVertices;
        adj = new Set[numVertices];

        for(int i = 0; i < vertices; i++) {
            adj[i] = new HashSet<>();
        }
    }

    public void addEdge(int vertexIndexA, int vertexIndexB) {
        if(!isValidVertex(vertexIndexA) || !isValidVertex(vertexIndexB)) return;
        adj[vertexIndexA].add(vertexIndexB);
        adj[vertexIndexB].add(vertexIndexA);
        edges++;
    }

    private boolean isValidVertex(int vertexIndex) {
        return vertexIndex >=0 && vertexIndex < vertices;
    }

    public Iterable<Integer> adj(int vertexIndex) {
        if(!isValidVertex(vertexIndex)) throw new IndexOutOfBoundsException(vertexIndex + " not in the Graph!");
        return(adj[vertexIndex]);
    }

    public int numVertices() {
        return this.vertices;
    }
}
