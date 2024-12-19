package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the LoginView class.
 * This class defines the graphical user interface for user login.
 */
public class LoginView extends JFrame {

    // Input field for user email.
    private JTextField emailField;

    // Password field for user password.
    private JPasswordField passwordField;

    // Button to initiate the login process.
    private JButton loginButton;

    /**
     * Constructs the LoginView interface.
     * Initializes and arranges all components within the JFrame.
     */
    public LoginView() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(new JLabel(""));
        panel.add(loginButton);

        add(panel);
    }

    /**
     * Gets the email input field.
     * @return the email input field
     */
    public JTextField getEmailField() { return emailField; }

    /**
     * Gets the password input field.
     * @return the password input field
     */
    public JPasswordField getPasswordField() { return passwordField; }

    /**
     * Gets the login button.
     * @return the login button
     */
    public JButton getLoginButton() { return loginButton; }
}
