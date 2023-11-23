package UserManagement;
import DatabaseManagement.HealthcareOfficialsTableManager;
import DatabaseManagement.StudentsTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthCareOfficial extends User implements AccessStudentProfile{
    public ArrayList<Student> accessStudentProfile() throws SQLException {
        return StudentsTableManager.getInstance().GetRecords(null, null, null, null);
    }
    public HealthCareOfficial(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }
    public HealthCareOfficial(Account account, String name) {
        super(account, name);
    }

    public HealthCareOfficial(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }
    public HealthCareOfficial(Account account) {
        super(account);
    }
    public HealthCareOfficial getInstance(){
        return this;
    }

}
