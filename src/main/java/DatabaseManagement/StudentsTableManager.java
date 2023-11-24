package DatabaseManagement;

import UserManagement.HealthCareOfficial;
import UserManagement.Student;

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
    public static StudentsTableManager getInstance() {
        if (instance == null)
            instance = new StudentsTableManager();
        return instance;
    }
    public ArrayList<Student> GetRecords(ArrayList<String> params, ArrayList<String> conds, String groupBy, String orderBy) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String sql = this.ProcessSql(params, conds);
        try (Statement statement = this.GetStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String _id = resultSet.getString("student_id");
                    ArrayList<String> _conds = new ArrayList<String>(List.of(new String[]{"user_id = '" + _id + "'"}));
                    students.add(new Student(
                            // String major, int age, int gender, String eid
                            UsersTableManager.getInstance().GetRecord(null, _conds),
                            resultSet.getString("major"),
                            resultSet.getInt("AGE"),
                            resultSet.getInt("GENDER"),
                            resultSet.getString("EID")
                            ));
                }
            }
        }
        return students;
    }
    // member fields
    private static StudentsTableManager instance;
}
