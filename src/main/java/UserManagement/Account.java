package UserManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private String id;
    private String name;
    private String password;
    public Account(String _username, String _password){
        name = _username;
        password = _password;
    }
    public String getName(){
        return (name);
    }
    public String getPassword() {
        return (password);
    }
    public Account(ResultSet resultSet) throws SQLException {
        this.name = resultSet.getString("name");
        this.password = resultSet.getString("password");
        this.id = resultSet.getString("user_id");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Account getInstance(){
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
