import java.util.Random;

public class Program{
    private CustomReadWriteLock lock = new CustomReadWriteLock();
    private Graph matrix;
    private int task;


    public Program(Graph matrix) {
        this.matrix = matrix;
        this.task = task;
    }

    public void ChangePrice(String city1,String  city2) {
        new Thread(() -> {
            Random rand = new Random();

            while (true) {
                int c = rand.nextInt() % 100;
                try {
                    lock.WriteLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                matrix.changeRouteCost(city1, city2, c);
                for (int j = 0; j < matrix.cities.size(); j++) {
                    System.out.print(matrix.cities.get(j) + " ");
                }
                System.out.println();
                for (int i = 0; i < matrix.cities.size(); i++) {
                    for (int j = 0; j < matrix.cities.size(); j++) {
                        System.out.print(matrix.routes.get(i).get(j) + " ");
                    }
                    System.out.println();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.WriteUnlock();
            }
        }).start();
    }

    public void findRoute(String city1,String  city2) {
        new Thread(() -> {
            Random rand = new Random();
            while (true) {

                try {
                    lock.ReadLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int c1 = Math.abs(rand.nextInt() % matrix.cities.size());
                int c2 = Math.abs(rand.nextInt() % matrix.cities.size());

                int cost = matrix.findRoute(matrix.cities.get(c1), matrix.cities.get(c2));
                System.out.println("Cost is " + cost);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.ReadUnlock();
            }
        }).start();
    }

    public void deleteCity() {
        new Thread(() -> {
            Random rand = new Random();
            while (true) {

                try {
                    lock.WriteLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int c1 = Math.abs(rand.nextInt() % matrix.cities.size());


                matrix.deleteCity(matrix.cities.get(c1));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.WriteUnlock();
            }
        }).start();
    }
    public void addCity(String city1) {
        new Thread(() -> {
            Random rand = new Random();
            while (true) {

                try {
                    lock.WriteLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                matrix.addCity(city1);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.WriteUnlock();
            }
        }).start();
    }
    public void deleteRoute(String city1, String  city2) {
        new Thread(() -> {
            while (true) {

                try {
                    lock.WriteLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                matrix.deleteRouteCost(city1, city2);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.WriteUnlock();
            }
        }).start();
    }
}
