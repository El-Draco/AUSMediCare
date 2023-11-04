package UserManagement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class HealthCareOfficial extends User implements AccessStudentProfile{
    public void accessStudentProfile(){}
    public HealthCareOfficial(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }
}
