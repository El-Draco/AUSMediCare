package UserManagement;

import java.util.Scanner;

public class User {

    private Account account;
    private String type;
    private boolean loggedIn;
    public void login(){
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Login Portal\n\n");

        System.out.println("Please enter your username: ");
        String username = in.next();
        System.out.println("Please enter your password: ");
        String password = in.next();
        //@Shafiay: Validate credentials
    }
    public void register(){
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the Registration Portal\n\n");
        System.out.println("Please select the account type: \n");
        System.out.println("1. Student");
        System.out.println("2. HealthcareOfficial");
        System.out.println("3. Administration Staff");
        int choice = 0;
        while (choice < 1 || choice > 3){
            choice = in.nextInt();
            if (choice < 1 || choice > 3)
                System.out.println("Invalid account type! Please try again.\n\n");
        }
        System.out.println("Please enter your name: ");
        String name = in.nextLine();
        System.out.println("Please enter your username: ");
        String username = in.next();
        System.out.println("Please enter your password: ");
        String password = in.next();
        Account newAccount = new Account(username, password);
        //@Shafiay add account to database
    }
    public String getType() {
        return (type);
    }
    public boolean isLoggedIn(){
        return (loggedIn);
    }
}
