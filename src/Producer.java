/**
 * Created by Phil on 8/21/2015.
 */
public class Producer implements Runnable {

    private BoundedBuffer buffer;

    public Producer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5000; i++) {
            try {
                Thread.sleep((long) (Math.random() * 500));
                buffer.deposit(i);
            } catch( InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
