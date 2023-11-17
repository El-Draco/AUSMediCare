package RequestManagement;

import DatabaseManagement.RequestsTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


public class RequestManager {
    private ArrayList<Request> requests;

    public RequestManager() throws SQLException {
        requests = RetrieveRequests();
    }
    public ArrayList<Request> RetrieveRequests() throws SQLException {
        if(requests==null) requests = RequestsTableManager.getInstance().GetRecords(null, null, null, null);
        return requests;
    }

    public void submitRequest(int id, String studentId, Date date, String form, String type, String studentEid) throws SQLException {
        Request request = null;
        if(Objects.equals(type, "sickleave"))
            request = new SickLeaveRequest(id, studentId, date, form, type, studentEid);

        else if(Objects.equals(type,"referral"))
            request = new ReferralRequest(id, studentId, date, form, type, studentEid);

        else if(Objects.equals(type,"refill"))
            request = new PrescriptionRefillRequest(id, studentId, date, form, type, studentEid);

        else
            System.out.println("Incorrect type input. Please try again.");

        if(request != null) {
            request.submitRequest();
            requests.add(request);
        }
    }

    public void processRequest(int id, int status) throws SQLException {
        for(Request request : requests){
            if(request.getId() == id){
                request.processRequest(status);
            }
        }
    }

    public void cancelRequest(int id) throws SQLException {
        for(Request request : requests){
            if(request.getId() == id){
                request.cancelRequest();
            }
        }
    }

    public int checkRequestStatus(int id, String type){
        for(Request request : requests){
            if(request.getId() == id && Objects.equals(request.getType(), type)){
                return request.getStatus();
            }
        }
        return -1; //request is not found, it's not the correct type, or there are no requests
    }

}
