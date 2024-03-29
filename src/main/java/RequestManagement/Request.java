package RequestManagement;

import DatabaseManagement.RequestsTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Request {
    private int id;
    private String healthcareOfficialId;
    private String studentId;
    private Date date;
    private String form;
    private String type;
    private String studentEid;

    private int status; //0 = nothing done yet, 1 = approved, 2 = declined, 3 = cancelled

    public int getId() {
        return id;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){ this.status=status; }

    public void setId(int id) {
        this.id = id;
    }

    public String getHealthcareOfficialId() {
        return healthcareOfficialId;
    }

    public void setHealthcareOfficialId(String healthcareOfficialId) {
        this.healthcareOfficialId = healthcareOfficialId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudentEid() {
        return studentEid;
    }

    public void setStudentEid(String studentEid) {
        this.studentEid = studentEid;
    }

    public Request(int id, String studentId, Date date, String form, String type, int status, String studentEid) {
        this.id = id;
        //this.healthcareOfficialId = healthcareOfficialId;
        this.studentId = studentId;
        this.date = date;
        this.form = form;
        this.type = type;
        this.status = status;
        this.studentEid = studentEid;
    }

    public Request(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("request_id");
        this.healthcareOfficialId = resultSet.getString("healthcareofficials_id");
        this.studentId = resultSet.getString("students_id");
        this.date = resultSet.getDate("request_date");
        this.form = resultSet.getString("request_form");
        this.type = resultSet.getString("request_type");
        this.status = resultSet.getInt("request_status");
        this.studentEid = resultSet.getString("students_eid");
    }

    public void processRequest(int status) throws SQLException {
        RequestsTableManager.getInstance().UpdateRecords(
                new ArrayList<String>(List.of(new String[]{"request_status = " + status})),
                new ArrayList<String>(List.of(new String[]{
                        "request_id = "+ id,
                        "students_id = '"+studentId+"'",
                        "healthcareofficials_id = '" + healthcareOfficialId + "'"})));
    }

    public void cancelRequest() throws SQLException {
        processRequest(3);
    }
    public void submitRequest() throws SQLException {
        String pattern = "dd/MMM/yyyy";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date according to the chosen pattern
        DateFormat df = new SimpleDateFormat(pattern);
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String dateAsString = df.format(date);

        RequestsTableManager.getInstance().AddRecord(
                new ArrayList<String>(List.of(new String[]{
                        "'" + id +"'",
                        "'b00087311'",
                        "'" + studentId+"'",
                        "'" + dateAsString + "'",
                        "'" + form + "'",
                        "'" + type + "'",
                        status + "",
                        "'" + studentEid + "'"
                        })));
    }
    @Override
    public String toString(){
        return "Request ID: " + id+ ", Status: "+ status
                + ", Type: " + this.type + ", Date: " + this.date
                + "\nForm: " + this.form;
    }


}
