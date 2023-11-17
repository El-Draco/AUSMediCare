package RequestManagement;

import DatabaseManagement.AppointmentsTableManager;
import DatabaseManagement.RequestsTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SickLeaveRequest extends Request{
    public SickLeaveRequest(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }

    public SickLeaveRequest(int id , String studentId, Date date, String form, String type, String studentEid){
        super(id,studentId, date, form, type,studentEid);
    }

    public void notifyProfessors(){
        //@TODO: Implement Notify Professors

    }

    public void processRequest(int status) throws SQLException {
        super.processRequest(status);
        if (status == 1)
            notifyProfessors();
    }

}
