package AppointmentManagement;
import DatabaseManagement.AdministrationTableManager;
import DatabaseManagement.AppointmentsTableManager;
import UserManagement.HealthCareOfficial;

import java.sql.SQLException;
import java.util.List;

import java.util.*;
public class Schedule {

    ArrayList<Appointment> appointments = new ArrayList<>();
    ArrayList<HealthCareOfficial> doctors = new ArrayList<>();
    private static Schedule instance;

    public static Schedule getInstance() {
        if (instance == null)
            instance = new Schedule();
        return instance;
    }

    boolean CheckAvailability(Date d, String doctorID) throws SQLException {
        boolean availdate = true;
        AppointmentManager.getInstance().retrieveAppointments();
        for (Appointment appointment : appointments) {
            if (appointment.getDate() == d) {
                availdate = false;
                break;
            }
        }

        return availdate;
    }
}

