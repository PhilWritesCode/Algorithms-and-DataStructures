/**
 * Created by Phil on 8/13/2015.
 */
public class ChainMap<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node next;

        private Node(Key k, Value v, Node next) {
            this.key = k;
            this.value = v;
            this.next = next;
        }
    }

    private int M;
    private Object[] array;

    public ChainMap(int size) {
        M = size/5;
        array = new Object[M];
    }

    private int hash(Key key) {
        return Math.abs(key.hashCode()%M);
    }

    public void add(Key key, Value value) {
        int index = hash(key);

        for(Node n = (Node)array[index]; n != null; n = n.next) {
            if(n.key.equals(key)) {
                n.value = value;
                return;
            }
        }
        array[index] = new Node(key, value, (Node)array[index]);
    }

    public Value get(Key key) {

        for(Node n = (Node)array[hash(key)]; n != null; n = n.next) {
            if(n.key.equals(key))
                return (Value) n.value;
        }

        return null;
    }

    public Value delete(Key key) {
        int index = hash(key);
        Value retVal;
        Node prev = null;
        for(Node n = (Node)array[index]; n != null; prev = n, n = n.next) {
            if(n.key.equals(key)) {
                retVal = (Value)n.value;
                if(prev == null)
                    array[index] = n.next;
                else
                    prev.next = n.next;
                return retVal;
            }
        }

        return null;
    }

    public void print() {
        for(int i = 0; i < M; i++) {
            System.out.print("[" + i + "]: ");
            for(Node n = (Node)array[i]; n != null; n = n.next) {
                System.out.print(n.key + ":" + n.value + "->");
            }
            System.out.println();
        }


    }

    public static void main(String[] args) {
        ChainMap<String, String> map = new ChainMap(20);

        map.add("one", "1");
        map.add("two", "2");
        map.add("three", "3");
        map.add("four", "4");
        map.add("five", "5");
        map.add("six", "6");
        map.add("seven", "7");
        map.add("eight", "8");
        map.add("nine", "9");
        map.add("ten", "10");
        map.add("eleven", "11");
        map.add("twelve", "12");
        map.add("thirteen", "13");
        map.add("fourteen", "14");
        map.add("fifteen", "15");
        map.add("sixteen", "16");
        map.add("seventeen", "17");
        map.add("eighteen", "18");
        map.add("nineteen", "19");
        map.add("twenty", "20");

        System.out.println("Printing...");
        map.print();

        System.out.println("Get ten: " + map.get("ten"));
        System.out.println("Get four: " + map.get("four"));
        System.out.println("Get twentyone: " + map.get("twentyone"));
        System.out.println("Get neg1: " + map.get("neg1"));
        System.out.println("Get twelve: " + map.get("twelve"));
        System.out.println("Get five: " + map.get("five"));
        System.out.println("Delete five...");
        map.delete("five");
        System.out.println("Get five: " + map.get("five"));

        System.out.println("Get nine: " + map.get("nine"));
        System.out.println("Delete nine...");
        map.delete("nine");
        System.out.println("Get nine: " + map.get("nine"));

        System.out.println("Printing...");
        map.print();

        System.out.println("Delete six...");
        map.delete("six");
        System.out.println("Delete three...");
        map.delete("three");
        System.out.println("Delete one...");
        map.delete("one");
        System.out.println("Delete four...");
        map.delete("four");

        System.out.println("Printing...");
        map.print();


    }
}
