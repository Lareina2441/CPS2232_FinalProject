package backend;

import boat.Boat;
import person.Client;

import javax.mail.MessagingException;
import java.io.*;
import java.util.*;

/**
 * This class describes the registration system of the Seacraft2018 Boat Company.
 * This class is used to register a new user.
 * the user information is stored in a binary file.
 * @version 1.0
 * @since 2023-12-17
 *
 */
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

    /**
     * This method is used to save the user database to a binary file.
     * @param userDatabase
     */
    public void saveUserDatabaseToFile(Map<String, Client> userDatabase) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH2,false))) {
            oos.writeObject(userDatabase);
            System.out.println("User database has been saved ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to load the user database from a binary file.
     * @return Map<String, Client>
     */
    public Map<String, Client> loadUserDatabaseFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH2))) {
            return (Map<String, Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException exception) {
            return new HashMap<>();
        }
    }

    /**
     * This method is used to register a new user.
     * @return Map<String, Client>
     * @throws MessagingException
     */
    public Map<String, Client> registerUser() throws MessagingException {
        String name = getInputName();
        System.out.print("Input your password: ");
        String password = input.next();

        password = storedPassword(password);
        //hash the password
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

    /**
     * This method is used to hide the password.
     * @param password
     * @return String
     */
    public static String storedPassword(String password) {
        int hash = 0;
        for (int i = 0; i < password.length(); i++) {
            password = password.replace(password.charAt(i), (char) (password.charAt(i)*password.charAt(password.length()-1-i) * 31));
            if (password.charAt(i) % 3 == 0) {
                password = password.replace(password.charAt(i), (char) (password.charAt(i) -31));
            }

        }
        return password;
    }

    /**
     * This method is used to write the user information to a CSV file.
     * @param name
     * @param password
     * @param email
     */
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
