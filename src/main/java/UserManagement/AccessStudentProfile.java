package UserManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccessStudentProfile {
    ArrayList<Student> accessStudentProfile() throws SQLException;
}
