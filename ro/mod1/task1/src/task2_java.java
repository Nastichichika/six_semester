import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(100);

                    if(queue.offer("The car " + i, 100, TimeUnit.MILLISECONDS)) {
                        System.out.println("The car " + i + " arrived");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(400);
                    System.out.println(queue.take() + " left");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}