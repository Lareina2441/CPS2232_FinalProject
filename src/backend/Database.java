package backend;

import boat.Boat;
import boat.BoatCreator;
import person.Sailor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.TreeMap;

public class Database {
    HashMap<String,ArrayList<Boat>> byAttributeBoats = new HashMap<>();
    TreeMap<Integer,Boat> byPriceBoats = new TreeMap(Boat.getComparator());
    BoatCreator boatCreator = new BoatCreator();

    ArrayList<Sailor> sailors = new ArrayList<>();

    public static void main(String[] args) {
        Database database = new Database();
        System.out.println(database.byAttributeBoats.get("Bavaria"));
        System.out.println(database.byPriceBoats);
    }

    public Database(){
        boatCreator.createBoats();
        ArrayList <Boat> boats = new ArrayList<>();
        //use different attribute to file the database
        for (Boat boat : boatCreator.boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getMake(), k -> new ArrayList<>()).add(boat);//by MAKE
        }
        for (Boat boat : boatCreator.boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getRegion(), k -> new ArrayList<>()).add(boat);//by REGION
        }
        for (Boat boat : boatCreator.boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getVarient(), k -> new ArrayList<>()).add(boat);//by VARIANT
        }
        for (Boat boat : boatCreator.boats
        ) {
            byPriceBoats.put(boat.getSellPrice(),boat);
        }
    }
}