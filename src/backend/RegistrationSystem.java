package backend;

import boat.Boat;
import person.Client;

import java.io.*;
import java.util.*;

public class RegistrationSystem implements Serializable {
    // This class describes the registration system of the WKU Boat Company.

    // Build a database to store the information of the clients.
    private String FILE_PATH1 = "resources/createdFiles/clients.csv";
    private String FILE_PATH2 = "resources/createdFiles/userData";
    private Map<String, Client> userDatabase = loadUserDatabaseFromFile();
    private Scanner input = new Scanner(System.in);
    private ArrayList<Boat> own = new ArrayList<>();

    public RegistrationSystem() {
    }

    public Map<String, Client> getUserDatabase() {
        return userDatabase;
    }

    private ArrayList<Boat> use = new ArrayList<>();

    public void saveUserDatabaseToFile(Map<String, Client> userDatabase) {
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
    public Map<String, Client> registerUser() {
        String name = getInputName();
        System.out.print("Input your password: ");
        String password = input.next();
        System.out.print("Input your email: ");
        String email = input.next();
        ArrayList transaction = null;
        Client client = new Client(name, password, email, new ArrayList<>(), new ArrayList<>());
        userDatabase.put(name, client);
        System.out.println("Register successfully.");

        // 将用户信息写入CSV文件
        writeUserToCSV(name, password, email);
        return userDatabase;
    }

    private void writeUserToCSV(String name, String password, String email) {
        try (FileWriter writer = new FileWriter(FILE_PATH1, true)) {
            writer.append(name).append(",").append(password).append(",").append(email).append("\n");
            System.out.println("User information has been written to CSV file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public ArrayList<Boat> getUse() {
        return use;
    }

    public void setUse(ArrayList<Boat> use) {
        this.use = use;
    }

    public ArrayList<Boat> getOwn() {
        return own;
    }

    public void setOwn(ArrayList<Boat> own) {
        this.own = own;
    }
}
