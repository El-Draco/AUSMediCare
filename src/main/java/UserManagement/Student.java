package UserManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends User{
    private String major;
    private int age;
    private boolean gender;
    private String eid;

    public Student(Account account, boolean loggedIn, String major, int age, boolean gender, String eid) {
        super(account, loggedIn);
        this.major = major;
        this.age = age;
        this.gender = gender;
        this.eid = eid;
    }

    public Student(ResultSet resultSet, Account account, String major, int age, boolean gender, String eid) throws SQLException {
        super(resultSet, account);
        this.major = major;
        this.age = age;
        this.gender = gender;
        this.eid = eid;
    }

    public Student(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }

    public void editMedicalHistory() {
        //@TODO: Implement Edit Medical History

    }

    public void submitRequest(String type) {
        //@TODO: Implement Submit New Request
        //idea: have a RequestManager object which would handle this
    }
    public void checkRequestStatus(String type) {
        //@TODO: Implement Check Request Status
        //idea: have a RequestManager object which would handle this
    }
    public void scheduleAppointment() {
        //@TODO: Implement Schedule Appointment
        //idea: have an AppointmentManager object for this
    }
    public void checkAppointmentStatus() {
        //@TODO: Implement Check Appointment Status
    }


}
