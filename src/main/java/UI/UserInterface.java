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
            System.out.println("2. Access Emergency Services\n");
            System.out.println("3. Exit Application\n");

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
                        System.out.println("Invalid Username or Password, redirecting to main screen...");
                    else
                        setMenuType();
                }
                case 2 -> displayEmergencyServices();
                case 3 -> System.out.println("Exiting application...\n");
                default -> System.out.println("Invalid choice");
            }
        }while (choice != 3 && user == null);
    }

    public void displayMenu() throws SQLException {
        switch (menuType) {
            case "student" -> {
                StudentUI studentMenu = new StudentUI((Student)user);
                studentMenu.display();
            }
            case "healthcareofficial" -> {
                HealthCareOfficialUI healthcareMenu = new HealthCareOfficialUI((HealthCareOfficial) user);
                healthcareMenu.display();
            }
            case "administration" -> {
                AdministrationUI adminMenu = new AdministrationUI((Administration)user);
                adminMenu.display();
            }
            default -> System.out.println("Invalid user type.");
        }
    }
    public void displayEmergencyServices(){
<<<<<<< HEAD
        System.out.println("Welcome to the Emergency Services Portal\n\nFor further help, please call 0543920023.\n");
=======
        System.out.println("Welcome to the Emergency Services Portal\n\nFor further help, please call 05439920023.\n");
>>>>>>> 06693204a448b5b42d9a2f3eef7db6c81d9c3cef
        System.out.println("\n\nPlease wait for the next feature update. stay tuned folks...");
    }

    public String getMenuType(){
        return (menuType);
    }
    public void setMenuType(){
        if (user instanceof Student)
            menuType = "student";
        else if (user instanceof Administration)
            menuType = "administration";
        else
            menuType = "healthcareofficial";
    }
}
