import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String city1 = "Kherson", city2 = "Kiev";

        ArrayList<String> cities = new ArrayList<>();
        cities.add("Kharkiv");
        cities.add("Lviv");
        cities.add("London");
        cities.add("New York");
        Graph map = new Graph(cities);
        Program wt1 = new Program(map);
        wt1.ChangePrice(city1, city2);
        wt1.findRoute(city1, city2);
        wt1.deleteCity();
        wt1.addCity(city2);
        wt1.deleteRoute(city1, city2);
    }
}
