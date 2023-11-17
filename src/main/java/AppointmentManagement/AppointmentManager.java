package AppointmentManagement;

import DatabaseManagement.AppointmentsTableManager;
import RequestManagement.Request;
import UserManagement.HealthCareOfficial;
import UserManagement.Student;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

public class AppointmentManager {

    ArrayList<Appointment> appointments = new ArrayList<>();
    Schedule schedule;
    boolean apptype; //0 is for medical and 1 is for therapy
    boolean appmode; //0 is for in-person and 1 is for online

    Scanner scanner = new Scanner(System.in);
    public void scheduleAppointment(int appid,Date date,int status,Student patient,HealthCareOfficial doctor) throws SQLException {

        if(schedule.CheckAvailability(date,doctor)) {
            System.out.println("Select appointment type: ");
            apptype = scanner.nextBoolean();
            appmode = scanner.nextBoolean();
            Appointment appointment = new Appointment(appid,date,status,patient,doctor,apptype,appmode);
            appointments.add(appointment);
            String pattern = "dd/MMM/yyyy";
            // Create an instance of SimpleDateFormat used for formatting
            // the string representation of date according to the chosen pattern
            DateFormat df = new SimpleDateFormat(pattern);
            // Using DateFormat format method we can create a string
            // representation of a date with the defined format.
            String dateAsString = df.format(date);
            ArrayList<String> _params =  new ArrayList<String>(List.of(new String[]{
                    "'" + appid +"'",
                    "'" + doctor.getAccount().getId()+"'",
                    "'" + patient.getAccount().getId() +"'",
                    "'" + dateAsString + "'",
                    "" + apptype,
                    "" + appmode,
                    "= " + status,
                    "'" + patient.getEid() + "'"
                    }));
            AppointmentsTableManager.getInstance().AddRecord(_params);
        }
        else
            System.out.println("Appointment is not available");
    }
    public ArrayList<Appointment> RetrieveAppointment() throws SQLException {
        if(appointments==null) appointments = AppointmentsTableManager.getInstance().GetRecords(null,
                null, null, null);
        return appointments;
    }

    public void getAppointments(String studentId) throws SQLException {
        for(Appointment appointment : appointments){
            if(Objects.equals(appointment.getPatient().getId(), studentId))
                System.out.println(appointment);
        }
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
