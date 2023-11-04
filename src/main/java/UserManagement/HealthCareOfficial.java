package UserManagement;

<<<<<<< Updated upstream
public class HealthCareOfficial extends User implements AccessStudentProfile{
    public void accessStudentProfile(){}

=======
import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthCareOfficial extends User{
    public HealthCareOfficial(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }
>>>>>>> Stashed changes
}
