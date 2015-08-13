/**
 * Created by Phil on 8/12/2015.
 */
public class Trie {

    private class Node {
        private Node[] children;
        private int value;

        private Node() {
            children = new Node[256];
            value = -1;
        }
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String key, int value) {
        insert(key, value, 0, root);
    }

    private void insert(String key, int value, int keyIndex, Node node) {
        if(keyIndex > key.length()) return;

        if(keyIndex == key.length()) {
            node.value = value;
            return;
        }

        char nextKey = key.charAt(keyIndex);

        if(node.children[nextKey] == null)
            node.children[nextKey] = new Node();

        insert(key, value, keyIndex+1, node.children[nextKey]);
    }

    public boolean contains(String key) {

        Node node = root;
        int keyIndex = 0;

        char nextKey = key.charAt(keyIndex++);
        while (keyIndex <= key.length() && node.children[nextKey] != null) {
            node = node.children[nextKey];
            if(keyIndex == key.length()) return node.value != -1;
            nextKey = key.charAt(keyIndex++);
        }

        return false;
    }

    public int get(String key) {
        Node node = root;
        int keyIndex = 0;

        char nextKey = key.charAt(keyIndex++);
        while (keyIndex <= key.length() && node.children[nextKey] != null) {
            node = node.children[nextKey];
            if(keyIndex == key.length()) break;
            nextKey = key.charAt(keyIndex++);
        }

        if(keyIndex == key.length()) return node.value;

        return -1;
    }

    public static void main(String[] args) {
        Trie t = new Trie();

        System.out.println("Contains Anna? " + t.contains("Anna"));
        t.insert("Anna", 26);
        t.insert("Anne", 24);
        System.out.println("Contains Anna? " + t.contains("Anna"));
        System.out.println("Anna: " + t.get("Anna"));
        System.out.println("Contains Anne? " + t.contains("Anne"));
        System.out.println("Anne: " + t.get("Anne"));
        t.insert("Anne", 31);
        System.out.println("Anne: " + t.get("Anne"));
        System.out.println("Contains Ann? " + t.contains("Ann"));
    }
}
