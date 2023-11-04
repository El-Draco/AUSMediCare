package RequestManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SickLeaveRequest extends Request{
    public SickLeaveRequest(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }
}
