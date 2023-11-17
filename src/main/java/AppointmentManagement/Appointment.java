package AppointmentManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import DatabaseManagement.AppointmentsTableManager;
import UserManagement.Student;
import UserManagement.HealthCareOfficial;

public class Appointment {

    private Student patient;
    private HealthCareOfficial doctor;
    private int appointmentID;
    private int status; //1 = booked, 2 = done, 3 = cancelled
    private Date date;
    private boolean appointmentType; //0 is for medical and 1 is for therapy
    private boolean appointmentMode; //0 is for in-person and 1 is for online

    Appointment(int appointmentID, Date date, int status, Student patient, HealthCareOfficial doctor, boolean appointmentType, boolean appointmentMode ){
        this.appointmentID=appointmentID;
        this.date=date;
        this.status=status;
        this.patient=patient;
        this.doctor=doctor;
        this.appointmentMode=appointmentMode;
        this.appointmentType=appointmentType;
    }
    public Appointment(ResultSet resultSet) throws SQLException {

    }
    public int getAppointmentID() {
        return this.appointmentID;
    }

    public void updateStatus(int status){
        this.status = status;
    }

    public Date getDate() {
        return this.date;
    }
    public int getStatus(){
        return this.status;
    }

    public void UpdateAppointment(int status) throws SQLException {
        //System.out.println(UsersTableManager.getInstance().RecordExists(
        //              new ArrayList<String>(List.of(new String[]{"user_id = 'b00087311'"}))
        //        ));
        AppointmentsTableManager.getInstance().UpdateRecords(new ArrayList<>(List.of(new String[]{"appointment_status = " + status})), new ArrayList<String>(List.of(new String[]{"appointment_id = '" + appointmentID+"'", "students_id = '" + patient.getAccount().getId()+"'","healthcare officials_id = '" + doctor.getAccount().getId()+"'"})));
    }

    public void CancelAppointment() throws SQLException {
        UpdateAppointment(3);
    }


}
