package RequestManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionRefillRequest extends Request{
    public PrescriptionRefillRequest(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }
}
