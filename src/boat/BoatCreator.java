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
        String csvSplitBy = ",";
        List<Boat> boats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "");
                String[] data = line.split(csvSplitBy);
                String maker = data[0];
                String variant = data[1];
                int length = Integer.parseInt(data[2]);
                String region = data[3];
                String price = data[5].replaceAll("[^\\d.]", ""); // Remove non-numeric characters from price
                int sellPrice = Integer.parseInt(price);
                int year = Integer.parseInt(data[6]);
                int costPrice = sellPrice - 100 - length * 2 - year / 10;
                int rentPrice = sellPrice / 100;
                // Create a new Boat object using the constructor
                Boat boat = new Boat(maker, variant, length, region, sellPrice, costPrice,sellPrice, year);
                boats.add(boat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number of boats created: " + boats.size());
        System.out.println(boats.get(1000).getMake());
    }
}