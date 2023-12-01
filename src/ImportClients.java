package calculator.A.FInal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportClients {
    public static void register(){
        String filePath = "E:\\SessionsAbout2023Fall\\CPS2232\\FinalProject\\Dataset\\clients.csv";
        String name = getInputName(filePath);
        System.out.print("Input your password: ");
        String password = input.next();
        System.out.print("Input your email: ");
        String email = input.next();
        String own = "";

        try {
            List<String[]> existingData = new ArrayList<>();
            File file = new File(filePath);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");
                        existingData.add(data);
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

                for (String[] data : existingData) {
                    writer.write(String.join(",", data));
                    writer.newLine();
                }

                writer.write(String.join(",", name, password, email, String.valueOf(own)));
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("register succesfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isNameExists(String name, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
        public static String getInputName(String filePath) {
        Scanner input = new Scanner(System.in);
        String name;

        while (true) {
            System.out.print("Input your name: ");
            name = input.next();

            if (!isNameExists(name, filePath)) {
                break;
            }

            System.out.println("Name already exists. Please enter a different name.");
        }

        return name;
    }

}
