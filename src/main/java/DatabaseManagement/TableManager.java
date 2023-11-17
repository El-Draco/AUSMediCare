package DatabaseManagement;

import UserManagement.Student;
import UserManagement.User;

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
    public String ProcessSql(ArrayList<String> params, ArrayList<String> conds, String groupBy, String orderBy) {
        String where;
        String cols;
        if (groupBy == null)
            groupBy = "";
        else
            groupBy = " GROUP BY " + groupBy;
        if (orderBy == null)
            orderBy = "";
        else
            orderBy = "ORDER BY " + orderBy;
        if (conds == null)
            where = "";
        else
            where = " WHERE " + String.join(", ", conds);
        if (params == null)
            cols = "*";
        else
            cols = String.join(", ", params);
        String sql = String.format("SELECT %s FROM %s %s %s %s", cols, this.getTableName(), where, groupBy, orderBy);
        return sql;
    }
    public boolean RecordExists(ArrayList<String> conds) throws SQLException {
        String sql = ProcessSql(null, conds, null, null);
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
        String sql = String.format("DELETE FROM %s WHERE %s",this.getTableName(), String.join(", ", conds));
        return this.getCon().executePrepared(sql);
    }
    public int UpdateRecords(ArrayList<String> params, ArrayList<String> conds) throws SQLException {
        String sql = String.format("UPDATE %s SET %s WHERE %s",this.getTableName(),
                String.join(", ", params),
                String.join(", ", conds));
        return this.getCon().executePrepared(sql);
    }
    // member fields
    private final String tableName;
    private final DBConnector con;
}
