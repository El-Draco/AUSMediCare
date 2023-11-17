package RequestManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PrescriptionRefillRequest extends Request{
    public PrescriptionRefillRequest(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }

    public PrescriptionRefillRequest(int id , String studentId, Date date, String form, String type, String studentEid){
        super(id,studentId, date, form, type,studentEid);
    }
}
