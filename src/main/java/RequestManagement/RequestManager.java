package RequestManagement;

import DatabaseManagement.RequestsTableManager;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class RequestManager {
    private ArrayList<Request> requests;

    public RequestManager() throws SQLException {
        requests = retrieveRequests();
    }
    public ArrayList<Request> retrieveRequests() throws SQLException {
        requests = RequestsTableManager.getInstance().GetRecords(null, null, null, null);
        return requests;
    }

    public ArrayList<Request> getStudentRequests(String studentId) throws SQLException {
        retrieveRequests();
        ArrayList<Request> _requests = new ArrayList<>();
        for(Request request : requests){
            if(request.getStudentId().equals(studentId))
                _requests.add(request);
        }
        return _requests;
    }

    public void submitRequest(String studentId, Date date, String form, String type, String studentEid) throws SQLException {
        retrieveRequests();
        int id = 0;
        if(requests!=null) id = requests.size()+1;

        Request request = null;
        if(Objects.equals(type, "sickleave"))
            request = new SickLeaveRequest(id, studentId, date, form, type, 1, studentEid);

        else if(Objects.equals(type,"referral"))
            request = new ReferralRequest(id, studentId, date, form, type, 1, studentEid);

        else if(Objects.equals(type,"refill"))
            request = new PrescriptionRefillRequest(id, studentId, date, form, type, 1, studentEid);

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
                request.setStatus(status);
            }
        }
    }

    public void cancelRequest(int id) throws SQLException {
        for(Request request : requests){
            if(request.getId() == id){
                request.cancelRequest();
                request.setStatus(3);
            }
        }
    }

    public int checkRequestStatus(int id, String type) throws SQLException {
        retrieveRequests();
        for(Request request : requests){
            if(request.getId() == id && Objects.equals(request.getType(), type)){
                return request.getStatus();
            }
        }
        return -1; //request is not found, it's not the correct type, or there are no requests
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Request request : requests){
            s.append(request.toString()).append("\n");
        }
        return s.toString();
    }

}
