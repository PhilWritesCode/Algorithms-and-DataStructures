import java.util.StringTokenizer;

/**
 * Created by Phil on 8/7/2015.
 */
public class BST<Key extends Comparable<Key>, Value> {

    private class Tree {
        Key key;
        Value value;
        Tree left;
        Tree right;

        private Tree(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    Tree root;

    public BST() {
        root = null;
    }

    public Value get(Key thing) {
        Tree current = root;

        while(current != null) {
            int compare = thing.compareTo(current.key);
            if(compare < 0) current = current.left;
            else if(compare > 0) current = current.right;
            else return current.value;
        }

        return null;
    }

    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    private Tree put(Key key, Value value, Tree node) {
        if(node == null) {
            return new Tree(key, value);
        }
        int compare = key.compareTo(node.key);
        if(compare < 0) node.left = put(key, value, node.left);
        else if(compare > 0) node.right = put(key, value, node.right);
        else node.value = value;

        return node;
    }

    public void delete(Key key) {
        root = delete(key, root);
    }

    private Tree delete(Key key, Tree node) {
        if(node == null)
            return null;

        int compare = key.compareTo(node.key);
        if(compare < 0) node.left = delete(key, node.left);
        else if(compare > 0) node.right = delete(key, node.right);
        else {
            Tree tempNode = node;
            node = getMin(tempNode.right);
            node.right = deleteMin(tempNode.right);
            node.left = tempNode.left;
        }

        return node;
    }

    private Tree getMin(Tree node) {
        if(node == null) {
            System.out.println("Error!  Trying to get min of null tree!");
            return null;
        }

        while(node.left != null)
            node = node.left;

        return node;
    }

    private Tree deleteMin(Tree node) {
        if(node.left == null)
            return node.right;

        node.left = deleteMin(node.left);

        return node;
    }

    public Tree floor(Key key) {
        return floor(key, root);
    }

    private Tree floor(Key key, Tree node) {
        if(node == null) return null;
        int compare = key.compareTo(node.key);
        if(compare == 0) return node;
        else if(compare < 0) return floor(key, node.left);
        else {
            Tree possibleFloor = floor(key, node.right);
            if(possibleFloor == null)
                return node;
            else
                return possibleFloor;
        }
    }

    public Tree ceiling(Key key) {
        return ceiling(key, root);
    }

    private Tree ceiling(Key key, Tree node) {
        if(node == null) return null;
        int compare = key.compareTo(node.key);
        if(compare == 0) return node;
        else if(compare > 0) return ceiling(key, node.right);
        else {
            Tree possibleCeiling = ceiling(key, node.left);
            if(possibleCeiling == null)
                return node;
            else
                return possibleCeiling;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Iterable<Key> keys() {
        ArrayQueue<Key> queue = new ArrayQueue();

        inOrder(root, queue);

        return queue;
    }



    public String serialize() {

        StringBuilder builder = new StringBuilder();

        serialize(root, builder);

        return builder.toString();
    }

    private void serialize(Tree node, StringBuilder builder) {
        if(node == null) {
            builder.append("# ");
            return;
        }

        builder.append(node.key + ":" + node.value + " ");
        serialize(node.left, builder);
        serialize(node.right, builder);
    }

    public void deserialize(String serializedTree) {
        StringTokenizer tokenizer = new StringTokenizer(serializedTree);
        root = null;
        if(tokenizer.hasMoreTokens()) {
            root = deserialize(root,  tokenizer);
        }
    }

    private Tree deserialize(Tree node, StringTokenizer tokenizer) {
        if(!tokenizer.hasMoreTokens())
            return null;

        String token = tokenizer.nextToken();

        if(token.equals("#")) {
            return null;
        }

        String[] parts = token.split(":");
        Key key = (Key)(Object) Integer.parseInt(parts[0]);
        Value value = (Value)(Object) parts[1];
        Tree newNode = new Tree(key, value);
        newNode.left = deserialize(newNode.left, tokenizer);
        newNode.right = deserialize(newNode.right, tokenizer);

        return newNode;

    }

    public void preOrder(Tree node, ArrayQueue queue) {

        if(node == null)
            return;
        queue.enqueue(node);
        preOrder(node.left, queue);
        preOrder(node.right, queue);
    }

    public void inOrder(Tree node, ArrayQueue queue) {
        if(node == null)
            return;

        inOrder(node.left, queue);
        queue.enqueue(node.key);
        inOrder(node.right, queue);
    }

    public void postOrder(Tree node, ArrayQueue queue) {
        if(node == null)
            return;

        postOrder(node.left, queue);
        postOrder(node.right, queue);
        queue.enqueue(node.key);

    }

    public void levelOrderTraversal() {
        Tree current = root;
        LinkedListQueue<Tree> queue = new LinkedListQueue<Tree>();

        queue.enqueue(current);
        while (!queue.isEmpty()) {
            current = queue.dequeue();
            System.out.println(current.value);
            if(current.left != null)
                queue.enqueue(current.left);
            if(current.right != null)
                queue.enqueue(current.right);
        }
    }

    public static void main(String[] args) {
        BST<Integer, String> tree = new BST();

        tree.put(10, "ten");
        tree.put(14, "fourteen");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(11, "eleven");
        tree.put(6, "six");
        tree.put(13, "thirteen");
        tree.put(20, "twenty");
        tree.put(1, "one");
        tree.put(8, "eight");
        tree.put(15, "fifteen");
        tree.put(5, "five");
        tree.put(9, "nine");
        tree.put(12, "twelve");

        System.out.println("Keys:");
        for(Integer k : tree.keys()) {
            System.out.println(k + ": " + tree.get(k));
        }

        System.out.println("Level order traversal:");
        tree.levelOrderTraversal();

        System.out.println("Get: 2, 6, 4, 20: ");
        System.out.println(tree.get(2));
        System.out.println(tree.get(6));
        System.out.println(tree.get(4));
        System.out.println(tree.get(20));

        System.out.println("floor 4:");
        System.out.println(tree.floor(4).value);

        System.out.println("ceiling 4:");
        System.out.println(tree.ceiling(4).value);

        System.out.println("deleting 4...");
        tree.delete(4);
        System.out.println("get 4:");
        System.out.println(tree.get(4));

        System.out.println("floor 4:");
        System.out.println(tree.floor(4).value);

        System.out.println("ceiling 4:");
        System.out.println(tree.ceiling(4).value);

        String serialized = tree.serialize();
        System.out.println("Serialized: ");
        System.out.println(serialized);

        BST<Integer, String> tree2 = new BST();
        tree2.deserialize(serialized);

        System.out.println("Level order traversal...");
        tree2.levelOrderTraversal();

        System.out.println("floor 4:");
        System.out.println(tree2.floor(4).value);

        System.out.println("ceiling 4:");
        System.out.println(tree2.ceiling(4).value);

        System.out.println("Get: 2, 6, 4, 20: ");
        System.out.println(tree2.get(2));
        System.out.println(tree2.get(6));
        System.out.println(tree2.get(4));
        System.out.println(tree2.get(20));
    }
}
