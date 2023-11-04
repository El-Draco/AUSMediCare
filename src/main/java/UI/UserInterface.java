package UI;

import AppointmentManagement.*;
import UserManagement.*;

import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    protected User user;
    protected String menuType;


    UserInterface() throws SQLException {
        user = new User();
        displayMainMenu();
    }
    public void close() {}

    public void displayMainMenu() throws SQLException {
        System.out.println("**********Welcome to the AUS MediCare Application!**********");
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
                    user.login();
                    if (!user.isLoggedIn())
                        break;
                    menuType = user.getType();
                }
                case 2 -> {
                    user.register();
                    System.out.println("User registered succesfully. Login to access AUS MediCare services\n");
                }
                case 3 -> displayEmergencyServices();
                case 4 -> System.out.println("Exiting application...\n");
                default -> System.out.println("Invalid choice");
            }
        }while (choice != 3 && !user.isLoggedIn());
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
        System.out.println("Welcome to the Emergency Services Portal\n\n");
    }

    public String getMenuType(){
        return (menuType);
    }
}
