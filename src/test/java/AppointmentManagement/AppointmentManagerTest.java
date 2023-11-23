package AppointmentManagement;

import DatabaseManagement.StudentsTableManager;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentManagerTest {

    @Test
    void getAppointments() throws SQLException {
        AppointmentManager appointmentManager = new AppointmentManager();
        assertEquals(true, appointmentManager.getStudentAppointments("b00090044").size() == 2);
        assertEquals(8, appointmentManager.getStudentAppointments("b00090044").get(0).getAppointmentID());
        assertEquals(9, appointmentManager.getStudentAppointments("b00090044").get(1).getAppointmentID());
    }
    @Test
    void scheduleAppointment() throws SQLException {
        AppointmentManager appointmentManager = new AppointmentManager();
        Date apptDate = new Date();
        appointmentManager.scheduleAppointment(apptDate, 1, StudentsTableManager.getInstance().GetRecords(null,null,null,null).get(1), "b00087311", 1, 1);
        assertEquals(1, appointmentManager.getStudentAppointments("g00087725").get(0).getAppointmentMode());
        assertEquals(1, appointmentManager.getStudentAppointments("g00087725").get(0).getAppointmentType());
    }
    @Test
    void checkAppointmentStatus() throws SQLException {
        AppointmentManager appointmentManager = new AppointmentManager();
        assertEquals(1, appointmentManager.checkAppointmentStatus(1));
        assertEquals(2,appointmentManager.checkAppointmentStatus(5) );
        assertEquals(3,appointmentManager.checkAppointmentStatus(6) );

    }
}