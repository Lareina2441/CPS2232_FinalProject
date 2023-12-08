package backend;

import person.Client;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegistrationSystem implements Serializable {
    // This class describes the registration system of the WKU Boat Company.

    // Build a database to store the information of the clients.
    private String FILE_PATH1 = "E:\\SessionsAbout2023Fall\\CPS2232\\FinalProject\\Dataset\\clients.csv";
    private String FILE_PATH2 = "E:\\SessionsAbout2023Fall\\CPS2232\\FinalProject\\Dataset\\userData";
    private Map<String, Client> userDatabase = loadUserDatabaseFromFile();
    private Scanner input = new Scanner(System.in);

    public Map<String, Client> getUserDatabase() {
        return userDatabase;
    }

    public void saveUserDatabaseToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH2,false))) {
            oos.writeObject(userDatabase);
            System.out.println("User database has been saved ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Client> loadUserDatabaseFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH2))) {
            return (Map<String, Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException exception) {
            return new HashMap<>();
        }
    }

    // The method to register a new user.
    public void registerUser() {
        String name = getInputName();
        System.out.print("Input your password: ");
        String password = input.next();
        System.out.print("Input your email: ");
        String email = input.next();
        Client client = new Client(name, password, email);
        userDatabase.put(name,client);
        System.out.println("Register successfully.");
    }


    public String getInputName() {
        Scanner input = new Scanner(System.in);
        String name;

        while (true) {
            System.out.print("Input your name: ");
            name = input.next();

            if (!userDatabase.containsKey(name)) {
                break;
            }

            System.out.println("Name already exists. Please enter a different name.");
        }

        return name;
    }

//    private static void saveUserDatabaseToFile() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath2))) {
//            oos.writeObject(userDatabase);
//            System.out.println("User database has been saved ");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Map<String, Client> loadUserDatabaseFromFile() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath2))) {
//            return (Map<String, Client>) ois.readObject();
//        } catch (IOException | ClassNotFoundException | NullPointerException exception) {
//            return new HashMap<>();
//        }
//
//    }
}
