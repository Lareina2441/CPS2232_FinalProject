package backend;

import person.Client;

import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;

public class Menu implements Serializable {
    private static Scanner scanner = new Scanner(System.in);
    // !!!!!!!!!!!!!!!!!! To read the database
    static RegistrationSystem system = new RegistrationSystem();

    static Database database = new Database();
    static Database baots = new Database(database.getBoats());

    public static void main(String[] args) throws ClassNotFoundException {

        //first, client log in their accounts
        System.out.println("Welcome to our company!");
        a:
        while (true) {
            System.out.println("You can choose 1. register  2. login  3. quit ");
            int log = scanner.nextInt();
            switch (log) {
                case 1:
                    // Use the register method in the RegistrationSystem class to register a new client;
                    system.registerUser();
                    system.saveUserDatabaseToFile();
                    break;
                case 2:
                    login();
                    break a;
                case 3:
                    system.saveUserDatabaseToFile();
                    System.out.println("Thank you for your visit!");
                    return;
            }
        }

    }


    public static void login() {
        // to log in;
        System.out.print("Please enter your username: ");
        String name = scanner.next();
        scanner.nextLine();
        Map<String, Client> userDatabase = system.loadUserDatabaseFromFile();
        // Check if the username exists
        if (!userDatabase.containsKey(name)) {
            System.out.println("Username not found. Please register first.");
            return;
        }
        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();
        scanner.nextLine();
        // Get the user info for the entered username
        Map<String, Client> userInfo = userDatabase;
        String storedPassword = userInfo.get(name).getPassword();

        // Check if the entered password matches the stored password
        if (enteredPassword.equals(storedPassword)) {
            System.out.println("Login successful!");
            System.out.println("----------------------------------------");
            // if login successful, then start to choose function
            // display basic function: choose borrow, buy or sell;
            int input1 = fiveRequest();
//            // Determine which module to enter based on the last input int
            switch (input1){
                case 1: borrowBoat();break;
                case 2: buyBoat();break;
                case 3: returnBoat();break;
                case 4: searchAppointment();break;
                case 5: displayLog();break;
                default:
                    System.out.println("valid input.");break;
            }

            //           scanner.close();
        } else {
            // If the passwords don't match, display an error message
            System.out.println("Invalid password. Please try again.");
        }
    }

    // display basic function: choose borrow, buy or sell;
    public static int fiveRequest() {
        int input1;
        while (true) {

            System.out.println("What is your request?");
            System.out.println("1. borrow  2. buy  3. return  4. search appointment  5. display log");
            try {
                input1 = scanner.nextInt();
                if (input1 == 1 || input1 == 2 || input1 == 3 || input1 == 4 || input1 == 5) {
                    break;
                }
                System.out.println("Please enter 1 or 2 or 3 or 4 or 5 ~");
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Please enter 1 or 2 or 3 or 4 or 5 ~");
            }
        }
        scanner.nextLine();
        return input1;
    }

    public static void borrowBoat() {
        recommendBoat();
    }

    public static void buyBoat() {
        recommendBoat();
    }

    public static void returnBoat() {

    }

    public static void searchAppointment(){

    }

    public static void displayLog(){

    }

    public static void recommendBoat(){
        System.out.println("Are there any requirements for the boat? ");
        System.out.println("You can select your boat's make, model, length, current docking area and year of production.");
        System.out.print("y/n : ");
        String input2;

        while (true) {
            try {
                input2 = scanner.nextLine();
                if (input2.equals("y") || input2.equals("n")) {
                    break;
                }
                System.out.println("Please enter y or n ~");
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Please enter y or n ~");
            }
        }
        if (input2.equals("y")){
            baots.show();
        }
        if (input2.equals("n")) {
            System.out.println("I will recommend any boat to you. The following is information about the boat:");

        }
    }
}
