import db.DatabaseConnection;
import view.LoginView;
import controller.LoginController;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        LoginView loginView = new LoginView();
        new LoginController(loginView, connection);
        loginView.setVisible(true);
    }
}
