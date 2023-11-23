package DatabaseManagement;

import UserManagement.Account;
import UserManagement.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersTableManager extends TableManager {
    // member methods
    private UsersTableManager() {
        super("SYSTEM_Users");
    }
    public Account GetRecord(ArrayList<String> params, ArrayList<String> conds) throws SQLException {
        String sql = this.ProcessSql(params, conds);
        try (Statement statement = this.GetStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.first())
                    return new Account(resultSet);
            }
        }
        return null;
    }
    public static UsersTableManager getInstance() {
        if (instance == null)
            instance = new UsersTableManager();
        return instance;
    }
    // member fields
    private static UsersTableManager instance;
}
