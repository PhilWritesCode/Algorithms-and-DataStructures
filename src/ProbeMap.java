/**
 * Created by Phil on 8/13/2015.
 */
public class ProbeMap<Key, Value> {

    private int M;
    private Key[] keys;
    private Value[] values;
    private int N;

    public ProbeMap(int size) {
        M = size*2;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return Math.abs(key.hashCode()%M);
    }

    public void add(Key key, Value value) {

        if(N == M/2) resize(M*2);
        int index;
        for (index = hash(key); keys[index] != null && !keys[index].equals("#"); index = (index+1)%M) {
            if(keys[index] == key) {
                values[index] = value;
                return;
            }
        }
        keys[index] = key;
        values[index] = value;
        N++;
    }

    public Value get(Key key) {

        for (int index = hash(key); keys[index] != null; index = (index+1)%M) {
            if(keys[index] == key) {
                return values[index];
            }
        }
        return null;
    }

    public Value delete(Key key) {

        Value retVal;
        for (int index = hash(key); keys[index] != null; index = (index+1)%M) {
            if(keys[index] == key) {
                retVal = values[index];
                keys[index] = (Key)"#";
                values[index] = null;
                N--;
                if(N == M/8) resize(M/2);
                return retVal;
            }
        }
        return null;
    }

    public void print() {
        System.out.print("Keys: [");
        for(int i = 0; i < M; i++) {
            System.out.print(keys[i]);
            if(i < M-1)
                System.out.print(",");
        }
        System.out.println();
        System.out.print("Values: [");
        for(int i = 0; i < M; i++) {
            System.out.print(values[i]);
            if(i < M-1)
                System.out.print(",");
        }
        System.out.println();

        System.out.println("N: " + N);
        System.out.println("M: " + M);
        System.out.println("M/8: " + M/8);
    }

    private void resize(int newSize) {
        M = newSize;
        Key[] newKeys = (Key[]) new Object[M];
        Value[] newValues = (Value[]) new Object[M];
        Key[] oldKeys = keys.clone();
        Value[] oldValues = values.clone();

        keys = newKeys;
        values = newValues;
        int storeN = N;
        for(int i = 0; i < oldKeys.length; i++) {
            if(oldKeys[i] != null && !oldKeys[i].equals("#")) {
                add(oldKeys[i], oldValues[i]);
            }
        }
        N = storeN;
    }

    public static void main(String[] args) {
        ProbeMap<String, String> map = new ProbeMap(20);

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

        System.out.print("add nine...");
        map.add("nine", "9");

        System.out.println("Printing...");
        map.print();

        map.add("thirty", "30");
        map.add("forty", "40");
        map.add("fifty", "50");
        map.add("sixty", "60");
        map.add("seventy", "70");
        map.add("eighty", "80");
        map.add("ninety", "90");
        map.add("onehundred", "100");

        System.out.println("Printing...");
        map.print();

        System.out.println("Get ten: " + map.get("ten"));
        System.out.println("Get four: " + map.get("four"));
        System.out.println("Get twentyone: " + map.get("twentyone"));
        System.out.println("Get neg1: " + map.get("neg1"));
        System.out.println("Get twelve: " + map.get("twelve"));
        System.out.println("Get thirty: " + map.get("thirty"));
        System.out.println("Get forty: " + map.get("forty"));
        System.out.println("Get fifty: " + map.get("fifty"));
        System.out.println("Get sixty: " + map.get("sixty"));
        System.out.println("Get seventy: " + map.get("seventy"));

        System.out.println("Delete six...");
        map.delete("six");
        System.out.println("Delete three...");
        map.delete("three");
        System.out.println("Delete one...");
        map.delete("one");
        System.out.println("Delete four...");
        map.delete("four");
        System.out.println("Delete thirty...");
        map.delete("thirty");
        System.out.println("Delete forty...");
        map.delete("forty");
        System.out.println("Delete fifty...");
        map.delete("fifty");
        System.out.println("Delete sixty...");
        map.delete("sixty");

        System.out.println("Delete two...");
        map.delete("two");
        System.out.println("Delete eight...");
        map.delete("eight");
        System.out.println("Delete nine...");
        map.delete("nine");
        System.out.println("Delete ten...");
        map.delete("ten");
        System.out.println("Delete eleven...");
        map.delete("eleven");
        System.out.println("Delete twelve...");
        map.delete("twelve");
        System.out.println("Delete thirteen...");
        map.delete("thirteen");
        System.out.println("Delete fourteen...");
        map.delete("fourteen");

        System.out.println("Delete fifreen...");
        map.delete("fifteen");
        System.out.println("Delete sixteen...");
        map.delete("sixteen");
        System.out.println("Delete seventeen...");
        map.delete("seventeen");
        System.out.println("Delete eighteen...");
        map.delete("eighteen");


        System.out.println("Printing...");
        map.print();
    }
}
