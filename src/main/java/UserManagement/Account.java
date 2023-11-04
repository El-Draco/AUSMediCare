package UserManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private String id;
    private String username;
    private String password;
    Account(String _username, String _password){
        //Optional: @Shafiay set id from database or else remove this data member
        username = _username;
        password = _password;
    }
    public String getUsername(){
        return (username);
    }
    public String getPassword() {
        return (password);
    }
    public Account(ResultSet resultSet) throws SQLException {
        this.username = resultSet.getString("name");
        this.password = resultSet.getString("password");
        this.id = resultSet.getString("user_id");
    }
}
