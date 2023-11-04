package DatabaseManagement;

import AppointmentManagement.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsTableManager extends TableManager {
    // overriden methdods
    private AppointmentsTableManager() {
        super("Appointments");
    }

    // member methods
    public ArrayList<Appointment> GetRecords(ArrayList<String> params, ArrayList<String> conds, String groupBy, String orderBy) throws SQLException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = this.ProcessSql(params, conds, groupBy, orderBy);
        try (Statement statement = this.GetStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    appointments.add(new Appointment(resultSet));
                }
            }
        }
        return appointments;
    }
    public static AppointmentsTableManager getInstance() {
        if (instance == null)
            instance = new AppointmentsTableManager();
        return instance;
    }
    // member fields
    private static AppointmentsTableManager instance;
}
