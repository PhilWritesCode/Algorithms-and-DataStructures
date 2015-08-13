/**
 * Created by Phil on 8/6/2015.
 */
public class LinkedListQueue<Thing> {

    private class Node {
        Thing data;
        Node next;

        public Node(Thing data) {
            this.data = data;
        }
    }

    Node head;
    Node tail;
    int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
    }

    public void enqueue(Thing item) {
       // System.out.println("adding " + item + "...");
        Node newNode = new Node(item);
        if(isEmpty()) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }
        tail = newNode;

        size++;
        //System.out.println("After inserting " + item + ": " + outputCurrentQueue());
    }

    public Thing dequeue() {
        //System.out.println("dequeueing...");
        if(isEmpty()) {
           // System.out.println("Cannot dequeue an empty queue!");
            return null;
        }
        Thing returnValue = head.data;
        head = head.next;
        if(head == null)
            tail = null;
        size--;
       // System.out.println("After dequeueing " + returnValue + ": " + outputCurrentQueue());
        return returnValue;
    }

    private String outputCurrentQueue() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");
        for (Node index = head; index != null; index = index.next) {
            builder.append(index.data);
            if(index.next != null)
                builder.append(",");
        }
        builder.append("]");
        builder.append("\n");
        builder.append("size: " + size + "\n");

        return builder.toString();
    }

    public Thing peek() {
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(10);
        queue.dequeue();
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);
        queue.enqueue(18);
        queue.enqueue(19);
        queue.enqueue(20);
        queue.enqueue(21);
        queue.enqueue(22);
        queue.enqueue(23);
        queue.enqueue(24);
        queue.enqueue(25);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }
}
