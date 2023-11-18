package AppointmentManagement;

import DatabaseManagement.AppointmentsTableManager;
import UserManagement.Account;
import UserManagement.HealthCareOfficial;
import UserManagement.Student;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentManagerTest {

    @Test
    void scheduleAppointment() throws SQLException {

        Student patient = new Student(
                new Account("g00087725","4297f44b13955235245b2497399d7a93", "rana"),
                "coe",21,0,"11111");
        HealthCareOfficial doctor = new HealthCareOfficial(new Account("b00087311",
                "4297f44b13955235245b2497399d7a93", "Mohamed Alshafai"));
        Date date = new Date();
        Appointment appointment = new Appointment(1,date,0,patient, doctor, false,false);
        Schedule schedule = Schedule.getInstance();
        if(schedule.CheckAvailability(date,doctor)){
            String pattern = "dd/MMM/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            String dateAsString = df.format(date);
            ArrayList<String> _params =  new ArrayList<String>(List.of(new String[]{
                    "" + (new Random()).nextInt(1000) +"",
                    "'" + doctor.getAccount().getId()+"'",
                    "'" + patient.getAccount().getId() +"'",
                    "'" + dateAsString + "'",
                    "" + 0,
                    "" + 1,
                    "" + 1,
                    "'" + patient.getEid() + "'"
            }));
            assertEquals(1,AppointmentsTableManager.getInstance().AddRecord(_params));
        }
        else{
            System.out.println("Appointment is not available");
        }
    }

    @Test
    void getAppointments() throws SQLException {
        String studentId = "g00087725";
        ArrayList<Appointment> appointments = AppointmentsTableManager.getInstance().GetRecords(null,null,null,null);
        StringBuilder appointment_db_result = new StringBuilder();
        for(Appointment appointment : appointments){
            if(appointment.getStudentID().equals(studentId)) {
                appointment_db_result.append(appointment.toString());
            }
        }
        String appointment_expected = "Appointment ID: 1, Status: 0";
        assertEquals(appointment_expected,appointment_db_result.toString());
    }

    @Test
    void checkAppointmentStatus() throws SQLException {
        int status = 0;
        ArrayList<Appointment> appointments = AppointmentsTableManager.getInstance().GetRecords(null,null,null,null);
        int aid = 1;
        int status_true = 0;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == aid)
                status = appointment.getStatus();
        }
        assertEquals(status_true,status);
    }
}