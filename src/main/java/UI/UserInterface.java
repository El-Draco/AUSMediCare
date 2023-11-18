package UI;

import DatabaseManagement.UsersTableManager;
import UserManagement.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    protected User user;
    protected String menuType;


    UserInterface() throws SQLException {    }
    public void close() {}

    public void displayMainMenu() throws SQLException, NoSuchAlgorithmException {
        System.out.println("**********Welcome to the AUS MediCare Application!**********");

        String id = "";
        String password = "";

        Scanner in = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n\n");

            System.out.println("1. Login\n");
            System.out.println("2. Register\n");
            System.out.println("3. Access Emergency Services\n");
            System.out.println("4. Exit Application\n");

            choice = in.nextInt();
            switch (choice) {
                case 1 -> {
                    in.nextLine();
                    System.out.println("ID: ");
                    id = in.nextLine();
                    System.out.println("Password: ");
                    password = in.nextLine();
                    user = new ProxyUser().login(id, password);
                    if (user == null)
                        break;
                    setMenuType();
                }
                case 2 -> {
                    this.register();
                    System.out.println("User registered successfully. Please login to use the " +
                            "AUS Medicare Facilities!\n");
                }
                case 3 -> displayEmergencyServices();
                case 4 -> System.out.println("Exiting application...\n");
                default -> System.out.println("Invalid choice");
            }
        }while (choice != 4 && user == null);
    }

    public void displayMenu() throws SQLException {
        switch (menuType) {
            case "Student" -> {
                StudentUI studentMenu = new StudentUI();
                studentMenu.display();
            }
            case "HealthcareOfficial" -> {
                HealthCareOfficialUI healthcareMenu = new HealthCareOfficialUI();
                healthcareMenu.display();
            }
            case "Administration" -> {
                AdministrationUI adminMenu = new AdministrationUI();
                adminMenu.display();
            }
            default -> System.out.println("Invalid user type.");
        }
    }
    public void displayEmergencyServices(){
        System.out.println("Welcome to the Emergency Services Portal\n\nFor further help, please call 05439920023.\n");
        //@TODO: Implement
    }

    public String getMenuType(){
        return (menuType);
    }
    public void setMenuType(){
        if (user instanceof Student)
            menuType = "Student";
        else if (user instanceof Administration)
            menuType = "Administration";
        else
            menuType = "HealthcareOfficial";
    }

    public void register() throws NoSuchAlgorithmException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("*EXPERIMENTAL*\nWelcome to the Registration Portal\n\n");
        System.out.println("Please select the account type: \n");
        System.out.println("1. Student");
        System.out.println("2. HealthcareOfficial");
        System.out.println("3. Administration Staff");
        // check validity of account type
        int choice = 0;
        while (choice < 1 || choice > 3){
            choice = in.nextInt();
            if (choice < 1 || choice > 3)
                System.out.println("Invalid account type! Please try again.\n\n");
        }
        // retrieve inputs
        System.out.println("Please enter your name: ");
        String name = in.nextLine();
        System.out.println("Please enter your id: ");
        String id = in.next();
        System.out.println("Please enter your password: ");
        String password = in.next();
        // hash the password
        password = UsersTableManager.getMD5Hash(password);
        // add the user to the database
        UsersTableManager.getInstance().AddRecord(new ArrayList<String>(List.of(new String[]{
                "'" + name +"'",
                "'" + id + "'",
                "'" + password + "'"
        })));
    }
}
