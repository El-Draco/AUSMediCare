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
    public HealthCareOfficial(Account account, boolean loggedIn) {
        super(account, loggedIn);
    }

    public HealthCareOfficial(ResultSet resultSet, Account account,String name,String email) throws SQLException {
        super(resultSet, account,name,email);
    }
    public HealthCareOfficial getInstance(){
        return this;
    }

    public void updateInfo(User user) throws SQLException {
        HealthcareOfficialsTableManager.getInstance().UpdateRecords(
                new ArrayList<>(List.of(new String[]{"name = '" + user.getName() +"'"
                        ,"email = '" + user.getEmail() +"'"})),
                new ArrayList<String>(List.of(new String[]{"id = '"
                        + user.getAccount().getId() +"'"})));
    }

}
