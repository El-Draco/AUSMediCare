package UI;

import DatabaseManagement.DBConnector;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException , NoSuchAlgorithmException {
        UserInterface ui = new UserInterface();
        ui.displayMainMenu();
        if (ui.user != null) {
            ui.displayMenu();
        }
        ui.close();
    }
}
