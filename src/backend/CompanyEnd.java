package backend;


import boat.Boat;
import exceptions.NotFoundByGivenInfo;
import person.Client;
import person.Company;

import java.util.Map;
import java.util.Scanner;

import static backend.Database.allBoats;
import static backend.Database.searchBoat;

/**
 * Purpose: This class is used to provide the functions for the company end
 * @version 1.0
 * @since 2023-12-17
 */

public class CompanyEnd {
    Database database;
    Company company = new Company(1,"Seacraft 2018 Boat Company","cps");
    RegistrationSystem registrationSystem = new RegistrationSystem();
    Map<String, Client> userInfo =  registrationSystem.loadUserDatabaseFromFile();

    public CompanyEnd(Database database) {
        this.database = database;
    }

    public void ui(Scanner input) throws NotFoundByGivenInfo {
        System.out.println("Verify your identity:");
        System.out.println("Please choose the function you want to use:");
        System.out.println("1 Login");
        System.out.println("2 Exit");
        System.out.print("Your choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Please input the company unified password: ");
                String password = input.next();
                if (company.getPassword().equals(password)) {
                    System.out.println("Login successfully.");
                    this.menu(input);
                } else {
                    System.out.println("Wrong password. Please try again.");
                    ui(input);
                }
                break;
            case 2:
                System.out.println("Thank you for using the WKU Boat Company!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                ui(input);
        }
    }

    public void menu(Scanner input) throws NotFoundByGivenInfo {
        String s = null;
        System.out.println("Please choose the function you want to use:");
        System.out.println("1 Add a new boat");
        System.out.println("2 Delete a boat");
        System.out.println("3 Modify a boat");
        System.out.println("4 Search a boat");
        System.out.println("5 show user information");
        System.out.println("6 show boats information");
        System.out.print("Enter Your choice please: ");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Please input the information of the boat you want to add:");
                System.out.println("Please input the make of the boat:");
                String make = input.next();
                System.out.println("Please input the variant of the boat:");
                String variant = input.next();
                System.out.println("Please input the length of the boat:");
                int length = input.nextInt();
                System.out.println("Please input the region of the boat:");
                String region = input.next();
                System.out.println("Please input the year of the boat:");
                int year = input.nextInt();
                System.out.println("Please input the cost price of the boat:");
                double costPrice = input.nextDouble();
                System.out.println("Please input the sell price of the boat:");
                double sellPrice = input.nextDouble();
                System.out.println("Please input the rent price of the boat:");
                double rentPrice = input.nextDouble();
                Boat boat = new Boat(make, variant, length, region, costPrice, sellPrice, rentPrice, year, allBoats.size()-1);
                database.addBoat(boat);
                System.out.println("continue?\ny/n");
                s = input.next();
                if(s.equals("y")){
                    menu(input);
                }
                else{
                    System.out.println("Thank you for using the WKU Boat Company!");
                    System.exit(0);
                }

                break;
            case 2:
                database.deleteBoat(askBoatInfo(input,"delete"));
                System.out.println("continue?\ny/n");
                s = input.next();
                if(s.equals("y")){
                    menu(input);
                }
                else{
                    System.out.println("Thank you for using Company End System!");
                    System.exit(0);
                }
                break;
            case 3:
                database.modifyBoat(input,askBoatInfo(input,"modify"));
                System.out.println("continue?\ny/n");
                s = input.next();
                if(s.equals("y")){
                    menu(input);
                }
                else{
                    System.out.println("Thank you for using the Seacraft2018 Boat Company!");
                    System.exit(0);
                }
                break;
            case 4:try {
                searchBoat(input);

                System.out.println("continue?\ny/n");
                s = input.next();
                if(s.equals("y")){
                    menu(input);
                }
                else{
                    System.out.println("Thank you for using the Seacraft2018 Boat Company!");
                    System.exit(0);
                }
            }catch (NotFoundByGivenInfo e){
                System.out.println(e.getMessage());
            }
                break;
            case 5:
                System.out.println("user information:");
                userInfo.forEach((k,v)->{
                    System.out.printf("[name:%-10spassword:%-10semail:%-20s]\n",k,v.getPassword(),v.getEmail());
                });
                System.out.println("continue?\ny/n");
                 s=input.next();
                if(s.equals("y")){
                    menu(input);
                }
                else{
                    System.out.println("Thank you for using the Seacraft2018 Boat Company!");
                    System.exit(0);
                }
                break;
            case 6:
                database.show(input);
                System.out.println("continue?\ny/n");
                s = input.next();
                if(s.equals("y")){
                    menu(input);
                }
                else{
                    System.out.println("Thank you for using the Seacraft2018 Boat Company!");
                    System.exit(0);
                }
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                menu(input);
        }

    }

    /**
     * This method is used to ask the user to input the information of the boat.
     * @param input
     * @param action
     * @return Boat
     * @throws NotFoundByGivenInfo
     */
    public Boat askBoatInfo(Scanner input,String action) throws NotFoundByGivenInfo {
        System.out.println("Please input the information of the boat you want to"  + action +":");
        Boat boat = database.searchBoat(input);
        return boat;

    }




}
