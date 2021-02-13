import java.util.*;

public class Main {
    public static void main(String[] args) {
        int row = 10;
        int column = 10;
        Map<Integer, ArrayList<Integer>> forrest = new HashMap<Integer, ArrayList<Integer>>();

        for (int x = 0; x < row; x++) {
            forrest.put(x, new ArrayList<>());
        }
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                forrest.get(x).add(0);
            }
        }
        Random random = new Random();
        int bear_x = random.nextInt(row);
        int bear_y = random.nextInt(column);
        System.out.println(bear_x + " " + bear_y);
        forrest.get(bear_x).set(bear_y, 1);
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                System.out.print(forrest.get(x).get(y) + " ");
            }
            System.out.println();
        }
        Beehive bee = new Beehive(forrest);
        bee.searchBear();
    }
}
