package UserManagement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class HealthCareOfficial extends User implements AccessStudentProfile{
    public void accessStudentProfile(){}
    public HealthCareOfficial(ResultSet resultSet, Account account) throws SQLException {
        super(resultSet, account);
    }
    public HealthCareOfficial(Account account, boolean loggedIn) {
        super(account, loggedIn);
    }

//    public void manageSickLeaveRequests(){
//        //@TODO: Implement
//    }
//    public void manageReferralRequests() {
//        //@TODO: Implement
//    }
//    public void manageAppointments() {
//        //@TODO: Implement
//    }
//    public void managePrescriptionRefillRequests() {
//        //@TODO: Implement
//    }
}
