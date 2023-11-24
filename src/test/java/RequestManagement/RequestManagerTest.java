package RequestManagement;

import DatabaseManagement.RequestsTableManager;
import DatabaseManagement.UsersTableManager;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestManagerTest {

    @Test
    void retrieveRequests() throws SQLException {
        RequestManager mng = new RequestManager();
        assertInstanceOf(ArrayList.class, mng.retrieveRequests());
    }

    @Test
    void submitRequest() throws SQLException {
        RequestManager mng = new RequestManager();
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "students_id = 'b00089207'",
                "request_form = 'Sloopy'"}));
        mng.submitRequest("b00089207", new Date(), "Sloopy", "referral", "9393939393");
        assertTrue(RequestsTableManager.getInstance().RecordExists(params));
        RequestsTableManager.getInstance().DeleteRecords(params);
    }

    @Test
    void processRequest() throws SQLException {
        RequestManager mng = new RequestManager();
        mng.submitRequest("b00089207", new Date(), "SloopyTest", "referral", "9393939393");
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "request_form = 'SloopyTest'"}));
        int reqID = RequestsTableManager.getInstance().GetRecords(null, params, null, null)
                .get(0).getId();
        mng.processRequest(reqID, 2);
        assertEquals(2, RequestsTableManager.getInstance().GetRecords(null,
                params, null, null).get(0).getStatus());
        RequestsTableManager.getInstance().DeleteRecords(params);
    }

    @Test
    void cancelRequest() throws SQLException {
        RequestManager mng = new RequestManager();
        mng.submitRequest("b00089207", new Date(), "SloopyTest", "referral", "9393939393");
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "request_form = 'SloopyTest'"}));
        int reqID = RequestsTableManager.getInstance().GetRecords(null, params, null, null)
                .get(0).getId();
        mng.cancelRequest(reqID);
        assertEquals(3, RequestsTableManager.getInstance().GetRecords(null,
                params, null, null).get(0).getStatus());
        RequestsTableManager.getInstance().DeleteRecords(params);
    }

    @Test
    void checkRequestStatus() throws SQLException {
        RequestManager mng = new RequestManager();
        mng.submitRequest("b00089207", new Date(), "SloopyTest", "referral", "9393939393");
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "request_form = 'SloopyTest'"}));
        Request request = RequestsTableManager.getInstance().GetRecords(null, params, null, null)
                .get(0);
        int reqID = request.getId();
        int stat = request.getStatus();
        assertEquals(stat, mng.checkRequestStatus(reqID));
        assertEquals(-1, mng.checkRequestStatus(255));
        RequestsTableManager.getInstance().DeleteRecords(params);
    }
}