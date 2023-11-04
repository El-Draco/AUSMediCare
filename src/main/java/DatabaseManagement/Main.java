package DatabaseManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class, args);
        System.out.println("Checking system for user with id b00087311:");
        System.out.println(UsersTableManager.getInstance().GetRecord(null,
                new ArrayList<String>(List.of(new String[]{"user_id = 'b00087311'"}))
        ));
    }
}
