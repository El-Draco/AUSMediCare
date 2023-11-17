package UI;

import AppointmentManagement.*;
import UserManagement.*;

import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    protected User user;
    protected String menuType;


    UserInterface() throws SQLException {
        user = new Student(new Account("b00087311","111111"),true,"",19,false,"9393939393","b00087311","b00087311@aus.edu");
        try {
            displayMainMenu();
        }
        catch(SQLException e){
            System.out.println("Exception: " + e.getMessage());
        }
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
                   // user = new ProxyUser();
                    /*if (!user.isLoggedIn())
                        break;
                    setMenuType();*/
                }
                case 2 -> {
                    User.register();
                    System.out.println("User registered succesfully. Please login to use the " +
                            "AUS Medicare Facilities!\n");
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
            menuType = "HealthCareOfficial";
    }
}
