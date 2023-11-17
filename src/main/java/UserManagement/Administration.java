package UserManagement;

import DatabaseManagement.AdministrationTableManager;
import DatabaseManagement.HealthcareOfficialsTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Administration extends User implements AccessStudentProfile{
    public Administration(Account account, boolean loggedIn) {
        super(account, loggedIn);
    }

    public void accessStudentProfile(){}
    public Administration(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }
    public Administration getInstance(){
        return this;
    }
    public Administration(ResultSet resultSet, Account account,String name,String email) throws SQLException {
        super(resultSet, account,name,email);
    }


    public void updateInfo(User user) throws SQLException {
        AdministrationTableManager.getInstance().UpdateRecords(
                new ArrayList<>(List.of(new String[]{"name = '" + user.getName() +"'"
                        ,"email = '" + user.getEmail() +"'"})),
                new ArrayList<String>(List.of(new String[]{"id = '"
                        + user.getAccount().getId() +"'"})));
    }

}
