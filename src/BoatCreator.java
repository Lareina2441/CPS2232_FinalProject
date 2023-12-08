import boat.Boat;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
  //Create boat array

public class BoatCreator implements Serializable {
    public static void main(String[] args) {
        String csvFile = "D:\\FinalProject\\CPS2232_FinalProject\\resources\\2023_MCM_Problem_Y_Boats.csv";
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
                String price = data[5].replaceAll("[^\\d.]", ""); // Remove non-numeric characters from price
                double costPrice = Double.parseDouble(price);
                int year = Integer.parseInt(data[6]);
                double sellPrice = costPrice * (30 + year * 20) / 30000;
                double rentPrice = sellPrice / 200.0;
                // Create a new Boat object using the constructor
                Boat boat = new Boat(make, variant, length, region, costPrice, sellPrice, rentPrice, year);
                boats.add(boat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveBoatsToFile(boats);
        System.out.println("Number of boats created: " + boats.size());
        System.out.println(boats.get(1000).getMake());
    }

    private static void saveBoatsToFile(List<Boat> boats) {
        String filePath = "D:\\FinalProject\\CPS2232_FinalProject\\resources\\2023_MCM_Problem_Y_Boats.csv";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(boats);
            System.out.println("Boats list has been saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

