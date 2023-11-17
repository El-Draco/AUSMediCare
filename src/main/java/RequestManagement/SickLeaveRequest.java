package RequestManagement;

import DatabaseManagement.AppointmentsTableManager;
import DatabaseManagement.ProfessorsTableManager;
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

    public void notifyProfessors() throws SQLException {
        //@TODO: Shafai needs to check database logic
        ArrayList<String> professors = ProfessorsTableManager.getInstance().GetRecords(null, new ArrayList<String>(List.of(new String[]{"students_id = '" + super.getStudentId()+"'"})));
        for(int i=0; i< professors.size();i++){
            System.out.println("Professor (" + professors.get(i) +") has been notified.");
        }
        System.out.println("Emails have been sent to the professors.");
    }

    public void processRequest(int status) throws SQLException {
        super.processRequest(status);
        if (status == 1)
            notifyProfessors();
    }

}
