import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Monk> monks = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            monks.add(new Monk(random.nextInt(100),i%2));
        }
        Monk max = new ForkJoinPool().invoke(new Fight(0, 9, monks));
        int m = (6)/2;
        System.out.println(max.power + "|" + m);
        for(int i = 0; i < 10; i++) {
            System.out.println(i + " | " + monks.get(i).power);
        }
    }
}
