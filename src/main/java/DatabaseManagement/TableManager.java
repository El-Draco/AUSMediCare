package DatabaseManagement;

import UserManagement.Student;
import UserManagement.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class TableManager {
    // member methods
    public TableManager(String tableName) {
        this.tableName = tableName;
        con = DBConnector.getInstance();
    }
    public String getTableName() {
        return tableName;
    }
    public DBConnector getCon() {
        return con;
    }
    public Statement GetStatement() throws SQLException {
        return this.getCon().getStatement();
    }
    public String ProcessSql(ArrayList<String> params, ArrayList<String> conds) {
        String where;
        String cols;

        if (conds == null)
            where = "";
        else
            where = " WHERE " + String.join(" and ", conds);
        if (params == null)
            cols = "*";
        else
            cols = String.join(", ", params);
        String sql = String.format("SELECT %s FROM %s %s", cols, this.getTableName(), where);
        return sql;
    }
    public boolean RecordExists(ArrayList<String> conds) throws SQLException {
        String sql = ProcessSql(null, conds);
        boolean result = false;
        try (Statement statement = this.getCon().getStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)){
                result = resultSet.first();
            }
        }
        return result;
    }
    public int AddRecord(ArrayList<String> params) throws SQLException {
        String sql = String.format("INSERT INTO %s VALUES (%s)",this.getTableName(), String.join(", ", params));
        return this.getCon().executePrepared(sql);
    }
    public int DeleteRecords(ArrayList<String> conds) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE %s",this.getTableName(), String.join(" and ", conds));
        return this.getCon().executePrepared(sql);
    }
    public int UpdateRecords(ArrayList<String> params, ArrayList<String> conds) throws SQLException {
        String sql = String.format("UPDATE %s SET %s WHERE %s",this.getTableName(),
                String.join(", ", params),
                String.join(" and ", conds));
        return this.getCon().executePrepared(sql);
    }
    public static String getMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());

        byte[] byteData = md.digest();

        // convert the byte to hex format
        StringBuilder hexString = new StringBuilder();
        for (byte aByteData : byteData) {
            String hex = Integer.toHexString(0xff & aByteData);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
    // member fields
    private final String tableName;
    private final DBConnector con;
}
