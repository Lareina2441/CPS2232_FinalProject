package backend;

import boat.Boat;
import person.Sailor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Database {
    //do operations based on the attribute
    HashMap<String,ArrayList<Boat>> byAttributeBoats = new HashMap<>();

    //use price as default "comparator to sort"
    TreeMap<Boat,Boat> byPriceBoats = new TreeMap<>();

    ArrayList<Sailor> sailors = new ArrayList<>();

    public static void main(String[] args) {
        Database database = new Database();

    }

    public Database(){
        //testing 
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
            price.put(boat,boat);//by PRICE
        }

    }
}
