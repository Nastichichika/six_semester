import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Operation implements Runnable{
    Map<Integer, ArrayList<Integer>> area;
    AtomicBoolean bear;
    int start;
    int end;
    int teem_number;

    public Operation(Map<Integer, ArrayList<Integer>> area, int start, int end, int teem_number,AtomicBoolean bear) {
        this.area = area;
        this.bear = bear;
        this.start = start;
        this.end = end;
        this.teem_number = teem_number;
    }
    @Override
    public void run() {

        for(int i = start; i < end; i++) {
            if (bear.compareAndSet(true, true)) {
                System.out.println("Bear caught:( teem " + teem_number);
                return;
            }
            int length = area.get(0).size();
            for (int j = 0; j < length; j++) {
                if (area.get(i).get(j) == 1) {
                    bear.set(true);
                    System.out.println("We found:) teem " + teem_number);
                    return;
                }
            }

            System.out.println("We didn't find :.( teem " + teem_number);
        }
    }
}
