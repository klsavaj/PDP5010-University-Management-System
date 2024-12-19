package controller;

import view.LoginView;
import model.User;
import view.AdminView;
import view.TeacherView;
import view.StudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * Controls the login process and redirects users to their respective dashboards based on roles.
 */
public class LoginController implements ActionListener {

    private LoginView loginView; // Reference to the LoginView instance
    private Connection connection; // Database connection instance

    /**
     * Constructs the LoginController and initializes listeners.
     *
     * @param loginView  the LoginView instance
     * @param connection the database connection
     */
    public LoginController(LoginView loginView, Connection connection) {
        this.loginView = loginView;
        this.connection = connection;
        this.loginView.getLoginButton().addActionListener(this);
    }

    /**
     * Handles the login button action and redirects the user to the appropriate dashboard.
     *
     * @param e the ActionEvent triggered by the login button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginView.getLoginButton()) {
            String email = loginView.getEmailField().getText();
            // For simplicity, password validation is not implemented.
            try {
                User user = User.getUserByEmail(connection, email);
                if (user == null) {
                    JOptionPane.showMessageDialog(loginView, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Redirect based on role
                String role = user.getRole();
                loginView.dispose();
                if (role.equals("admin")) {
                    int adminUserId = user.getUserId();
                    AdminView adminView = new AdminView();
                    new AdminController(adminView, connection, adminUserId);
                    adminView.setVisible(true);
                } else if (role.equals("teacher")) {
                    TeacherView teacherView = new TeacherView();
                    new TeacherController(teacherView, connection, user.getUserId());
                    teacherView.setVisible(true);
                } else if (role.equals("student")) {
                    StudentView studentView = new StudentView();
                    new StudentController(studentView, connection, user.getUserId());
                    studentView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginView, "Unknown role!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(loginView, "Login failed: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
