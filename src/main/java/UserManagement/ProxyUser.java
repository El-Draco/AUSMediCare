package UserManagement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProxyUser extends User {
    private User user;
    private String type;
    public ProxyUser(ResultSet resultSet, Account account) throws SQLException{
        super(resultSet,  account);
        try {
            login(); //use return value
        }
        catch (SQLException e){

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
    }

    @Override
    public void updateInfo(User user) throws SQLException {}//no implementation

    public void enterCredentials(){};//no implementation

}
