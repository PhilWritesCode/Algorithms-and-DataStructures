/**
 * Created by Phil on 8/21/2015.
 */
public class Consumer implements Runnable {

    private BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5000; i++) {
            try {
                Thread.sleep((long) (Math.random() * 500));
                System.out.println("Withdrew: " + buffer.withdrawal());
            } catch( InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
