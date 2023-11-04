package DatabaseManagement;

import UserManagement.Administration;
import UserManagement.HealthCareOfficial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministrationTableManager extends TableManager {
    // overriden methdods
    private AdministrationTableManager() {
        super("Administration");
    }

    // member methods

    public ArrayList<Administration> GetRecords(ArrayList<String> params, ArrayList<String> conds, String groupBy, String orderBy) throws SQLException {
        ArrayList<Administration> administration = new ArrayList<>();
        String sql = this.ProcessSql(params, conds, groupBy, orderBy);
        try (Statement statement = this.GetStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String _id = resultSet.getString("id");
                    ArrayList<String> _params = new ArrayList<String>(List.of(new String[]{"name", "id", "password"}));
                    ArrayList<String> _conds = new ArrayList<String>(List.of(new String[]{"id = '" + _id + "'"}));
                    administration.add(new Administration(resultSet,
                            UsersTableManager.getInstance().GetRecord(_params, _conds)));
                }
            }
        }
        return administration;
    }
    public static AdministrationTableManager getInstance() {
        if (instance == null)
            instance = new AdministrationTableManager();
        return instance;
    }
    // member fields
    private static AdministrationTableManager instance;
}
