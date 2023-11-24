package UserManagement;

import DatabaseManagement.AdministrationTableManager;
import DatabaseManagement.StudentsTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Administration extends User implements AccessStudentProfile{


    public ArrayList<Student> accessStudentProfile() throws SQLException {
        return StudentsTableManager.getInstance().GetRecords(null, null, null, null);
    }

    public Administration(Account account, String name) {
        super(account, name);
    }
    public Administration(Account account) throws SQLException {
        super(account);
    }
    public Administration getInstance(){
        return this;
    }

}
