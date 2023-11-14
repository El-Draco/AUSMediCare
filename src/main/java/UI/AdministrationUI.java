package UI;

import UserManagement.Administration;
import UserManagement.HealthCareOfficial;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class AdministrationUI extends UserInterface{
    AdministrationUI() throws SQLException {
    }

    public void display(){
        System.out.println("Welcome to the Administration Menu");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAdministration Menu:");
            System.out.println("1. View Student Profiles");
            System.out.println("2. Manage Requests");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Access Emergency Contact Information");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (user instanceof Administration) {
                        Administration administration = (Administration) user;
                        administration.accessStudentProfile();
                    } else {
                        System.out.println("Error! Invalid Permissions");
                        exit(0);
                    }
                    break;
                case 2:
                    // Implement Manage Requests - out of scope
                    break;
                case 3:
                    // Implement Manage Appointments - out of scope
                    break;
                case 4:
                    // Implement Access Emergency Contact Information
                    displayEmergencyServices();
                    break;
                case 5:
                    System.out.println("Exiting Administration Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 5);
    }
}
