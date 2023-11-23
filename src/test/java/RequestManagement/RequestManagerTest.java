package RequestManagement;

import DatabaseManagement.RequestsTableManager;
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
        assertFalse(mng.retrieveRequests().isEmpty());
    }

    @Test
    void submitRequest() throws SQLException {
        RequestManager mng = new RequestManager();
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "students_id = 'b00089207'",
                "request_form = 'Sleepy'"}));
        mng.submitRequest("b00089207", new Date(), "Sleepy", "referral", "9393939393");
        assertTrue(RequestsTableManager.getInstance().RecordExists(params));
    }

    @Test
    void processRequest() throws SQLException {
        RequestManager mng = new RequestManager();
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "request_id = 5"}));
        mng.processRequest(7, 2);
        assertEquals(2, RequestsTableManager.getInstance().GetRecords(null,
                params, null, null).get(0).getStatus());
    }

    @Test
    void cancelRequest() throws SQLException {
        RequestManager mng = new RequestManager();
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "request_id = 1"}));
        mng.cancelRequest(1);
        assertEquals(3, RequestsTableManager.getInstance().GetRecords(null,
                params, null, null).get(0).getStatus());
    }

    @Test
    void checkRequestStatus() throws SQLException {
        RequestManager mng = new RequestManager();
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "request_id = 5"}));
        assertEquals(1, mng.checkRequestStatus(8));
        assertEquals(-1, mng.checkRequestStatus(255));
    }
}