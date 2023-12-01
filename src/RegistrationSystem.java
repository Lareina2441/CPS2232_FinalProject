package calculator.A.FInal;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegistrationSystem {
    // This class describes the registration system of the WKU Boat Company.
    
    // Build a database to store the information of the clients.
    private static Map<String, Client> userDatabase = new HashMap<>();

    // The "main" method of the registration system.
    public static void register() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the WKU Boat Company registration system!");
            System.out.println("Register as new user?");
            System.out.println("Type '1' to register");
            // The user can also exit the registration system.
            System.out.println("Type '2' to exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    registerUser();
                    return;
                case 2:
                    System.out.println("Exiting the registration system.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
    // The method to register a new user.
    private static void registerUser() {
        // Ask the user to enter the username.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user name: ");
        String name = scanner.nextLine();

        // Check if the username already exists
        // !!better to write a loop here
        if (userDatabase.containsKey(name)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }
        else {
            System.out.println("This username is available.");

        }

        // Ask the user to enter the password and email.
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        // Create a new client according to the information that the user has entered.
        Client client = new Client(name, password, email);
        
        // Add the user to the database
        userDatabase.put(name, client);

        System.out.println("Registration successful!");
        System.out.println("Welcome, " + name);

        // !!better to export a csv file to store the information of the clients here
        
    }
    public Map<String, Client> getUserDatabase() {
        return userDatabase;
    }
}
