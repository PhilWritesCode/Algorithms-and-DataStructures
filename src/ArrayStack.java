/**
 * Created by Phil on 8/7/2015.
 */
public class ArrayStack<Thing> {

    Thing stack[];
    int size;

    public ArrayStack() {
        stack = (Thing[])new Object[1];
    }

    public void push(Thing thing) {

        System.out.println("Pushing " + thing + "...");
        if(stack.length == size)
            resizeStack(stack.length*2);

        stack[size++] = thing;

        System.out.println("After pushing " + thing + ": ");
        System.out.println(outputCurrentStack());
    }

    private void resizeStack(int newSize) {
        System.out.println("resize!");
        Thing newStack[] = (Thing[])new Object[newSize];
        for(int i = 0; i < stack.length && stack[i] != null; i++)
            newStack[i] = stack[i];

        stack = newStack;
    }

    public Thing pop() {
        if(size == 0) {
            System.out.println("Stack is empty!");
            return null;
        }

        Thing returnValue = stack[--size];
        stack[size] = null;

        if(stack.length > 1 && size == stack.length/4)
            resizeStack(stack.length / 2);

        System.out.println("After popping " + returnValue + ": ");
        System.out.println(outputCurrentStack());

        return returnValue;
    }

    private String outputCurrentStack() {
        StringBuilder builder = new StringBuilder();

        for (int i = stack.length-1; i >= 0; i--) {
            builder.append(stack[i] + "\n");
        }
        builder.append("\n");
        builder.append("size: " + size + "\n");

        return builder.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();

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
