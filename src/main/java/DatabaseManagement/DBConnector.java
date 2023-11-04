package DatabaseManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBConnector {
    private static DBConnector instance = null;
    final String DBURL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    final String DBUSER = "b00087311";
    final String DBPASS = "b00087311";

    Connection con;
    Statement statement;
    PreparedStatement prepStatement;
    ResultSet rs;

    private DBConnector() {
        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Connect to Oracle Database
            con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("SQL Error - Retreiving usename/password.");
        }
    }

    public static DBConnector getInstance(){
        if (instance == null)
            instance = new DBConnector();
        return instance;
    }

    // returns records selected
    public Statement getStatement() throws SQLException {
        // make the result set scrollable forward/backward updatable
        statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        // populate valid mgr numbers
        return statement;
    }

    // update, insert (return number of records affected
    public int executePrepared(String strSQL) throws SQLException {
        prepStatement = con.prepareStatement(strSQL);
        int result = prepStatement.executeUpdate();
        prepStatement.close();
        return result;
    }
}
