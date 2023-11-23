package DatabaseManagement;

import AppointmentManagement.Appointment;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentsTableManagerTest {

    @Test
    void recordExists() {
    }

    @Test
    void addRecord() {
    }

    @Test
    void deleteRecords() {
    }

    @Test
    void updateRecords() {
    }

    @Test
    void getRecords() throws SQLException {
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

    //remove if u want. just ane extra test we developed - might as well keep
    @Test
    void getRecordStatus() throws SQLException {
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