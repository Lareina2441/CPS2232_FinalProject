package backend;

import boat.Boat;
import exceptions.FailedTransactionException;
import exceptions.InvalidEmailAddress;
import exceptions.NotFoundByGivenInfo;
import person.Client;
import person.Company;
import javax.mail.MessagingException;
import java.io.*;
import java.time.LocalTime;
import java.util.*;

/**
 * Purpose: This class is used to provide the functions for the co
 * @version 1.0
 * @since 2023-12-17
 */


public class Menu implements Serializable {
    private static Scanner scanner = new Scanner(System.in);
    // !!!!!!!!!!!!!!!!!! To read the database

    //use static because we do not need to instantiate the class
    static RegistrationSystem system = new RegistrationSystem();
    static ArrayList<Boat> boats = loadBoatsFromFile();
    static Database database = new Database(boats);
    static CompanyEmailSender companyEmailSender;
    static Logger companyLogger;

    static {
        try {
            companyLogger = new Logger(new Company());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, NotFoundByGivenInfo, FailedTransactionException, InvalidEmailAddress {
        //Initializing
        System.out.println("We are sending your information to our server, please wait a moment...");
        LocalTime constructedTime = LocalTime.now();

        //sent construct information to company
        try {
            companyEmailSender = new CompanyEmailSender();
            ArrayList<Boat> boatsInfo = database.getBoats();
            String boatInfo = "";
            for (int i = 0; i < boatsInfo.size(); i++) {
                boatInfo += "Boat" + (i + 1) + " " + boatsInfo.get(i).toString() + "\n";
            }
            companyEmailSender.sent("Hello,\n  the system starts running at: \n\n\n" + getCurrentTime() +"\n" +
                    "Following are the boats we have, if you want to delete or modify a boat information (CANNOT RECALL!!)\n\n\n" +
                    " You can log in company end to make changes: \n" +boatInfo, new Company(0, "zhaoq@kean.edu", "2232"));
        } catch (MessagingException e) {
            companyLogger.writeExceptionErrorAndTransLog(e);
            e.printStackTrace();
        } catch (InvalidEmailAddress e) {
            System.out.println("Something badly happen in our server, email notification is not available ");
            ;
        }
        System.out.println("If you want to log in as a client, please enter 1, otherwise enter other key");
        if (!(scanner.nextInt() == 1)) {
            CompanyEnd companyEnd = new CompanyEnd(database);
            a:
            try {
                companyEnd.ui(scanner);
            }catch (NotFoundByGivenInfo e){
                companyLogger.writeExceptionErrorAndTransLog(e);
                System.out.println("Sorry, we cannot find the boat you want, please try again later");
                System.out.println("Continue? y/n");
                String s = scanner.next();
                if (s.equals("y")){
                    companyEnd.ui(scanner);
                }
                else {
                    System.out.println("Thank you for your visit!");
                    return;
                }
            }catch (InputMismatchException e){
                companyLogger.writeExceptionErrorAndTransLog(e);
                System.out.println("Wrong input, please enter 1 or 2 or 3");
                scanner.next();
                break a;
            }
        }

        //first, client log in their accounts
        System.out.println("Welcome to our company!");
        System.out.println("Time now: " + getCurrentTime());

        Boolean flag = true;
        a:
        do {

            System.out.println("You can choose 1. register  2. login  3. quit ");
            try{
                int log = scanner.nextInt();
                switch (log) {
                    case 1:
                         flag = true;
                        // Use the register method in the RegistrationSystem class to register a new client;
                        Map<String, Client> userDatabase = system.registerUser();
                        system.saveUserDatabaseToFile(userDatabase);
                        break;
                    case 2:
                        flag = true;
                        system.loadUserDatabaseFromFile();
                        login();
                        saveBoatsToFile(boats);
                        System.out.println("..................................");
                        break a;
                    case 3:
                        flag = true;
                        System.out.println("Thank you for your visit!");
                        flag = false;
                        return;

                }
                }catch (InputMismatchException e){
                companyLogger.writeExceptionErrorAndTransLog(e);
                    System.out.println("Wrong input, please enter 1 or 2 or 3");
                    scanner.next();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }while (flag);
    }

    public static String getCurrentTime() {
        return java.time.LocalDateTime.now().toString();
    }

    public static void login() throws exceptions.NotFoundByGivenInfo, FailedTransactionException, InvalidEmailAddress {

        // to log in;
        System.out.print("Please enter your user name:");
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

        // Get the user info for the entered username
        enteredPassword = RegistrationSystem.storedPassword(enteredPassword);

        String storedPassword = userDatabase.get(name).getPassword();
        // Check if the entered password matches the stored password
        while (true) {
            if (enteredPassword.equals(storedPassword)) {
                System.out.println("Login successfully!");
                System.out.println("We are sending your login information to your email, please wait a moment...");
                userDatabase.get(name).getLogger().writeLog("You logged in at " + getCurrentTime(),2);

                try {
                    if (companyEmailSender.isValidEmail(userDatabase.get(name).getEmail()) == -1) {
                        System.out.println("Invalid email address, so we cannot send email to you please register again");
                    }
                    Client client = userDatabase.get(name);
                    if (client.getUse() == null&&client.getOwn()==null){
                        companyEmailSender.sent("Someone is trying to log in your account at \n\n\n" + getCurrentTime(),
                                client );
                    } else if (client.getUse()!=null) {
                            companyEmailSender.sent("Someone is trying to log in your account at \n\n\n" +
                                            getCurrentTime() + "\nThe boats you have rent \n" +
                                            "are: " + client.getUse(),
                                    client );

                    }
                    else if (client.getOwn()!=null) {
                            companyEmailSender.sent("Someone is trying to log in your account at \n\n\n" +
                                            getCurrentTime()+"\n\n\nThe boats you have bought are: \n" +client.getOwn(),
                                    client );

                    }
                    else {
                    companyEmailSender.sent("Someone is trying to log in your account at \n\n\n" + getCurrentTime()
                                    +"The boats you have bought are: \n" +client.getOwn() + "\n\n\n" + "The boats you have rent \n" +
                                    "are: " + client.getUse(),
                            client );
                    }
                } catch (InvalidEmailAddress e) {
                    companyLogger.writeExceptionErrorAndTransLog(e);
                    userDatabase.get(name).getLogger().writeExceptionErrorAndTransLog(e);
                    System.out.println("Invalid email address, so we cannot send email to you please register again");
                }
                System.out.println("Login Information has sent to your email");
                System.out.println("----------------------------------------");

                // if login successful, then start to choose function
                fiveRequest(name);
                break;
                // Determine which module to enter based on the last input int
                //           scanner.close();
            } else {
                // If the passwords don't match, display an error message
                System.out.println("Invalid password. Please try again.");
                enteredPassword = scanner.nextLine();
            }
        }
    }

    // display basic function: choose borrow, buy or sell;
    public static void fiveRequest(String name) throws exceptions.NotFoundByGivenInfo, FailedTransactionException, InvalidEmailAddress {
        int input1;
        Client currentClient = system.loadUserDatabaseFromFile().get(name);
        Logger logger = currentClient.getLogger();
        a:while (true) {
            System.out.println("What is your request?");
            System.out.println("1. borrow  2. buy  3. return  4. display log  5. quit");

            try {
                input1 = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (input1) {
                    case 1:
                        borrowBoat(name);
                        break;
                    case 2:
                        buyBoat(name);
                        break;
                    case 3:
                        returnBoat(name);
                        break;
                    case 4:
                        displayLog(name);
                        break;
                    case 5:
                        break a; // Exit the loop and method
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                companyLogger.writeExceptionErrorAndTransLog(e);
                logger.writeExceptionErrorAndTransLog(e);
                System.out.println("Invalid input format. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                logger.writeExceptionErrorAndTransLog(e);
                // Handle other exceptions if needed
                e.printStackTrace();
            }
        }
    }


    public static void borrowBoat(String name) throws exceptions.NotFoundByGivenInfo, InvalidEmailAddress {

        Map<String, Client> userDatabase = system.loadUserDatabaseFromFile();
        Client currentClient = userDatabase.remove(name);
        Logger logger = currentClient.getLogger();
        try {
            Boat boat = recommendBoat();
            borrowTransaction(name, boat);
            logger.writeExceptionErrorAndTransLog(boat);
            companyLogger.writeExceptionErrorAndTransLog(boat);
            logger.writeLog("You borrow a " + boat.toString()+"It cost you "+boat.getRentPrice()+"$ ",1);

        }catch (FailedTransactionException e) {
            companyLogger.writeExceptionErrorAndTransLog(e);
            logger.writeExceptionErrorAndTransLog(e);
            companyEmailSender.sent("Sorry, the dealing is failed, please try again later\n\n"+e.getMessage(),currentClient);
        }
    }

    public static void buyBoat(String name) throws exceptions.NotFoundByGivenInfo, FailedTransactionException, InvalidEmailAddress {
        Map<String, Client> userDatabase = system.loadUserDatabaseFromFile();
        Client currentClient = userDatabase.remove(name);
        Logger logger = currentClient.getLogger();
        try {
            Boat boat = recommendBoat();
            buyTransaction(name, boat);
            logger.writeExceptionErrorAndTransLog(boat);
            companyLogger.writeExceptionErrorAndTransLog(boat);
            logger.writeLog("You bought a " + boat.toString()+"It cost you "+boat.getSellPrice()+"$ ",1);

        }catch (FailedTransactionException e) {
            companyLogger.writeExceptionErrorAndTransLog(e);
            logger.writeExceptionErrorAndTransLog(e);
            companyEmailSender.sent("Sorry, the dealing failed, please try again later\n\n"+e.getMessage(),currentClient);
        }
    }

    public static void returnBoat(String name) {
        Map<String, Client> userDatabase = system.loadUserDatabaseFromFile();
        Client currentClient = userDatabase.get(name);
        Logger logger = currentClient.getLogger();
        System.out.println("user:" + currentClient.getName());
        if (currentClient.getUse().isEmpty() || currentClient.getUse() == null) {
            System.out.println("no boat can return");
        } else {
            int i;
            for (i = 0; i < currentClient.getUse().size(); i++) {
                System.out.println("Boat" + (i+1) + " " + currentClient.getUse().get(i));
            }
            System.out.print("Which boat do you want to return? input No.x");
            int deleted = scanner.nextInt();
            Boat deletedBoat = currentClient.getUse().get(deleted-1);
            currentClient.getUse().remove(deletedBoat);
            deletedBoat.setUser(new Company());
            saveBoatsToFile(boats);
            companyLogger.writeLog("returned a " + deletedBoat.toString(),2);
            logger.writeLog("You returned a " + deletedBoat.toString(),2);
            system.saveUserDatabaseToFile(userDatabase);
        }
    }

    public static void displayLog(String name) throws InvalidEmailAddress {
        Map<String, Client> userDatabase = system.loadUserDatabaseFromFile();
        Client currentClient = userDatabase.get(name);
        System.out.println("user: " + currentClient.getName()+"\n We are sending log info to your email, please wait a moment...");
            currentClient.getLogger().sentLog();
        }


    public static Boat recommendBoat() throws exceptions.NotFoundByGivenInfo, FailedTransactionException {
        System.out.println("Are there any requirements for the boat? ");
        System.out.println("You can select your boat's make, model, length, current docking area and year of production.");
        System.out.print("y/n : ");
        String input2;
        Boat currentBoat = null;
        while (true) {
            try {
                input2 = scanner.next();
                if (input2.equals("y") || input2.equals("n")) {
                    if (input2.equals("y")){
                        scanner.nextLine();
                        database.show(scanner);
                        while (true){
                            try{
                                currentBoat = Database.searchBoat(scanner);
                                System.out.println("Right input.");
                                break;
                            }
                            catch (NotFoundByGivenInfo e){
                                System.out.println("Sorry, we cannot find the boat you want, please try again later");
                            }
                        }
                        //scanner.nextLine();
                    }
                    else {
                        int i = 0;
                        System.out.println("I will recommend any boat to you. The following is information about the boat:");
                        for (Map.Entry<Double, ArrayList<Boat>> entry : database.rPriceBoats.subMap(480000.0,500000.0).entrySet()) {
                            System.out.println("---> Price: " + entry.getKey());
                            for (Boat boat : entry.getValue()) {
                                i++;
                                System.out.println("boat " + i + " : " + boat);
                            }
                            System.out.println();
                        }
                        System.out.print("Choose one: ");
                        int count = scanner.nextInt();
                        int j = 0;

                        for (Map.Entry<Double, ArrayList<Boat>> entry : database.rPriceBoats.subMap(480000.0,500000.0).entrySet()) {
                            for (Boat boat : entry.getValue()) {
                                j++;
                                if(j == count)
                                    currentBoat = boat;
                            }
                        }
                    }
                    break;
                }
                System.out.println("Please enter y or n ~");
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Please enter y or n ~");
            }
        }

        return currentBoat;
    }
    public static void borrowTransaction(String name, Boat boat) throws FailedTransactionException {
        System.out.println("Dealing.....\nPlease Wait a moment");
        Map<String, Client> userDatabase = system.loadUserDatabaseFromFile();
        Client currentClient = userDatabase.get(name);
        if (!(boat.getOwner() instanceof Company))
            throw new FailedTransactionException("The boat is sold out");
        else if (boat.getUser() instanceof Client)
            throw new FailedTransactionException("The boat is rented");
        boat.setUser(currentClient);
        //Boat oldBoat=boats.get(boat.getIndex());
        currentClient.getUse().add(boat);
        System.out.println("Now, you can use: " + currentClient.getUse());
        userDatabase.put(name, currentClient);
        system.saveUserDatabaseToFile(userDatabase);
    }
    public static void buyTransaction(String name, Boat boat) throws FailedTransactionException {
        System.out.println("Dealing.....\nPlease Wait a moment");
        Map<String, Client> userDatabase = system.loadUserDatabaseFromFile();
        Client currentClient = userDatabase.get(name);
        if (!(boat.getOwner() instanceof Company))
            throw new FailedTransactionException("The boat is sold out");
        else if (boat.getUser() instanceof Client)
            throw new FailedTransactionException("The boat is rented");
        boat.setUser(currentClient);
        boat.setOwner(currentClient);
        //saveBoatsToFile(boats);
        ArrayList<Boat> set = currentClient.getOwn();
        set.add(boat);
        System.out.println("Now, you own : " + currentClient.getOwn());
        userDatabase.put(name, currentClient);
        system.saveUserDatabaseToFile(userDatabase);

        //currentClient.getTransaction().add(boat);
    }

    public static ArrayList<Boat> loadBoatsFromFile() {
        String filePath = "resources/createdFiles/allboats";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ArrayList<Boat>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveBoatsToFile(ArrayList<Boat> boats) {
        String filePath = "resources/createdFiles/allboats";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(boats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


