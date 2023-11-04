package UserManagement;

<<<<<<< Updated upstream
public class Administration extends User implements AccessStudentProfile{

    public void accessStudentProfile(){}
=======
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administration extends User{
    public Administration(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }
>>>>>>> Stashed changes
}
