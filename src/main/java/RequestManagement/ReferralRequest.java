package RequestManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReferralRequest extends Request{
    public ReferralRequest(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }

    public ReferralRequest(int id, String studentId, Date date, String form, String type, int status, String studentEid){
        super(id,studentId, date, form, type, status, studentEid);
    }
}
