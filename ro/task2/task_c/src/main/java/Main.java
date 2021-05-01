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
        Monk max = new ForkJoinPool(4).invoke(new Fight(0, 9, monks));
        int count_In = 0;
        int count_Yn = 0;
        for(int i = 0; i < 10; i++) {
            if(i%2 == 0)
                count_Yn += monks.get(i).countOfVictory;
            else
                count_In += monks.get(i).countOfVictory;
        }

        if (count_In > count_Yn)
            System.out.println("Guan In winner");
        else
            System.out.println("Guan Yan winner");
    }
}
