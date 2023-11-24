package DatabaseManagement;

import RequestManagement.PrescriptionRefillRequest;
import RequestManagement.ReferralRequest;
import RequestManagement.Request;
import RequestManagement.SickLeaveRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RequestsTableManager extends TableManager {
    // overriden methdods
    private RequestsTableManager() {
        super("Requests");
    }

    // member methods
    public ArrayList<Request> GetRecords(ArrayList<String> params, ArrayList<String> conds, String groupBy, String orderBy) throws SQLException {
        ArrayList<Request> requests = new ArrayList<>();
        String sql = this.ProcessSql(params, conds);
        try (Statement statement = this.GetStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String type = resultSet.getString("request_type");
                    if (type.equals("referral"))
                        requests.add(new ReferralRequest(resultSet));
                    else if (type.equals("refill"))
                        requests.add(new PrescriptionRefillRequest(resultSet));
                    else if (type.equals("sickleave"))
                        requests.add(new SickLeaveRequest(resultSet));
                }
            }
        }
        return requests;
    }
    public static RequestsTableManager getInstance() {
        if (instance == null)
            instance = new RequestsTableManager();
        return instance;
    }
    // member fields
    private static RequestsTableManager instance;
}
