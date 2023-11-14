package UI;


import UserManagement.HealthCareOfficial;
import UserManagement.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentUI extends UserInterface{
    Student student;
    StudentUI() throws SQLException {
        if (user instanceof Student)
            student = (Student) user;
    }

    public void display(){
        System.out.println("Welcome to the Student Menu");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nStudent Menu:");
            System.out.println("1. Edit Profile");
            System.out.println("2. Edit Medical History");
            System.out.println("3. Submit New Sick Leave Request");
            System.out.println("4. Check Sick Leave Status");
            System.out.println("5. Submit Referral Request");
            System.out.println("6. Check Referral Status");
            System.out.println("7. Schedule Appointment");
            System.out.println("8. Check Appointment Status");
            System.out.println("9. Request Prescription Refill");
            System.out.println("10. Check Prescription Refill Status");
            System.out.println("11. Emergency Services");
            System.out.println("12. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //Edit Profile
                    user.editProfile();
                    break;
                case 2:
                    //Edit Medical History
                    student.editMedicalHistory();
                    break;
                case 3:
                    //Submit New Sick Leave Request
                    student.submitRequest("Sick Leave");
                    break;
                case 4:
                    //Check Sick Leave Status
                    student.checkRequestStatus("Sick Leave");
                    break;
                case 5:
                    //Submit Referral Request
                    student.submitRequest("Referral");
                    break;
                case 6:
                    //Check Referral Status
                    student.checkRequestStatus("Referral");
                    break;
                case 7:
                    //Schedule Appointment
                    student.scheduleAppointment();
                    break;
                case 8:
                    //Check Appointment Status
                    student.checkAppointmentStatus();
                    break;
                case 9:
                    //Request Prescription Refill
                    student.submitRequest("Prescription Refill");
                    break;
                case 10:
                    //Check Prescription Refill Status
                    student.checkRequestStatus("Prescription Refill");
                    break;
                case 11:
                    //Display Emergency Services
                    displayEmergencyServices();
                    break;
                case 12:
                    System.out.println("Exiting Student Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 13);
    }
}
