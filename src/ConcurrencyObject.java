import java.util.concurrent.Semaphore;

/**
 * Created by Phil on 8/20/2015.
 */
public class ConcurrencyObject {

    private static int value = 0;

    Semaphore mutex = new Semaphore(1);

    public  int getNextValue() throws InterruptedException{
        try {
            mutex.acquire();
            return value++;
        } finally {
            mutex.release();
        }
    }
}
