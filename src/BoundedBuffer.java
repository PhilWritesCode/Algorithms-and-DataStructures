import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Phil on 8/21/2015.
 */
public class BoundedBuffer {

    private int[] buffer;
    private  int insertionPoint;
    private  int removalPoint;
    private int size;

    private final Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private int capacity;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        buffer = new int[capacity];
        insertionPoint = 0;
        removalPoint = 0;
    }

    public void deposit(int i) throws InterruptedException {
        lock.lock();
        try {
            while(size == capacity) {
                System.out.println("full!  Waiting...");
                notFull.await();
            }
            System.out.println("depositing " + i + "...");
            buffer[insertionPoint] = i;
            insertionPoint = (insertionPoint + 1)%capacity;
            size++;
            notEmpty.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public int withdrawal() throws InterruptedException {
        lock.lock();
        try {

            while(size == 0) {
                System.out.println("empty!  Waiting...");
                notEmpty.await();
            }

            int retVal = buffer[removalPoint];
            removalPoint = (removalPoint + 1)%capacity;
            size--;
            notFull.signal();
            return retVal;
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(10);

        Thread producer = new Thread(new Producer(buffer));
        Thread consumer = new Thread(new Consumer(buffer));

        producer.start();
        consumer.start();
    }
}
