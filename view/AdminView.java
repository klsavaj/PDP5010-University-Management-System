package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the AdminView class.
 * This class defines the graphical user interface for the admin dashboard.
 */
public class AdminView extends JFrame {

    // Search input field.
    private JTextField searchField;

    // Button to initiate a user search.
    private JButton searchButton;

    // Table to display user details.
    private JTable usersTable;

    // Button to add a new user.
    private JButton addUserButton;

    // Button to delete a user.
    private JButton deleteUserButton;

    // Button to manage attendance records.
    private JButton manageAttendanceButton;

    // Button to view or update user details.
    private JButton updateDetailsButton;

    // Button to send a message.
    private JButton sendMessageButton;

    // Area to compose and view messages.
    private JTextArea messageArea;

    // Fields for adding or updating user details.
    private JTextField firstNameField, lastNameField, ageField, dobField, addressField, phoneField, emailField, roleField, courseField, branchField;

    // Button to save user details.
    private JButton saveUserButton;

    /**
     * Constructs the AdminView interface.
     * Initializes and arranges all components within the JFrame.
     */
    public AdminView() {
        setTitle("Admin Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Top panel for search functionality.
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        topPanel.add(new JLabel("Search User:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        // Center panel: Users table and actions.
        usersTable = new JTable();
        JScrollPane tableScroll = new JScrollPane(usersTable);

        addUserButton = new JButton("Add User");
        deleteUserButton = new JButton("Delete User");
        manageAttendanceButton = new JButton("Manage Attendance");
        updateDetailsButton = new JButton("View/Update Details");
        sendMessageButton = new JButton("Send Message");

        JPanel actionPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        actionPanel.add(addUserButton);
        actionPanel.add(deleteUserButton);
        actionPanel.add(manageAttendanceButton);
        actionPanel.add(updateDetailsButton);
        actionPanel.add(sendMessageButton);

        // User detail panel.
        JPanel userDetailsPanel = new JPanel(new GridLayout(11, 2, 5, 5));
        userDetailsPanel.setBorder(BorderFactory.createTitledBorder("User Details"));
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        ageField = new JTextField();
        dobField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        roleField = new JTextField();
        courseField = new JTextField();
        branchField = new JTextField();
        saveUserButton = new JButton("Save User");

        userDetailsPanel.add(new JLabel("First Name:"));
        userDetailsPanel.add(firstNameField);
        userDetailsPanel.add(new JLabel("Last Name:"));
        userDetailsPanel.add(lastNameField);
        userDetailsPanel.add(new JLabel("Age:"));
        userDetailsPanel.add(ageField);
        userDetailsPanel.add(new JLabel("DOB (YYYY-MM-DD):"));
        userDetailsPanel.add(dobField);
        userDetailsPanel.add(new JLabel("Address:"));
        userDetailsPanel.add(addressField);
        userDetailsPanel.add(new JLabel("Phone:"));
        userDetailsPanel.add(phoneField);
        userDetailsPanel.add(new JLabel("Email:"));
        userDetailsPanel.add(emailField);
        userDetailsPanel.add(new JLabel("Role:"));
        userDetailsPanel.add(roleField);
        userDetailsPanel.add(new JLabel("Course:"));
        userDetailsPanel.add(courseField);
        userDetailsPanel.add(new JLabel("Branch:"));
        userDetailsPanel.add(branchField);
        userDetailsPanel.add(saveUserButton);

        // Message panel.
        JPanel messagePanel = new JPanel(new BorderLayout());
        messageArea = new JTextArea(5, 30);
        messagePanel.setBorder(BorderFactory.createTitledBorder("Send Message"));
        messagePanel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // Combine center and detail panels.
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableScroll, BorderLayout.CENTER);
        centerPanel.add(actionPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPanel, userDetailsPanel);
        splitPane.setDividerLocation(600);

        // Add components to frame.
        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    /**
     * Gets the search field.
     * @return the search input field
     */
    public JTextField getSearchField() { return searchField; }

    /**
     * Gets the search button.
     * @return the search button
     */
    public JButton getSearchButton() { return searchButton; }

    /**
     * Gets the users table.
     * @return the table displaying user details
     */
    public JTable getUsersTable() { return usersTable; }

    /**
     * Gets the add user button.
     * @return the add user button
     */
    public JButton getAddUserButton() { return addUserButton; }

    /**
     * Gets the delete user button.
     * @return the delete user button
     */
    public JButton getDeleteUserButton() { return deleteUserButton; }

    /**
     * Gets the manage attendance button.
     * @return the manage attendance button
     */
    public JButton getManageAttendanceButton() { return manageAttendanceButton; }

    /**
     * Gets the update details button.
     * @return the update details button
     */
    public JButton getUpdateDetailsButton() { return updateDetailsButton; }

    /**
     * Gets the send message button.
     * @return the send message button
     */
    public JButton getSendMessageButton() { return sendMessageButton; }

    /**
     * Gets the message area.
     * @return the text area for messages
     */
    public JTextArea getMessageArea() { return messageArea; }

    /**
     * Gets the first name input field.
     * @return the first name field
     */
    public JTextField getFirstNameField() { return firstNameField; }

    /**
     * Gets the last name input field.
     * @return the last name field
     */
    public JTextField getLastNameField() { return lastNameField; }

    /**
     * Gets the age input field.
     * @return the age field
     */
    public JTextField getAgeField() { return ageField; }

    /**
     * Gets the DOB input field.
     * @return the DOB field
     */
    public JTextField getDobField() { return dobField; }

    /**
     * Gets the address input field.
     * @return the address field
     */
    public JTextField getAddressField() { return addressField; }

    /**
     * Gets the phone number input field.
     * @return the phone field
     */
    public JTextField getPhoneField() { return phoneField; }

    /**
     * Gets the email input field.
     * @return the email field
     */
    public JTextField getEmailField() { return emailField; }

    /**
     * Gets the role input field.
     * @return the role field
     */
    public JTextField getRoleField() { return roleField; }

    /**
     * Gets the course input field.
     * @return the course field
     */
    public JTextField getCourseField() { return courseField; }

    /**
     * Gets the branch input field.
     * @return the branch field
     */
    public JTextField getBranchField() { return branchField; }

    /**
     * Gets the save user button.
     * @return the save user button
     */
    public JButton getSaveUserButton() { return saveUserButton; }
}
