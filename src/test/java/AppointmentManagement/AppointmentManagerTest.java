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
        AppointmentManager appointmentManager = new AppointmentManager();

//        appointmentManager.scheduleAppointment(new Date(), 1, student, "b00087311", mode, type);

    }

    @Test
    void getAppointments() throws SQLException {

    }

    @Test
    void checkAppointmentStatus() throws SQLException {

    }
}