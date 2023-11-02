package RequestManagement;

import java.util.Date;

public class Request {
    private int id;
    private String healthcareOfficialId;
    private String studentId;
    private Date date;
    private String form;
    private String type;

    public Request() {}

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
