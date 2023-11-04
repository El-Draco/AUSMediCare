package DatabaseManagement;

import UserManagement.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfessorsTableManager extends TableManager {
    // overriden methods
    private ProfessorsTableManager() {
        super("Professors");
    }

    // member methods
    public static ProfessorsTableManager getInstance() {
        if (instance == null)
            instance = new ProfessorsTableManager();
        return instance;
    }
    public ArrayList<String> GetRecords(ArrayList<String> params, ArrayList<String> conds) throws SQLException {
        ArrayList<String> professors = new ArrayList<>();
        if (conds == null)
            return professors;
        String sql = this.ProcessSql(params, conds, null, null);
        try (Statement statement = this.GetStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    professors.add(resultSet.getString("professor_id"));
                }
            }
        }
        return professors;
    }
    // member fields
    private static ProfessorsTableManager instance;
}
