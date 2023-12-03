package CPS2232.FinalProject;
import java.io.*;
import java.util.*;

public class RegistrationSystem implements Serializable {
    // This class describes the registration system of the WKU Boat Company.

    // Build a database to store the information of the clients.
    private static Map<String, Client> userDatabase = loadUserDatabaseFromFile();
    private static Scanner input = new Scanner(System.in);

    private static final String filePath1 = "E:\\SessionsAbout2023Fall\\CPS2232\\FinalProject\\Dataset\\clients.csv";
    private static final String filePath2 = "E:\\SessionsAbout2023Fall\\CPS2232\\FinalProject\\Dataset\\userData";

    // The "main" method of the registration system.
    public static void register() throws ClassNotFoundException {

        while (true) {
            System.out.println("Welcome to the WKU Boat Company registration system!");
            System.out.println("Register as a new user?");
            System.out.println("Type '1' to register");
            // The user can also exit the registration system.
            System.out.println("Type '2' to exit");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    // Save userDatabase to file
                    saveUserDatabaseToFile();
                    break;
                case 2:
                    System.out.println("Exiting the registration system.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    // The method to register a new user.
    public static void registerUser() {
        String name = getInputName(filePath1);
        System.out.print("Input your password: ");
        String password = input.next();
        System.out.print("Input your email: ");
        String email = input.next();
        Client client = new Client(name, password, email);
        userDatabase.put(name,client);
        System.out.println("Register successfully.");
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

    private static void saveUserDatabaseToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath2))) {
            oos.writeObject(userDatabase);
            System.out.println("User database has been saved ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Client> loadUserDatabaseFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath2))) {
            return (Map<String, Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }

    }
}
