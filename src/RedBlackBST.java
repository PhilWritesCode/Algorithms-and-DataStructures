import java.util.StringTokenizer;

/**
 * Created by Phil on 8/7/2015.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Tree {
        Key key;
        Value value;
        boolean color;
        Tree left;
        Tree right;

        private Tree(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }

        private Tree(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            this.color = color;
        }
    }

    Tree root;

    public RedBlackBST() {
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

    private boolean isRed(Tree node) {
        if(node == null)
            return false;
        return node.color == RED;
    }

    private Tree rotateLeft(Tree node) {
        assert(isRed(node.right));
        Tree temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = RED;

        return temp;
    }

    private Tree rotateRight(Tree node) {
        assert(isRed(node.left));
        Tree temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = RED;

        return temp;
    }

    private void flipColors(Tree node) {
        assert(!isRed(node));
        assert(isRed(node.left));
        assert(isRed(node.right));
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    private Tree put(Key key, Value value, Tree node) {
        if(node == null) {
            return new Tree(key, value);
        }
        int compare = key.compareTo(node.key);
        if(compare < 0) {
            node.left = put(key, value, node.left);
        }
        else if(compare > 0) {
            node.right = put(key, value, node.right);
        }
        else node.value = value;

        if(isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if(isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if(isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
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

        builder.append(node.key + ":" + node.value + ":" + node.color + " ");
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
        boolean color = Boolean.parseBoolean(parts[2]);
        Tree newNode = new Tree(key, value, color);
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
        RedBlackBST<Integer, String> tree = new RedBlackBST();

        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(4, "four");
        tree.put(5, "five");
        tree.put(6, "six");
        tree.put(7, "seven");
        tree.put(8, "eight");
        tree.put(9, "nine");
        tree.put(10, "ten");
        tree.put(11, "eleven");
        tree.put(12, "twelve");
        tree.put(13, "thirteen");
        tree.put(14, "fourteen");
        tree.put(15, "fifteen");


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

        String serialized = tree.serialize();
        System.out.println("Serialized: ");
        System.out.println(serialized);

        RedBlackBST<Integer, String> tree2 = new RedBlackBST();
        tree2.deserialize(serialized);

        System.out.println("Level order traversal...");
        tree2.levelOrderTraversal();

        System.out.println("Get: 2, 6, 4, 20: ");
        System.out.println(tree2.get(2));
        System.out.println(tree2.get(6));
        System.out.println(tree2.get(4));
        System.out.println(tree2.get(20));
    }
}
