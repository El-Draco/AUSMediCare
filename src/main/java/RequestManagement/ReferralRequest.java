package RequestManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferralRequest extends Request{
    public ReferralRequest(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }
}
