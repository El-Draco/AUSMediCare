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
    private String studentID;
    private String healthcareofficialID;

    private String studentEID;
    private HealthCareOfficial doctor;
    private int appointmentID;
    private int status; //1 = booked, 2 = done, 3 = cancelled
    private Date date;
    private int appointmentType; //0 is for medical and 1 is for therapy
    private int appointmentMode; //0 is for in-person and 1 is for online

    public Student getPatient() {
        return patient;
    }

    public void setPatient(Student patient) {
        this.patient = patient;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getHealthcareofficialID() {
        return healthcareofficialID;
    }

    public void setHealthcareofficialID(String healthcareofficialID) {
        this.healthcareofficialID = healthcareofficialID;
    }

    public String getStudentEID() {
        return studentEID;
    }

    public void setStudentEID(String studentEID) {
        this.studentEID = studentEID;
    }

    public HealthCareOfficial getDoctor() {
        return doctor;
    }

    public void setDoctor(HealthCareOfficial doctor) {
        this.doctor = doctor;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int isAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(int appointmentType) {
        this.appointmentType = appointmentType;
    }

    public int isAppointmentMode() {
        return appointmentMode;
    }

    public void setAppointmentMode(int appointmentMode) {
        this.appointmentMode = appointmentMode;
    }

    Appointment(int appointmentID, Date date, int status, Student patient, HealthCareOfficial doctor, int appointmentType, int appointmentMode ){
        this.appointmentID=appointmentID;
        this.date=date;
        this.status=status;
        this.patient=patient;
        this.doctor=doctor;
        this.appointmentMode=appointmentMode;
        this.appointmentType=appointmentType;
    }
    public Appointment(ResultSet resultSet) throws SQLException {
        this.appointmentID = resultSet.getInt("appointment_id");
        this.healthcareofficialID = resultSet.getString("healthcareofficials_id");
        this.studentID = resultSet.getString("students_id");
        this.date = resultSet.getDate("appointment_date");
        this.appointmentType = resultSet.getInt("appointment_type");
        this.appointmentMode = resultSet.getInt("appointment_mode");
        this.appointmentType = resultSet.getInt("appointment_type");
        this.studentEID = resultSet.getString("students_eid");
    }

    public void UpdateAppointment(int status) throws SQLException {
        //System.out.println(UsersTableManager.getInstance().RecordExists(
        //              new ArrayList<String>(List.of(new String[]{"user_id = 'b00087311'"}))
        //        ));
        this.status = status;
        AppointmentsTableManager.getInstance().UpdateRecords(
                new ArrayList<>(List.of(new String[]{"appointment_status = " + status})),
                new ArrayList<String>(List.of(new String[]{"appointment_id = '"
                        + appointmentID+"'", "students_id = '" + patient.getAccount().getId()
                        +"'","healthcareofficials_id = '" + doctor.getAccount().getId()+"'"})));
    }

    public void CancelAppointment() throws SQLException {
        UpdateAppointment(3);
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

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", Status: " + status;
    }
}
