package UserManagement;

import DatabaseManagement.AppointmentsTableManager;
import DatabaseManagement.StudentsTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student extends User{
    private String major;
    private int age;
    private boolean gender; //True=Female, False= Male
    private String eid;






    public Student(Account account, boolean loggedIn, String major, int age, boolean gender, String eid, String name,  String email) throws SQLException {
        super(account, loggedIn,name,email);
        this.major = major;
        this.age = age;
        this.gender = gender;
        this.eid = eid;

    }

    public Student(ResultSet resultSet, Account account, String major, int age, boolean gender, String eid, Boolean Gender) throws SQLException {

        super(resultSet, account);
        this.major = major;
        this.age = age;
        this.gender = gender;
        this.eid = eid;

    }

    public Student(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }

    public Student getInstance(){
        return this;
    }
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


    public void updateInfo(User user) throws SQLException {
        Student student=(Student)user;
        StudentsTableManager.getInstance().UpdateRecords(
                new ArrayList<>(List.of(new String[]{"name = '" + student.getName() +"'"
                        ,"email = '" + student.getEmail() +"'","eid = '" + student.eid+"'"
                        ,"major = '" + student.major
                        ,"Age = '" + student.age+"'","gender = '" + student.gender+"'"})),
                new ArrayList<String>(List.of(new String[]{"id = '"
                        + student.getAccount().getId()+"'"})));
    }

}
