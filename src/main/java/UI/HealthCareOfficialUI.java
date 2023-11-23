package UI;

import UserManagement.HealthCareOfficial;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class HealthCareOfficialUI extends UserInterface{
    HealthCareOfficial healthCareOfficial;

    public HealthCareOfficial getHealthCareOfficial() {
        return healthCareOfficial;
    }

    public void setHealthCareOfficial(HealthCareOfficial healthCareOfficial) {
        this.healthCareOfficial = healthCareOfficial;
    }

    HealthCareOfficialUI(HealthCareOfficial user) throws SQLException {
        this.healthCareOfficial = user;
    }

    public void display() throws SQLException {
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
                    if (healthCareOfficial instanceof HealthCareOfficial) {
                        healthCareOfficial.accessStudentProfile();
                    } else {
                        System.out.println("Error! Invalid Permissions");
                        exit(0);
                    }
                    break;
                case 2:
                    // Manage Sick Leave Requests:
                   // healthCareOfficial.manageSickLeaveRequests();
                    break;
                case 3:
                    // Manage Referral Requests:
                   // healthCareOfficial.manageReferralRequests();
                    break;
                case 4:
                    // Manage Appointments
                   // healthCareOfficial.manageAppointments();
                    break;
                case 5:
                    // Manage Prescription Refill Requests
                    //healthCareOfficial.managePrescriptionRefillRequests();
                    break;
                case 6:
                    // Access Emergency Contact Information
                    displayEmergencyServices();
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
