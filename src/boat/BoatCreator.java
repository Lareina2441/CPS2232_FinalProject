package boat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create a list of boats from a csv file and save it as binary object file.
 * you need to run this program first and once to create the binary file.
 * @version 1.0
 * @since 2023-12-17
 *
 */

public class BoatCreator implements Serializable {
    public static void main(String[] args) {
        String csvFile = "resources/2023_MCM_Problem_Y_Boats.csv";
        String line;
        String csvSplitBy = ",";
        List<Boat> boats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "");
                String[] data = line.split(csvSplitBy);
                String make = data[0];
                String variant = data[1];
                int length = Integer.parseInt(data[2]);
                String region = data[3];
                int year = Integer.parseInt(data[6]);
                String price = data[5].replaceAll("[^\\d.]", ""); // Remove non-numeric characters from price
                double costPrice = Double.parseDouble(price) * 100 ;
                double sellPrice = costPrice * (4-( 2030d - year )/10) ;
                double rentPrice = Double.parseDouble(price) ;
                // Create a new Boat object using the constructor
                Boat boat = new Boat(make, variant, length, region, costPrice, sellPrice, rentPrice, year,boats.size()-1);
                boats.add(boat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveBoatsToFile(boats);
        System.out.println("Number of boats created: " + boats.size());
        System.out.println(boats);
    }

    private static void saveBoatsToFile(List<Boat> boats) {
        String filePath = "resources/createdFiles/allboats";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(boats);
            System.out.println("Boats list has been saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

