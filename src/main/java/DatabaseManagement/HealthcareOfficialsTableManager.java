package DatabaseManagement;

import UserManagement.HealthCareOfficial;
import UserManagement.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HealthcareOfficialsTableManager extends TableManager {
    // overriden methdods
    private HealthcareOfficialsTableManager() {
        super("HealthcareOfficials");
    }

    // member methods
    public ArrayList<HealthCareOfficial> GetRecords(ArrayList<String> params, ArrayList<String> conds, String groupBy, String orderBy) throws SQLException {
        ArrayList<HealthCareOfficial> healthCareOfficials = new ArrayList<>();
        String sql = this.ProcessSql(params, conds, groupBy, orderBy);
        try (Statement statement = this.GetStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String _id = resultSet.getString("id");
                    ArrayList<String> _params = new ArrayList<String>(List.of(new String[]{"name", "id", "password"}));
                    ArrayList<String> _conds = new ArrayList<String>(List.of(new String[]{"id = '" + _id + "'"}));
                    healthCareOfficials.add(new HealthCareOfficial(resultSet,
                            UsersTableManager.getInstance().GetRecord(_params, _conds)));
                }
            }
        }
        return healthCareOfficials;
    }
    public static HealthcareOfficialsTableManager getInstance() {
        if (instance == null)
            instance = new HealthcareOfficialsTableManager();
        return instance;
    }
    // member fields
    private static HealthcareOfficialsTableManager instance;
}
