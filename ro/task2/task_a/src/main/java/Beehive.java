import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Beehive {
    AtomicBoolean bear = new AtomicBoolean(false);
    public Map<Integer, ArrayList<Integer>> forrest;

    int NUMBER_OF_THREAD = 4;
    public Beehive(Map<Integer, ArrayList<Integer>> forrest){
        this.forrest = forrest;
    }
    public void searchBear() {
        int number = forrest.size();
        int teams =  (number+(NUMBER_OF_THREAD-1))/NUMBER_OF_THREAD;
        Thread[] command = new Thread[number];
        int start;
        int end = 0;
        for(int i = 0; i < NUMBER_OF_THREAD; i++) {
            start = end;
            end = start + teams;
            if(end >= number) {
                end  = number;
            }
            command[i] = new Thread(new Operation(forrest, start, end, i + 1, bear));

        }
        for(int i = 0; i < NUMBER_OF_THREAD; i++) { command[i].start();
        }
    }
}
