package UserManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
public class Administration extends User implements AccessStudentProfile{

    public void accessStudentProfile(){}
    public Administration(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }
}
