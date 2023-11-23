package DatabaseManagement;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersTableManagerTest {

    @Test
    void recordExists() throws SQLException {
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "user_id = 'b00087311'"}));
        assertTrue(UsersTableManager.getInstance().RecordExists(params));
        params = new ArrayList<String>(List.of(new String[]{
                "user_id = 'GO4'"}));
        assertFalse(UsersTableManager.getInstance().RecordExists(params));
    }

    @Test
    void addRecord() throws NoSuchAlgorithmException, SQLException {
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "'Ariel'",
                "'b00088568'",
                "'" + UsersTableManager.getMD5Hash("12345678") + "'"}));
        assertEquals(1, UsersTableManager.getInstance().AddRecord(params));
    }

    @Test
    void deleteRecords() throws SQLException {
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "user_id = 'b00086528'"}));
        assertEquals(1, UsersTableManager.getInstance().DeleteRecords(params));

        params = new ArrayList<String>(List.of(new String[]{
                "user_id = 'b00090000'"}));
        assertEquals(0, UsersTableManager.getInstance().DeleteRecords(params));

        params = new ArrayList<String>(List.of(new String[]{
                "username = 'extra'"}));
        assertEquals(4, UsersTableManager.getInstance().DeleteRecords(params));
    }

    @Test
    void updateRecords() throws SQLException, NoSuchAlgorithmException {
        ArrayList<String> params = new ArrayList<String>(List.of(new String[]{
                "user_password = 'b00086528'"}));
        ArrayList<String> conds = new ArrayList<String>(List.of(new String[]{
                "user_password = '123123'"}));
        assertEquals(0, UsersTableManager.getInstance().UpdateRecords(params, conds));

        conds = new ArrayList<String>(List.of(new String[]{
                "user_password = '" + UsersTableManager.getMD5Hash("12345678") + "'"}));
        params = new ArrayList<String>(List.of(new String[]{
                "user_password = '" + UsersTableManager.getMD5Hash("123123") + "'"}));
        assertEquals(5, UsersTableManager.getInstance().UpdateRecords(params, conds));
    }
}