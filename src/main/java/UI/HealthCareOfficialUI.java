package UI;

import UserManagement.HealthCareOfficial;
import UserManagement.Student;

import java.util.Scanner;

import static java.lang.System.exit;

public class HealthCareOfficialUI extends UserInterface{
    public void display(){
        System.out.println("Welcome to the Healthcare Official Menu");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nHealthcare Official Menu:");
            System.out.println("1. View Student Profiles");
            System.out.println("2. Manage Sick Leave Requests");
            System.out.println("3. Manage Referral Requests");
            System.out.println("4. Manage Appointments");
            System.out.println("5. Manage Prescription Refill Requests");
            System.out.println("6. Access Emergency Contact Information");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    if (user instanceof HealthCareOfficial) {
                        HealthCareOfficial healthcareOfficial = (HealthCareOfficial) user;
                        healthcareOfficial.accessStudentProfile();
                    } else {
                        System.out.println("Error! Invalid Permissions");
                        exit(0);
                    }
                    break;
                case 2:
                    // Implement logic for managing sick leave requests
                    break;
                case 3:
                    // Implement logic for managing referral requests
                    break;
                case 4:
                    // Implement logic for managing appointments
                    break;
                case 5:
                    // Implement logic for managing prescription refill requests
                    break;
                case 6:
                    // Implement logic for accessing emergency contact information
                    break;
                case 7:
                    System.out.println("Exiting Healthcare Official Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 7);
    }
}
