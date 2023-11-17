package UI;


import AppointmentManagement.AppointmentManager;
import RequestManagement.RequestManager;
import UserManagement.HealthCareOfficial;
import UserManagement.Student;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class StudentUI extends UserInterface{
    Student student;
    HealthCareOfficial doctor;
    RequestManager requestManager = new RequestManager();
    AppointmentManager appointmentManager = new AppointmentManager();

    Date currentDate;
    Date appointment_date;  //date for scheduling appointment

    //not sure if this is right but this is my logic for unique request ids
    private static int lastId = 0;

    public static int generateUniqueId(){
        return ++lastId;
    }

    public void statusUpdate(int status, int reqId){//1 = approved, 2 = declined, 3 = cancelled
        if(status == 1)
            System.out.println("Request (ID: " +reqId+" has been approved.");
        else if(status==2)
            System.out.println("Request (ID: " +reqId+" has been declined.");
        else if(status == 3)
            System.out.println("Request (ID: " +reqId+" has been cancelled.");
        else
            System.out.println("Request (ID: " +reqId+" has not been updated yet.");
    }

    StudentUI() throws SQLException {
        if (user instanceof Student)
            student = (Student) user;
    }

    public void display() throws SQLException {
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
            currentDate = new Date();
            int reqId, reqStatus;
            int appointmentId, appointmentStatus;

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
                    //student.submitRequest("Sick Leave");
                    requestManager.submitRequest(generateUniqueId(),student.getId(),currentDate,"","sickleave",student.getEid());
                    break;
                case 4:
                    //Check Sick Leave Status
                    //student.checkRequestStatus("Sick Leave");
                    System.out.print("Enter a request ID to check: ");
                    reqId = scanner.nextInt();
                    reqStatus=requestManager.checkRequestStatus(reqId,"sickleave");
                    statusUpdate(reqStatus,reqId);
                    break;
                case 5:
                    //Submit Referral Request
                    //student.submitRequest("Referral");
                    requestManager.submitRequest(generateUniqueId(),student.getId(),currentDate,"","referral",student.getEid());
                    break;
                case 6:
                    //Check Referral Status
                    //student.checkRequestStatus("Referral");
                    System.out.print("Enter a request ID to check: ");
                    reqId = scanner.nextInt();
                    reqStatus=requestManager.checkRequestStatus(reqId,"referral");
                    statusUpdate(reqStatus,reqId);
                    break;
                case 7:
                    //Schedule Appointment
                    appointmentStatus = 0;
                    appointmentManager.scheduleAppointment(generateUniqueId(),appointment_date,appointmentStatus,student,doctor);
                    break;
                case 8:
                    //Check Appointment Status
                    System.out.println("Enter an appointment ID to check: ");
                    appointmentId = scanner.nextInt();
                    appointmentStatus = appointmentManager.checkAppointmentStatus(appointmentId);
                    System.out.println("Appointment status is: " + appointmentStatus);
                    break;
                case 9:
                    //Request Prescription Refill
                    //student.submitRequest("Prescription Refill");
                    requestManager.submitRequest(generateUniqueId(),student.getId(),currentDate,"","refill",student.getEid());
                    break;
                case 10:
                    //Check Prescription Refill Status
                    //student.checkRequestStatus("Prescription Refill");
                    System.out.print("Enter a request ID to check: ");
                    reqId = scanner.nextInt();
                    reqStatus=requestManager.checkRequestStatus(reqId,"refill");
                    statusUpdate(reqStatus,reqId);
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
