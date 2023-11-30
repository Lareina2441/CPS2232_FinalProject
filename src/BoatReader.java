package calculator.A.FInal;
//  This file reads the data of the boats from a csv file
// and create a boat object according to each row of the table.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BoatReader {
    public static void main(String[] args) {
        // Map to store boats based on their make
        HashMap<String, HashMap<String, Boat>> boatMap = new HashMap<>();

        // File path of your CSV file
        String filePath = "D:/课本/CPS 2232/project/BoatsData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header if present
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Split CSV line into an array
                String[] data = line.split(",");

                // Extracting values from the CSV
                String make = data[1].trim();
                String variant = data[2].trim();
                double length = Double.parseDouble(data[3].trim());
                String country = data[5].trim();
                Double listingPrice = Double.parseDouble(data[6].trim());
                String yearString = data[7].trim().replaceAll("[^\\d]", ""); // Remove non-numeric characters
                int year = Integer.parseInt(yearString);


                // Creating a Boat object
                Boat boat = new Boat(make, variant, length, country, listingPrice, year);

                // Check if the make is already in the outer HashMap
                if (boatMap.containsKey(make)) {
                    // If yes, get the inner HashMap
                    HashMap<String, Boat> innerMap = boatMap.get(make);
                    // Put the Boat object into the inner HashMap with the variant as the key
                    innerMap.put(variant, boat);
                } else {
                    // If no, create a new inner HashMap
                    HashMap<String, Boat> innerMap = new HashMap<>();
                    // Put the Boat object into the inner HashMap with the variant as the key
                    innerMap.put(variant, boat);
                    // Put the inner HashMap into the outer HashMap with the make as the key
                    boatMap.put(make, innerMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Now you have all your Boat objects stored in the boatMap
        // You can retrieve a specific boat using its make and variant
        Boat specificBoat = boatMap.get("Alubat").get("Ovni 395");

        // Do whatever you need with the specific boat
        System.out.println(specificBoat);
    }
}
