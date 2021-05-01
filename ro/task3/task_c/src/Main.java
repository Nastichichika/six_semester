import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Table table  = new Table();

        Semaphore mediator_working = new Semaphore(1);
        Semaphore smoker_working = new Semaphore(1);

        Thread tobaccoSmoker = new Thread(new Smoker(0, smoker_working, table, mediator_working));
        Thread paperSmoker = new Thread(new Smoker(1, smoker_working, table, mediator_working));
        Thread matchesSmoker = new Thread(new Smoker(2, smoker_working, table, mediator_working));
        Thread mediator = new Thread(new Mediator(table, mediator_working, smoker_working));

        try {
            smoker_working.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mediator.start();

        tobaccoSmoker.start();
        paperSmoker.start();
        matchesSmoker.start();
    }
}
