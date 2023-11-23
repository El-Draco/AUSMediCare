package UI;


import AppointmentManagement.Appointment;
import AppointmentManagement.AppointmentManager;
import DatabaseManagement.HealthcareOfficialsTableManager;
import DatabaseManagement.UsersTableManager;
import RequestManagement.Request;
import RequestManagement.RequestManager;
import UserManagement.HealthCareOfficial;
import UserManagement.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class StudentUI extends UserInterface {
    Student student;
    HealthCareOfficial doctor;
    RequestManager requestManager = new RequestManager();
//    AppointmentManager appointmentManager = new AppointmentManager();

    Date currentDate;

    public void statusUpdate(int status, int reqId) {
        if (status == 1)
            System.out.println("Request (ID: " + reqId + " has been requested)");
        else if (status == 2)
            System.out.println("Request (ID: " + reqId + " has been approved)");
        else if (status == 3)
            System.out.println("Request (ID: " + reqId + " has been declined)");
        else if (status == 4)
            System.out.println("Request (ID: " + reqId + " has been canceled)");
        else
            System.out.println("Request (ID: " + reqId + " has not been updated yet)");
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
            System.out.println("1. View Appointments History");
            System.out.println("2. View Requests History");
            System.out.println("3. Check Request Status");
            System.out.println("4. Submit New Sick Leave Request");
            System.out.println("5. Submit Referral Request");
            System.out.println("6. Submit Prescription Refill Request");
            System.out.println("7. Schedule Appointment");
            System.out.println("8. Check Appointment Status");
            System.out.println("9. Emergency Services");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            currentDate = new Date();
            int reqId, reqStatus;
            int appointmentId, appointmentStatus;

            switch (choice) {
                case 1:
                    displayAppointmentsHistory();
                    break;
                case 2:
                    displayRequestsHistory();
                    break;
                case 3:
                    displayRequestsHistory();
                    displayCheckRequestStatus();
                    break;
                case 4:
                    displaySubmitRequest("sickleave");
                    break;
                case 5:
                    displaySubmitRequest("referral");
                    break;
                case 6:
                    displaySubmitRequest("refill");
                    break;
                case 7:
                    displayScheduleAppointment();
                    break;
                case 8:
                    displayAppointmentsHistory();
                    displayCheckAppointmentStatus();
                    break;
                case 9:
                    displayEmergencyServices();
                    break;
                case 10:
                    System.out.println("Exiting Student Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 10);
    }

    private void displayScheduleAppointment() throws SQLException {
        System.out.println("**************Welcome to the Schedule Appointment Page*******************\n\n");
        Scanner scanner = new Scanner(System.in);
        Date apptDate = new Date();

        System.out.println("Is the appt. medical or therapy? (0:medical, 1:therapy)");
        int type;
        do {
            type = scanner.nextInt();
            if (type != 1 && type != 0) {
                System.out.println("invalid option, enter 0 for medical or 1 for therapy:");
            }
        } while (type != 1 && type != 0);

        System.out.println("\nIs the appt. in-person or online? (0:in-person, 1:online)");
        int mode;
        do {
            mode = scanner.nextInt();
            if (mode != 1 && mode != 0) {
                System.out.println("invalid option, enter 0 for in-person or 1 for online:");
            }
        } while (mode != 1 && mode != 0);
        scanner.nextLine();

        //retrieve list of doctors
        ArrayList<HealthCareOfficial> doctors = HealthcareOfficialsTableManager.getInstance().GetRecords(null, null, null,null);
        for (int i = 0; i < doctors.size(); i++){
            System.out.println((i + 1) + "." + " " + doctors.get(i).getAccount().getName() + " - " + doctors.get(i).getAccount().getId());
        }
        int d = 0;
        do {
            System.out.println("Please choose which doctor you'd like to book: ");
            d = scanner.nextInt();
            if (d <= 0 || d > doctors.size())
                System.out.println("Invalid choice.");
        }while (d <= 0 || d > doctors.size());
        AppointmentManager.getInstance().scheduleAppointment(apptDate, 1, student, doctors.get(d - 1).getAccount().getId(), mode, type);
        System.out.println("\nYour appointment has been booked successfully!");
    }

    private void displayCheckAppointmentStatus() throws SQLException {
        System.out.println("**************Welcome to the Appointment Status Page*******************\n\n");

        //display all appointment ids:
        AppointmentManager.getInstance().getStudentAppointments(getStudent().getAccount().getId());
        Scanner scanner = new Scanner(System.in);
        int appointmentId, appointmentStatus;
        do {
            System.out.println("Enter an appointment ID to check. Enter -1 to exit.\n");

            appointmentId = scanner.nextInt();

            if (appointmentId == -1)
                break;
            appointmentStatus = AppointmentManager.getInstance().checkAppointmentStatus(appointmentId);
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

    private void displayCheckRequestStatus() throws SQLException {
        System.out.println("**************Welcome to the Check Request Status Page*******************\n\n");
        //display request ids here:
        ArrayList<Request> requests = requestManager.getStudentRequests(getStudent().getAccount().getId());
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a request ID to check. Enter -1 to exit.\n");
        int reqId;
        do {
            reqId = scanner.nextInt();
            if (reqId < 1)
                System.out.println("Invalid ID entered, enter valid ID >0");
        } while (reqId < 1 && reqId != -1);
        boolean found;
        while (reqId != -1) {
            found = false;
            for (Request reqt : requests) {
                if (reqt.getId() == reqId) {
                    found = true;
                    statusUpdate(reqt.getStatus(), reqId);
                }
            }
            if (!found) {
                System.out.println("Request not found , enter ID again\n");
            }
            System.out.print("Enter a request ID to check. Enter -1 to exit.\n");
            reqId = scanner.nextInt();
        }
        scanner.nextLine();
    }

    private void displaySubmitRequest(String requestType) throws SQLException {
        System.out.println("**************Welcome to the Submit Request Page**********************\n\n");

        Scanner scanner = new Scanner(System.in);
        int choice;
        String form;
        do {
            System.out.println("Please choose an option: \n");
            System.out.println("1. Submit a " + requestType + " Request.");
            System.out.println("2. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter request form:");
                    form = scanner.nextLine();
                    if (form.isEmpty())
                        form = "Empty";
                    requestManager.submitRequest(student.getAccount().getId(), currentDate, form, requestType, student.getEid());
                    if (Objects.equals(requestType, "sickleave"))
                        System.out.println(" Note: Request is valid for only 7 days starting today." +
                                "If you need a longer period of absence excused please apply again after 5 days");
                    System.out.println("Submitting " + requestType + " request....\n");
                    System.out.println("Request submitted succesfully.\n");
                    break;
                case 2:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.\n\n");
                    break;
            }
        } while (choice != 2);
    }

    private void displayAppointmentsHistory() throws SQLException {
        ArrayList<Appointment> appointments = AppointmentManager.getInstance().getStudentAppointments(student.getAccount().getId());
        for (Appointment appt : appointments) {
            System.out.println(appt.toString());
        }
    }

    private void displayRequestsHistory() throws SQLException {
        ArrayList<Request> requests = requestManager.getStudentRequests(student.getAccount().getId());
        for (Request rqst : requests) {
            System.out.println(rqst.toString());
        }
    }
}
