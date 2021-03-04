import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Bear implements Runnable{
    private AtomicInteger semafore;
    private int num_honey;
    public Bear() {
    }
    public Bear(AtomicInteger semafore, int num_honey) {
        this.semafore = semafore;
        this.num_honey = num_honey;
    }
    @Override
    public void run() {
        while(true) {
            if (semafore.compareAndSet(num_honey, 0)) {
                System.out.println("the bear drank the honey");
            }
        }
    }
}
