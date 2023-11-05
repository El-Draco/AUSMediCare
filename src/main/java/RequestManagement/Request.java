package RequestManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public abstract class Request {
    private int id;
    private String healthcareOfficialId;
    private String studentId;
    private Date date;
    private String form;
    private String type;
    private String studentEid;

    public Request(int id, String healthcareOfficialId, String studentId, Date date, String form, String type, String studentEid) {
        this.id = id;
        this.healthcareOfficialId = healthcareOfficialId;
        this.studentId = studentId;
        this.date = date;
        this.form = form;
        this.type = type;
        this.studentEid = studentEid;
    }

    public Request(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("request_id");
        this.healthcareOfficialId = resultSet.getString("healthcareofficials_id");
        this.studentId = resultSet.getString("students_id");
        this.date = resultSet.getDate("date");
        this.form = resultSet.getBlob("form").toString();
        this.type = resultSet.getString("request_type");
        this.studentEid = resultSet.getString("students_eid");
    }

    public boolean processRequest() {
        return (true);
    }
    public boolean cancelRequest() {
        return (true);
    };
    public boolean submitRequest() {
        return (true);
    };
}
