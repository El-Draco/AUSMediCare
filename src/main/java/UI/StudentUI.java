package UI;


import java.util.Scanner;

public class StudentUI extends UserInterface{
    public void display(){
        System.out.println("Welcome to the Student Menu");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nStudent Menu:");
            System.out.println("1. Edit Profile");
            System.out.println("2. Edit Personal Info");
            System.out.println("3. Edit Medical History");
            System.out.println("4. Submit New Sick Leave Request");
            System.out.println("5. Check Sick Leave Status");
            System.out.println("6. Submit Referral Request");
            System.out.println("7. Check Referral Status");
            System.out.println("8. Schedule Appointment");
            System.out.println("9. Check Appointment Status");
            System.out.println("10. Request Prescription Refill");
            System.out.println("11. Check Prescription Refill Status");
            System.out.println("12. Emergency Services");
            System.out.println("13. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Implement Edit Profile
                    break;
                case 2:
                    // Implement Edit Personal Info
                    break;
                case 3:
                    // Implement Edit Medical History
                    break;
                case 4:
                    // Implement Submit New Sick Leave Request
                    break;
                case 5:
                    // Implement Check Sick Leave Status
                    break;
                case 6:
                    // Implement Submit Referral Request
                    break;
                case 7:
                    // Implement Check Referral Status
                    break;
                case 8:
                    // Implement Schedule Appointment
                    break;
                case 9:
                    // Implement Check Appointment Status
                    break;
                case 10:
                    // Implement Request Prescription Refill
                    break;
                case 11:
                    // Implement Check Prescription Refill Status
                    break;
                case 12:
                    // Implement Emergency Services
                    break;
                case 13:
                    System.out.println("Exiting Student Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 13);
    }
}
