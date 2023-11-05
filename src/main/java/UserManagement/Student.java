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
}
