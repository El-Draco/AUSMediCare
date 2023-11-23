package UserManagement;

import DatabaseManagement.StudentsTableManager;
import DatabaseManagement.HealthcareOfficialsTableManager;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private Account account;
    private String email;

    public User(){

    }
    public User(ResultSet resultSet) throws SQLException {
        this.email = resultSet.getString("user_id") + "@aus.edu";
        this.account = new Account(
                resultSet.getString("user_id"),
                resultSet.getString("user_password"),
                resultSet.getString("username"));
    }

    public User(ResultSet resultSet, Account account) throws SQLException {
        this.account = account;
        this.email = resultSet.getString("user_id") + "@aus.edu";
    }

    public User(Account account, String name) {
        this.account = account;
        this.email = account.getId() + "@aus.edu";
    }

    public static User login(String id) throws SQLException, NoSuchAlgorithmException {
        if(HealthcareOfficialsTableManager.getInstance().RecordExists(new ArrayList<String>(
                List.of(new String[]{"official_id = '" + id +"'"})))){
            return HealthcareOfficialsTableManager.getInstance().GetRecords(null,
                    new ArrayList<String>(List.of(new String[]{"official_id = '" + id +"'"})), null
                    ,null).get(0);
        }
        else if(StudentsTableManager.getInstance().RecordExists(new ArrayList<String>(
                List.of(new String[]{"student_id = '" + id +"'"})))){
            return StudentsTableManager.getInstance().GetRecords(null,
                    new ArrayList<String>(List.of(new String[]{"student_id = '" + id +"'"})), null
                    ,null).get(0);

        }
        return null; // Should never occur since we call login in user after confirming existence
    }

    public User(Account account) {
        this.account = account;
        this.email = account.getId() + "@aus.edu";
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

