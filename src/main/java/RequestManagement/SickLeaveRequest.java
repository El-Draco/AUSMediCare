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

    public SickLeaveRequest(int id, String studentId, Date date, String form, String type, int status, String studentEid){
        super(id,studentId, date, form, type, status, studentEid);
    }

    public void notifyProfessors() throws SQLException {
        //@TODO: Shafai needs to check database logic
        ArrayList<String> professors = ProfessorsTableManager.getInstance().GetRecords(null, new ArrayList<String>(List.of(new String[]{"students_id = '" + super.getStudentId()+"'"})));
        for (String professor : professors) {
            System.out.println("Professor (" + professor + ") has been notified.");
        }
        System.out.println("Emails have been sent to the professors.");
    }

    public void processRequest(int status) throws SQLException {
        super.processRequest(status);
        if (status == 1)
            notifyProfessors();
    }

}
