package UI;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        try {
            ui.displayMenu();
        }
        catch(SQLException e){
            System.out.println("Exception: " + e.getMessage());
        }
        ui.close();
    }
}
