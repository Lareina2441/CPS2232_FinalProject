
package boat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoatCreator {
    public static void main(String[] args) {
        String csvFile = "resources/2023_MCM_Problem_Y_Boats.xlsx";
        String line;
        String csvSplitBy = "\t"; // Assuming it's tab-separated
        List<Boat> boats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                // Print the entire line to help identify the problem
                System.out.println("Line content: " + line);

                try {
                    String[] data = line.split(csvSplitBy);

                    if (data.length >= 7) { // Ensure there are enough elements in the array
                        String maker = data[0];
                        String variant = data[1];
                        int length = Integer.parseInt(data[2]);
                        String region = data[3];
                        String price = data[5].replaceAll("[^\\d.]", "");
                        int sellPrice = Integer.parseInt(price);
                        int year = Integer.parseInt(data[6]);
                        int costPrice = sellPrice - 100 - length * 2 - year / 10;
                        int rentPrice = sellPrice / 100;

                        // Create a new Boat object using the constructor
                        Boat boat = new Boat(maker, variant, length, region, sellPrice, costPrice, sellPrice, year);
                        boats.add(boat);
                    } else {
                        System.out.println("Insufficient data elements in the line: " + line);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing data: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number of boats created: " + boats.size());

        // Check if there are at least 1001 boats before accessing the 1000th index
        if (boats.size() > 1000) {
            System.out.println("Make of the 1000th boat: " + boats.get(1000).getMake());
        } else {
            System.out.println("Not enough boats in the list.");
        }
    }
}

