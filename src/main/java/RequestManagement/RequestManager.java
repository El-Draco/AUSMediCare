package RequestManagement;

import DatabaseManagement.RequestsTableManager;

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
        if(requests==null) requests = RequestsTableManager.getInstance().GetRecords(null, null, null, null);
        return requests;
    }

    public void getRequests(String studentId) throws SQLException {
        for(Request request : requests){
            if(Objects.equals(request.getStudentId(), studentId))
                System.out.println(request);
        }
        //return RequestsTableManager.getInstance().GetRecords(null, new ArrayList<String>(List.of(new String[]{"students_id = " + studentId})), null, null);
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

    public int checkRequestStatus(int id, String type){
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
