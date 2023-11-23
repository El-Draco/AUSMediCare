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

    StudentUI(Student user) throws SQLException {
            student = user;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void display() throws SQLException {
        System.out.println("Welcome to the Student Menu");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nStudent Menu:");
            System.out.println("1. Edit Medical History");
            System.out.println("2. Submit New Sick Leave Request");
            System.out.println("3. Check Sick Leave Status");
            System.out.println("4. Submit Referral Request");
            System.out.println("5. Check Referral Status");
            System.out.println("6. Schedule Appointment");
            System.out.println("7. Check Appointment Status");
            System.out.println("8. Request Prescription Refill");
            System.out.println("9. Check Prescription Refill Status");
            System.out.println("10. Emergency Services");
            System.out.println("11. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            currentDate = new Date();
            int reqId, reqStatus;
            int appointmentId, appointmentStatus;

            switch (choice) {
                case 1:
                    System.out.println("Feature not yet implemented! Please wait for the next feature update.");
                    break;
                case 2:
                    displaySubmitRequest("sickleave");
                    break;
                case 3:
                    displayCheckRequestStatus("sickleave");
                    break;
                case 4:
                    displaySubmitRequest("referral");
                    break;
                case 5:
                    displayCheckRequestStatus("referral");
                    break;
                case 6:
                    displayScheduleAppointment();
                    break;
                case 7:
                    displayCheckAppointmentStatus();
                    break;
                case 8:
                    displaySubmitRequest("refill");
                    break;
                case 9:
                    displayCheckRequestStatus("refill");
                    break;
                case 10:
                    displayEmergencyServices();
                    break;
                case 11:
                    System.out.println("Exiting Student Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 11);
    }

    private void displayScheduleAppointment() throws SQLException{
        System.out.println("**************Welcome to the Schedule Appointment Page*******************\n\n");
        Scanner scanner = new Scanner(System.in);
        Date apptDate = new Date();

        System.out.println("Is the appt. medical or therapy? (0:medical, 1:therapy)\n");
        scanner.nextInt();
        System.out.println("Is the appt. in-person or online? (0:in-person, 1:online)\n");
        scanner.nextInt();
        appointmentManager.scheduleAppointment(apptDate,0,student,doctor);
    }

    private void displayCheckAppointmentStatus() throws SQLException{
        System.out.println("**************Welcome to the Appointment Status Page*******************\n\n");

        //display all appointment ids:
        appointmentManager.getAppointments(getStudent().getAccount().getId());
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

    private void displayCheckRequestStatus(String requestType) throws SQLException {
        System.out.println("**************Welcome to the Check Request Status Page*******************\n\n");
        //display request ids here:
        requestManager.getRequests(getStudent().getAccount().getId());
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
                    requestManager.submitRequest(student.getAccount().getId(),currentDate,"",requestType,student.getEid());
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
