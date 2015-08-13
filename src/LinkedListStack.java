/**
 * Created by Phil on 8/7/2015.
 */
public class LinkedListStack<Thing> {

    private class Node {
        Thing data;
        Node next;

        private Node(Thing thing) {
            this.data = thing;
            this.next = null;
        }
    }

    private Node head;
    int size;

    public LinkedListStack() {
        head = null;
    }

    public void push(Thing thing) {
        System.out.println("Pushing " + thing + "...");
        Node newHead = new Node(thing);
        newHead.next = head;
        head = newHead;
        size++;

        System.out.println("After pushing " + thing + ": ");
        System.out.println(outputCurrentStack());
    }

    public Thing pop() {
        if(isEmpty()) {
            System.out.println("Can't pop from empty stack!");
            return null;
        }

        Thing returnValue = head.data;
        head = head.next;
        size--;

        System.out.println("After popping " + returnValue + ": ");
        System.out.println(outputCurrentStack());

        return returnValue;
    }

    private String outputCurrentStack() {
        StringBuilder builder = new StringBuilder();

        Node index = head;

        while (index != null) {
            builder.append(index.data + "\n");
            index = index.next;
        }
        builder.append("\n");
        builder.append("size: " + size + "\n");

        return builder.toString();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();

        stack.pop();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.pop();
        stack.pop();
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.pop();
        stack.push(9);
        stack.push(10);
        stack.push(11);
        stack.push(12);
        stack.push(13);
        stack.push(14);
        stack.push(15);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.push(16);
        stack.push(17);
        stack.push(18);
        stack.push(19);
        stack.push(20);
        stack.push(21);
        stack.push(22);
        stack.push(23);
        stack.push(24);
        stack.push(25);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}
