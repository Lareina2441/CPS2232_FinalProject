package backend;

import boat.Boat;
import person.Sailor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Database {
    HashMap<String,ArrayList<Boat>> byAttributeBoats = new HashMap<>();
    TreeMap<Integer,ArrayList<Boat>> rPrice = new TreeMap<>();
    TreeMap<Integer,ArrayList<Boat>> sPrice = new TreeMap<>();


    ArrayList<Sailor> sailors = new ArrayList<>();

    public static void main(String[] args) {
        Database database = new Database();

    }

    public Database(){
        Boat[] boats = new Boat[3];
        boats[0] = new Boat("Bav", "Cruiser 46", 14, "Mediterranean", 39999990, 200, 10, 20124);
        boats[1] = new Boat("Alamba", "Cruiser 56", 234, "Mediterraneaner", 30000, 2001000, 100400, 20214);
        boats[2] = new Boat("Bavaria", "Cruiser 46", 24, "Mediterraneean", 3000, 2000300, 100100, 20414);

        //use different attribute to file the database
        for (Boat boat : boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getMake(), k -> new ArrayList<>()).add(boat);//by MAKE
        }
        for (Boat boat : boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getRegion(), k -> new ArrayList<>()).add(boat);//by REGION
        }
        for (Boat boat : boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getVarient(), k -> new ArrayList<>()).add(boat);//by VARIANT
        }
        for (Boat boat : boats
        ) {
            rPrice.computeIfAbsent(boat.getPrice()[0], k -> new ArrayList<>()).add(boat);//by PRICE
            sPrice.computeIfAbsent(boat.getPrice()[1], k -> new ArrayList<>()).add(boat);//by PRICE
        }

    }
}
