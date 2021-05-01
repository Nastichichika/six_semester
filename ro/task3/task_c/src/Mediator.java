import java.util.Random;
import java.util.concurrent.Semaphore;

public class Mediator implements Runnable{
    private Table table;
    Semaphore mediator_working;
    Semaphore smoker_working;
    Random rand = new Random();

    Mediator(Table table, Semaphore mediator_working, Semaphore smoker_working) {
        this.table = table;
        this.mediator_working = mediator_working;
        this.smoker_working = smoker_working;
    }

    @Override
    public void run() {
        while (true) {

            try {
                mediator_working.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int x = Math.abs(rand.nextInt()) % 3;
            table.allTrue();
            switch (x) {
                case 0 -> {
                    System.out.println("put -  matches, paper");
                    table.setTobacco();
                }
                case 1 -> {
                    System.out.println("put -  tobacco, matches");
                    table.setPaper();
                }
                case 2 -> {
                    System.out.println("put -  tobacco, paper");
                    table.setMatches();
                }
            }
            smoker_working.release();
        }
    }
}