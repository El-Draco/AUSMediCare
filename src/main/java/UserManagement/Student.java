package UserManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends User{
    private String major;
    private int age;
    private boolean gender;
    private String eid;

    private String id;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student(Account account, boolean loggedIn, String major, int age, boolean gender, String eid, String id) {
        super(account, loggedIn);
        this.major = major;
        this.age = age;
        this.gender = gender;
        this.eid = eid;
        this.id=id;
    }

    public Student(ResultSet resultSet, Account account, String major, int age, boolean gender, String eid, String id) throws SQLException {
        super(resultSet, account);
        this.major = major;
        this.age = age;
        this.gender = gender;
        this.eid = eid;
        this.id=id;
    }

    public Student(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }

//    public void editMedicalHistory() {
//        //@TODO: Implement Edit Medical History
//
//    }
//
//    /*public void submitRequest(String type) {
//        //@TODO: Implement Submit New Request
//        //idea: have a RequestManager object which would handle this
//    }
//    public void checkRequestStatus(String type) {
//        //@TODO: Implement Check Request Status
//        //idea: have a RequestManager object which would handle this
//    }
//    */
//    public void scheduleAppointment() {
//        //@TODO: Implement Schedule Appointment
//        //idea: have an AppointmentManager object for this
//    }
//    public void checkAppointmentStatus() {
//        //@TODO: Implement Check Appointment Status
//    }


}
