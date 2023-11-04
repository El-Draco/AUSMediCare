package DatabaseManagement;

import UserManagement.Student;
import UserManagement.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsTableManager extends TableManager {
    // overriden methdods
    private StudentsTableManager() {
        super("Students");
    }

    // member methods
    public static StudentsTableManager getInstance(String tableName) {
        if (instance == null)
            instance = new StudentsTableManager();
        return instance;
    }
    public ArrayList<Student> GetRecords(ArrayList<String> params, ArrayList<String> conds, String groupBy, String orderBy) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String sql = this.ProcessSql(params, conds, groupBy, orderBy);
        try (Statement statement = this.GetStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String _id = resultSet.getString("id");
                    ArrayList<String> _params = new ArrayList<String>(List.of(new String[]{"name", "id", "password"}));
                    ArrayList<String> _conds = new ArrayList<String>(List.of(new String[]{"id = '" + _id + "'"}));
                    students.add(new Student(resultSet, UsersTableManager.getInstance().GetRecord(_params, _conds)));
                }
            }
        }
        return students;
    }
    // member fields
    private static StudentsTableManager instance;
}
