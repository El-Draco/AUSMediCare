package RequestManagement;

import DatabaseManagement.RequestsTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RequestManager {
    ArrayList<Request> requests;
    public ArrayList<Request> RetrieveRequests() throws SQLException {
        if(requests==null) requests = RequestsTableManager.getInstance().GetRecords(null, null, null, null);
        return requests;
    }
}
