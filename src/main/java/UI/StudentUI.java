package UI;


import AppointmentManagement.AppointmentManager;
import RequestManagement.Request;
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
//                    student.editMedicalHistory();
                    break;
                case 3:
                    //Submit New Sick Leave Request
                    //student.submitRequest("Sick Leave");
                    requestManager.submitRequest(generateUniqueId(),student.getId(),currentDate,"","sickleave",student.getEid());
                    break;
                case 4:
                    //Check Sick Leave Status
                    //student.checkRequestStatus("Sick Leave");
                    displayCheckRequestStatus("sickleave");

                    break;
                case 5:
                    //Submit Referral Request
                    displaySubmitRequest("referral");
                    break;
                case 6:
                    //Check Referral Status
                    displayCheckRequestStatus("referral");
                    break;
                case 7:
                    //Schedule Appointment
                    appointmentStatus = 0;
                    displayScheduleAppontment();
                    break;
                case 8:
                    //Check Appointment Status
                    displayCheckAppointmentStatus();
                    break;
                case 9:
                    //Request Prescription Refill
                    displaySubmitRequest("refill");
                    break;
                case 10:
                    //Check Prescription Refill Status
                    displayCheckRequestStatus("refill");
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

    private void displayScheduleAppontment() throws SQLException{
        System.out.println("**************Welcome to the Schedule Appointment Page*******************\n\n");
        Scanner scanner = new Scanner(System.in);

//        appointmentManager.scheduleAppointment(generateUniqueId(),appointment_date,0,student,doctor);

        System.out.println("Is the appt. medical or therapy? (0:medical, 1:therapy)\n");
        scanner.nextInt();
        System.out.println("Is the appt. in-person or online? (0:in-person, 1:online)\n");
        scanner.nextInt();
        appointmentManager.scheduleAppointment(generateUniqueId(),appointment_date,0,student,doctor);

    }

    private void displayCheckAppointmentStatus() {
        System.out.println("**************Welcome to the Appointment Status Page*******************\n\n");

        //display all appointment ids:

        Scanner scanner = new Scanner(System.in);
        int appointmentId, appointmentStatus;
        do {
            System.out.println("Enter an appointment ID to check. Enter -1 to exit.\n");
            appointmentId = scanner.nextInt();
            if (appointmentId == -1)
                break;
            appointmentStatus = appointmentManager.checkAppointmentStatus(appointmentId);
            System.out.println("Appointment status is: " + appointmentStatus);
            //1: booked, 2: done, 3: cancelled
            if (appointmentStatus == 1)
                System.out.println("Appointment status: " + "Booked.");
            else if (appointmentStatus == 2)
                System.out.println("Appointment status: " + "Done & Completed.");
            else if (appointmentStatus == 3)
                System.out.println("Appointment status: " + "Cancelled.");
            else
                System.out.println("Appointment does not exist. Invalid.");
        } while (appointmentId != -1);
    }

    private void displayCheckRequestStatus(String requestType) {
        System.out.println("**************Welcome to the Check Request Status Page*******************\n\n");
        //display request ids here:

        Scanner scanner = new Scanner(System.in);
        int reqId, reqStatus;
        do {
            System.out.print("Enter a request ID to check. Enter -1 to exit.\n");
            reqId = scanner.nextInt();
            if (reqId == -1)
                break;
            reqStatus = requestManager.checkRequestStatus(reqId, requestType);
            statusUpdate(reqStatus,reqId);
        } while (reqId != -1);

    }

    private void displaySubmitRequest(String requestType) throws SQLException{
        System.out.println("**************Welcome to the Submit Request Page**********************\n\n");

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Please choose an option: \n");
            System.out.println("1. Submit a " + requestType + " Request.");
            System.out.println("2. Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    requestManager.submitRequest(generateUniqueId(),student.getId(),currentDate,"",requestType,student.getEid());
                    if (requestType == "sickleave")
                        System.out.println(" Note: Request is valid for only 7 days starting today." +
                                "If you need a longer period of absence excused please apply again after 5 days");
                    System.out.println("Submitting" + requestType + " request....\n");
                    System.out.println("Request submitted succesfully.\n");
                    break;
                case 2:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 2);
    }
}
