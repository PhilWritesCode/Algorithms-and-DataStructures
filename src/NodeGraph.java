import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Phil on 8/14/2015.
 */
public class NodeGraph<Key, Value> {

    Map<Key, Node> nodes;
    Map<Key, Boolean> visited;
    Map<Key, Integer> distance;

    private class Node {

        private Key key;
        private Value value;
        private List<Node> edges;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;

            edges = new ArrayList();
        }
    }

    public NodeGraph() {
        nodes = new HashMap<Key, Node>();
        visited = new HashMap<Key, Boolean>();
        distance = new HashMap<Key, Integer>();
    }

    public void addNode(Key key, Value value) {
        nodes.put(key, new Node(key, value));
        visited.put(key, false);
        distance.put(key, Integer.MAX_VALUE);
    }

    public void addEdge(Key key1, Key key2) {
        nodes.get(key1).edges.add(nodes.get(key2));
        nodes.get(key2).edges.add(nodes.get(key1));
    }

    public void depthFirstSearch(Key nodeKey) {
        distance.put(nodeKey, 0);
        dfs(nodeKey);
    }

    private void dfs(Key nodeKey) {
        visited.put(nodeKey, true);
        Node node = nodes.get(nodeKey);
        int thisDistance = this.distance.get(nodeKey);
        System.out.println("thisDistance: " + (thisDistance));
        for(Node childNode : node.edges) {
            System.out.println("Visited? " + visited.get(childNode.key));
            if(visited.get(childNode.key) == false) {
                distance.put(childNode.key, (thisDistance + 1));
                System.out.println("Child " + childNode.key + " Distance: " + distance.get(childNode.key));
                dfs(childNode.key);
            }
        }
    }

    public static void main(String[] args) {
        NodeGraph<Integer, String> graph = new NodeGraph();
        graph.addNode(0,"zero");
        graph.addNode(1,"one");
        graph.addNode(2,"two");
        graph.addNode(3,"three");
        graph.addNode(4,"four");
        graph.addNode(5,"five");

        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        //graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        //graph.addEdge(3, 4);
        //graph.addEdge(2, 3);

        graph.depthFirstSearch(0);

        System.out.println("Edge from 0 to 0? " + graph.visited.get(0));
        System.out.println("Edge from 0 to 1? " + graph.visited.get(1));
        System.out.println("Edge from 0 to 2? " + graph.visited.get(2));
        System.out.println("Edge from 0 to 3? " + graph.visited.get(3));
        System.out.println("Edge from 0 to 4? " + graph.visited.get(4));
        System.out.println("Edge from 0 to 5? " + graph.visited.get(5));

        System.out.println("Distance from 0 to 0: " + graph.distance.get(0));
        System.out.println("Distance from 0 to 1: " + graph.distance.get(1));
        System.out.println("Distance from 0 to 1: " + graph.distance.get(1));
        System.out.println("Distance from 0 to 2: " + graph.distance.get(2));
        System.out.println("Distance from 0 to 3: " + graph.distance.get(3));
        System.out.println("Distance from 0 to 4: " + graph.distance.get(4));
        System.out.println("Distance from 0 to 5: " + graph.distance.get(5));
    }

}
