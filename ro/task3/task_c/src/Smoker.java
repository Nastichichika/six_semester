import java.util.concurrent.Semaphore;

public class Smoker implements Runnable{

    private final int item;
    private final Semaphore smoker_working;
    private final Table table;
    private final Semaphore mediator_working;

    public Smoker(int item, Semaphore smoker_working, Table table, Semaphore mediator_working) {
        this.item = item;
        this.smoker_working = smoker_working;
        this.table = table;
        this.mediator_working = mediator_working;
    }

    @Override
    public void run() {
        do {

            try {
                smoker_working.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (item) {
                case 0 -> {
                    if (!table.tobacco) {
                        System.out.println("tobacco smoker is smoking");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mediator_working.release();
                    } else smoker_working.release();
                }
                case 1 -> {
                    if (!table.paper) {
                        System.out.println("paper smoker is smoking");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mediator_working.release();
                    } else smoker_working.release();
                }
                case 2 -> {
                    if (!table.matches) {
                        System.out.println("matches smoker is smoking");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mediator_working.release();
                    } else smoker_working.release();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
