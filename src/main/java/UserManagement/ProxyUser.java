package UserManagement;

import DatabaseManagement.ProfessorsTableManager;
import DatabaseManagement.StudentsTableManager;
import DatabaseManagement.UsersTableManager;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProxyUser extends User {
    private User user;
    public ProxyUser() {

    }


        //@TODO:
        //1. Retrieve account from DB
        //2. Decide whether we want to use the resultSet to provide to
        //  the constructors. if so, retrieve that and pass along...

//        ResultSet resultSet = new ResultSet(); //retrieve the resultSet var
//        Account account = new Account();       // get their account
//        if (type == "Student")
//           user = new Student(resultSet, account);
//        else if (type == "Administration")
//             user = new Administration(resultSet, account);
//        else if (type == "HealthCareOfficial")
//             user = new HealthCareOfficial(resultSet, account);

    public ProxyUser(String id, String password) throws SQLException {

    }


    public User login(String id, String password) throws SQLException, NoSuchAlgorithmException {
        user = null;
        // if the user's credentials are correct
        if (UsersTableManager.getInstance().RecordExists(new ArrayList<String>(List.of(new String[]{
                "user_id = '" + id +"'",
                "password = '" + UsersTableManager.getMD5Hash(password) +"'"}))))
        {
            // retrieve the user
            user = User.login(id);
        }
        // return the user
        return user;
    }

    @Override
    public void updateInfo(User user) throws SQLException {}//no implementation

    public void enterCredentials(){};//no implementation

}
