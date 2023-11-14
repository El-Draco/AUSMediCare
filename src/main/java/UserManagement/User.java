package UserManagement;

import DatabaseManagement.UsersTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private Account account;
    private boolean loggedIn;


    public User() {
    }
    public boolean login() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Login Portal\n\n");

        System.out.println("Please enter your username: ");
        String username = in.next();
        System.out.println("Please enter your password: ");
        String password = in.next();

        //@TODO: Validate credentials
        //retrieve and set 'account' variable here if login was succesfull
        boolean loginResult = UsersTableManager.getInstance().RecordExists(
                new ArrayList<String>(List.of(new String[]{"user_id = 'b00087311'"})));
        System.out.println(loginResult);
        return loginResult;
    }
    public static void register(){
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
        //@TODO: Add account to database
    }

    public void editProfile() {

    }
    public boolean isLoggedIn() {
        return (loggedIn);
    }

    public User(Account account, boolean loggedIn) {
        this.account = account;
        this.loggedIn = loggedIn;
    }

    public User(ResultSet resultSet, Account account) throws SQLException {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
