import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Fight extends RecursiveTask<Monk> {

    private final int start;
    private final int end;
    List<Monk> monks;

    public Fight(int start, int end, List<Monk> monks) {
        this.start = start;
        this.end = end;
        this.monks = monks;

    }
    @Override
    protected Monk compute() {
        if (end - start < 3) {
            //System.out.println(start + " " + end);
            //System.out.println(start + " " + monks.get(start).power);
            //System.out.println(maxPower(monks.get(start), monks.get(start+1)).power);
            return maxPower(monks.get(start), monks.get(start+1));
        }

        int middle = (start + end) / 2;

        Fight left = new Fight(start, middle, monks);
        Fight right = new Fight(middle+1, end, monks);

        left.fork();
        right.fork();
        Monk q = (Monk)right.join();
        Monk w = (Monk) left.join();

        System.out.println(q.power + " + " + w.power);
        return maxPower(q, w);
    }
    public Monk maxPower(Monk monk1, Monk monk2) {

        if(monk1.power > monk2.power) {
            synchronized (monk1){
                monk1.addVictory();
            }
            return monk1;
        }
        else{
            synchronized (monk2){
                monk1.addVictory();
            }
            return monk2;
        }
    }
}
