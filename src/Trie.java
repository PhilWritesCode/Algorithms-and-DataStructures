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

    public Node get(String key) {
        Node node = root;
        int keyIndex = 0;

        char nextKey = key.charAt(keyIndex++);
        while (keyIndex <= key.length() && node.children[nextKey] != null) {
            node = node.children[nextKey];
            if(keyIndex == key.length()) break;
            nextKey = key.charAt(keyIndex++);
        }

        if(keyIndex == key.length()) return node;

        return null;
    }

    public Iterable<String> startsWith(String prefix) {
        ArrayQueue<String> queue = new ArrayQueue();

        Node prefixNode = get(prefix);
        StringBuilder builder = new StringBuilder(prefix);

        gatherWords(builder, queue, prefixNode);

        return queue;
    }

    private void gatherWords(StringBuilder builder, ArrayQueue<String> queue, Node prefixNode) {

        if(prefixNode.value != -1) queue.enqueue(builder.toString());

        for(char c = 0; c < 256; c++) {
            if(prefixNode.children[c] != null) {
                builder.append(c);
                gatherWords(builder, queue, prefixNode.children[c]);
                builder.deleteCharAt(builder.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        Trie t = new Trie();

        System.out.println("Contains Anna? " + t.contains("Anna"));
        t.insert("Anna", 26);
        t.insert("Anne", 24);
        t.insert("Apple", 40);
        t.insert("Anagram", 91);
        t.insert("Anagranimals", 12);
        t.insert("George", 19);
        t.insert("Geoff", 32);
        System.out.println("Contains Anna? " + t.contains("Anna"));
        System.out.println("Anna: " + t.get("Anna").value);
        System.out.println("Contains Anne? " + t.contains("Anne"));
        System.out.println("Anne: " + t.get("Anne").value);
        t.insert("Anne", 31);
        System.out.println("Anne: " + t.get("Anne").value);
        System.out.println("Contains Ann? " + t.contains("Ann"));

        for(String word : t.startsWith("An")) {
            System.out.println("Starts with 'An': " + word);
        }
    }
}
