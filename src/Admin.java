package calculator.A.FInal;

public class Admin {
    System.out.print("Are you a client or an administrator? ");System.out.println("1. client  2. administrator");
    int choice = input.nextInt();input.nextLine();

    switch(choice)
    {
            case 1:
                loginClient();
                return;
            case 2:
                loginAdmin();
                return;
            default:
                System.out.println("Invalid option. Please choose again.");
        }
}
