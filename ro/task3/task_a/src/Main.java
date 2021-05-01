import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static int num_honey = 9;
    private static AtomicInteger semafore = new AtomicInteger(0);
    private static int number_bee = 3;
    public static void main(String[] args) {
        Thread[] command = new Thread[number_bee];
        Thread bear = new Thread(new Bear(semafore, num_honey));
        bear.start();
        for(int i = 0; i < number_bee; i++) {
            command[i] = new Thread(new Bee(semafore));
        }
        for(int i = 0; i < number_bee; i++) {
            command[i].start();
        }
    }
}
