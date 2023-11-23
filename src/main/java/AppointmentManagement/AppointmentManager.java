package AppointmentManagement;

import DatabaseManagement.AppointmentsTableManager;
import UserManagement.HealthCareOfficial;
import UserManagement.Student;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

public class AppointmentManager {

    ArrayList<Appointment> appointments = new ArrayList<>();
    Schedule schedule;
    int apptype; //0 is for medical and 1 is for therapy
    int appmode; //0 is for in-person and 1 is for online
    public AppointmentManager() {
        schedule = new Schedule();
    }
    Scanner scanner = new Scanner(System.in);
    public void scheduleAppointment(Date date, int status, Student patient, String doctorID, int mode, int type) throws SQLException {
        retrieveAppointments();
        int appid = 0;
        if(appointments!=null) appid=appointments.size()+1;
        if(schedule.CheckAvailability(date, doctorID)) {

            Appointment appointment = new Appointment(appid, date, status, patient, doctorID, type, mode);
            appointments.add(appointment);
            String pattern = "dd/MMM/yyyy";
            // Create an instance of SimpleDateFormat used for formatting
            // the string representation of date according to the chosen pattern
            DateFormat df = new SimpleDateFormat(pattern);
            // Using DateFormat format method we can create a string
            // representation of a date with the defined format.
            String dateAsString = df.format(date);
            ArrayList<String> _params =  new ArrayList<String>(List.of(new String[]{
                    appid +"",
                    "'" + doctorID +"'",
                    "'" + patient.getAccount().getId() +"'",
                    "'" + dateAsString + "'",
                    "" + type,
                    "" + mode,
                    "" + status,
                    "'" + patient.getEid() + "'"
                    }));
            AppointmentsTableManager.getInstance().AddRecord(_params);
        }
        else
            System.out.println("Appointment is not available");
    }
    public ArrayList<Appointment> retrieveAppointments() throws SQLException {
        appointments = AppointmentsTableManager.getInstance().GetRecords(null,
                null, null, null);
        return appointments;
    }

    public ArrayList<Appointment> getStudentAppointments(String studentId) throws SQLException {
        retrieveAppointments();
        ArrayList<Appointment> stdAppts = new ArrayList<>();
        for(Appointment appointment : appointments){
            if(appointment.getStudentID().equals(studentId))
                stdAppts.add(appointment);
        }
        return stdAppts;
    }

    public int checkAppointmentStatus(int aid) throws SQLException {
        int status = 0;
        retrieveAppointments();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == aid)
                status = appointment.getStatus();
        }
        return status;
    }
}
