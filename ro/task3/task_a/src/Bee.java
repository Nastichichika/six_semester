import java.util.concurrent.atomic.AtomicInteger;

public class Bee implements Runnable{
    private AtomicInteger semafore;

    public Bee(AtomicInteger semafore) {
        this.semafore = semafore;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (semafore) {
                semafore.set(semafore.intValue() + 1);
                System.out.println("the bee brought honey " + semafore.intValue());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
