package AppointmentManagement;
import DatabaseManagement.AppointmentsTableManager;
import UserManagement.HealthCareOfficial;

import java.sql.SQLException;
import java.util.List;

import java.util.*;
public class Schedule {

    ArrayList<Appointment> appointments = new ArrayList<>();
    ArrayList<HealthCareOfficial> doctors = new ArrayList<>();

    boolean CheckAvailability(Date d, HealthCareOfficial doctor) {
        boolean availdate = true, availdoctor = true;

        for (Appointment appointment : appointments) {
            if (appointment.getDate() == d) {
                availdate = false;
                break;
            }
        }

        for (HealthCareOfficial official : doctors) {
            if (Objects.equals(official.getAccount().getId(), doctor.getAccount().getId())) {
                availdoctor = false;
                break;
            }
        }
        return availdoctor && availdate;
    }
}

