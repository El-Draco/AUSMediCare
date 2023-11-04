package UserManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends User{
    private String major;
    private int age;
    private boolean gender;
    private String eid;

    Student() {super();}
    public Student(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }
}
