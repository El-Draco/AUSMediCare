package UI;

import DatabaseManagement.DBConnector;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserInterface ui = new UserInterface();
        try {
            ui.displayMainMenu();
        }
        catch(SQLException | NoSuchAlgorithmException e){
            System.out.println("Exception: " + e.getMessage());
        }
        try {
            if (ui.user != null) {
                ui.displayMenu();
            }
        }
        catch(SQLException e){
            System.out.println("Exception: " + e.getMessage());
        }
        ui.close();
    }
}
