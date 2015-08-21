/**
 * Created by Phil on 8/20/2015.
 */
public class MyRunnable implements Runnable {

    ConcurrencyObject obj;
    public MyRunnable(ConcurrencyObject obj) {
        this.obj = obj;
    }

   

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
       // System.out.println("Thread " + Thread.currentThread().getName() + " started at " + startTime);


        try {
            System.out.println(Thread.currentThread().getName() + " value: " + obj.getNextValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

/*        try {
            Thread.sleep(5000);
        }catch(InterruptedException e){
            long interruptTime = System.currentTimeMillis();
            System.out.println("Thread " + Thread.currentThread().getName() + " interrupted at " + interruptTime);
            System.out.println("Thread " + Thread.currentThread().getName() + " difference of " + (interruptTime - startTime));

        }*/

       // long finishTime = System.currentTimeMillis();
        //System.out.println("Thread " + Thread.currentThread().getName() + " finished at " + finishTime);
        //System.out.println("Thread " + Thread.currentThread().getName() + " difference of " + (finishTime - startTime));
    }

    public static void main(String[] args) {

        Runnable runnable = new MyRunnable(new ConcurrencyObject());

        Thread thread1 = new Thread(runnable);
        thread1.setName("Thread 1");

        Thread thread2 = new Thread(runnable);
        thread2.setName("Thread 2");


        thread1.start();
/*        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("Shit!");
        }*/

        thread2.start();
/*        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
            System.out.println("Main thread interrupted?");
        }
       // thread2.interrupt();*/


        System.out.println("Main Thread!");
    }
}
