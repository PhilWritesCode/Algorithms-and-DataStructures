import java.util.Iterator;

/**
 * Created by Phil on 8/5/2015.
 */
public class ArrayQueue<Thing> implements Iterable<Thing> {

    Thing[] queue;
    int insertionIndex;
    int headIndex;
    int size;

    public ArrayQueue() {
        queue = (Thing[]) new Object[1];
    }

    public void enqueue(Thing item) {
      //  System.out.println("adding " + item + "...");
        if (size == queue.length)
            resizeArray(queue.length*2);
        queue[insertionIndex] = item;
        size++;
        insertionIndex = (insertionIndex+1)%queue.length;
      //  System.out.println("After inserting " + item + ": " + outputCurrentQueue());
    }

    private void resizeArray(int newSize) {
       // System.out.println("Resizing array from " + queue.length + " to " + newSize);
       // System.out.println("Old queue: " + outputCurrentQueue());
        Thing[] newQueue = (Thing[]) new Object[newSize];

        int newIndex = 0;
        for(int i = headIndex; newIndex < queue.length && queue[i] != null; i = (i+1)%queue.length) {
            newQueue[newIndex++] = queue[i];
        }

        queue = newQueue;
        headIndex = 0;
        insertionIndex = newIndex;
      //  System.out.println("New queue: " + outputCurrentQueue());
    }

    public Thing dequeue() {
      //  System.out.println("dequeueing...");
        if(isEmpty()) {
     //       System.out.println("Cannot dequeue an empty queue!");
            return null;
        }
        Thing returnValue = queue[headIndex];
        queue[headIndex] = null;
        size--;
        headIndex = (headIndex+1)%queue.length;
        if (queue.length > 1 && size == queue.length/4)
            resizeArray(queue.length/2);

     //   System.out.println("After dequeueing " + returnValue + ": " + outputCurrentQueue());
        return returnValue;
    }

    private String outputCurrentQueue() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");
        for (int i = 0; i < queue.length; i++) {
            builder.append(queue[i]);
            if (i < queue.length - 1)
                builder.append(",");
        }
        builder.append("]");
        builder.append("\n");
        builder.append("size: " + size + "\n");
        builder.append("headIndex: " + headIndex + "\n");
        builder.append("insertionIndex: " + insertionIndex + "\n");

        return builder.toString();
    }

    public Thing peek() {
        return queue[headIndex];
    }

    public boolean isEmpty() {
        return queue[headIndex] == null;
    }

    public Iterator<Thing> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Thing> {

        Thing[] things = queue.clone();
        int index = headIndex;
        int count = 0;

        public boolean hasNext() {
            return count < size;
        }
        public Thing next() {
            Thing item = things[index];
            count++;
            index = (index+1)%things.length;

            return item;

        }

        public void remove() {/*nada */}
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(4);
        arrayQueue.enqueue(5);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(7);
        arrayQueue.enqueue(8);
        arrayQueue.enqueue(9);
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.enqueue(10);
        arrayQueue.dequeue();
        arrayQueue.enqueue(11);
        arrayQueue.enqueue(12);
        arrayQueue.enqueue(13);
        arrayQueue.enqueue(14);
        arrayQueue.enqueue(15);
        arrayQueue.enqueue(16);
        arrayQueue.enqueue(17);
        arrayQueue.enqueue(18);
        arrayQueue.enqueue(19);
        arrayQueue.enqueue(20);
        arrayQueue.enqueue(21);
        arrayQueue.enqueue(22);
        arrayQueue.enqueue(23);
        arrayQueue.enqueue(24);
        arrayQueue.enqueue(25);
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
    }
}
