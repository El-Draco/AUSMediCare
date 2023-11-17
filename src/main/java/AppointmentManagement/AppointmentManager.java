package AppointmentManagement;

import DatabaseManagement.AppointmentsTableManager;
import UserManagement.HealthCareOfficial;
import UserManagement.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AppointmentManager {

    ArrayList<Appointment> appointments = new ArrayList<>();
    Schedule schedule;
    boolean apptype; //0 is for medical and 1 is for therapy
    boolean appmode; //0 is for in-person and 1 is for online

    Scanner scanner = new Scanner(System.in);
    public void scheduleAppointment(int appid,Date date,int status,Student patient,HealthCareOfficial doctor){

        if(schedule.CheckAvailability(date,doctor)) {
            System.out.println("Select appointment type: ");
            apptype = scanner.nextBoolean();
            appmode = scanner.nextBoolean();
            Appointment appointment = new Appointment(appid,date,status,patient,doctor,apptype,appmode);
            appointment.updateStatus(1);    //update status as booked
            appointments.add(appointment);
        }
        else
            System.out.println("Appointment is not available");
    }
    public ArrayList<Appointment> RetrieveAppointment() throws SQLException {
        if(appointments==null) appointments = AppointmentsTableManager.getInstance().GetRecords(null, null, null, null);
        return appointments;
    }

    public int checkAppointmentStatus(int aid){
        int status = 0;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == aid)
                status = appointment.getStatus();
        }
        return status;
    }

}
