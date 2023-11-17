package UserManagement;

import DatabaseManagement.StudentsTableManager;
import DatabaseManagement.UsersTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class User {
    private Account account;
    private boolean loggedIn;
    private String name;
    private String email;
    private ResultSet resultSet;

    public User(ResultSet resultSet,Account account ,String name,String email) {
        this.account = account;
        this.resultSet = resultSet;
    }

    public User(ResultSet resultSet,Account account) {
        this.account = account;
        this.resultSet = resultSet;
    }

    public User(ResultSet resultSet, Account account, boolean loggedIn, String name,  String email) {
        this.account = account;
        this.loggedIn = loggedIn;
        this.name = name;
        this.email = email;
        this.resultSet = resultSet;
    }

    public User(Account account, boolean loggedIn, String name, String email) throws SQLException {
        this.account = account;
        this.loggedIn = loggedIn;
        this.name = name;
        this.email = email;

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


    public boolean isLoggedIn() {
        return (loggedIn);
    }

    public User(Account account, boolean loggedIn) {
        this.account = account;
        this.loggedIn = loggedIn;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void updateCredentials(User user) throws SQLException {
        StudentsTableManager.getInstance().UpdateRecords(
                new ArrayList<>(List.of(new String[]{"username = '" + user.getAccount().getUsername() +"'"
                        ,"password = '" + user.getAccount().getPassword() +"'"})),
                new ArrayList<String>(List.of(new String[]{"id = '"
                        + user.getAccount().getId()+"'"})));
    };



    public abstract void updateInfo(User user) throws SQLException;

}

