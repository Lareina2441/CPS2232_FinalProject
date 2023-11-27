package CPS2232.FinalProject;

import java.util.Scanner;

public class menu {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // display basic function: choose borrow, buy or sell;
        int input1 = firstFunction();

        // Determine which module to enter based on the last input int
        if (input1 == 1) {
            borrowBoat();
        } else if (input1 == 2) {
            buyBoat();
        } else {
            returnBoat();
        }

        input.close();
    }

    public static int firstFunction() {
        System.out.println("Welcome to our company!");
        System.out.println("What is your request?");

        int input1;
        while (true) {
            System.out.println("1. borrow  2. buy  3. return ");
            try {
                input1 = input.nextInt();
                if (input1 == 1 || input1 == 2 || input1 == 3) {
                    break;
                }
                System.out.println("Please enter 1 or 2 or 3 ~");
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Please enter 1 or 2 or 3 ~");
            }
        }input.nextLine();
        return input1;
    }

    public static void borrowBoat() {
        System.out.println("Are there any requirements for the boat? ");
        System.out.println("You can select your boat's make, model, length, current docking area and year of production.");
        System.out.print("y/n : ");
        String input2;

        while (true) {
            try {
                input2 = input.nextLine();
                if (input2.equals("y") || input2.equals("n")) {
                    break;
                }
                System.out.println("Please enter y or n ~");
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Please enter y or n ~");
            }
        }

        if(input2.equals("n")){
            System.out.println("I will recommend any boat to you. The following is information about the boat:");

        }
    }

    public static void buyBoat() {
        System.out.println("Are there any requirements for the boat? ");
        System.out.println("You can select your boat's make, model, length, current docking area and year of production.");
        System.out.print("y/n : ");
        String input2;

        while (true) {
            try {
                input2 = input.nextLine();
                if (input2.equals("y") || input2.equals("n")) {
                    break;
                }
                System.out.println("Please enter y or n ~");
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Please enter y or n ~");
            }
        }
    }

    public static void returnBoat() {

    }
}
