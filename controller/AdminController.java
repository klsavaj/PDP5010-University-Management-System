package controller;

import view.AdminView;
import model.User;
import model.Attendance;
import model.Messages;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles the logic and interactions for the Admin dashboard view.
 */
public class AdminController implements ActionListener {

    private AdminView view; // Reference to the AdminView instance
    private Connection connection; // Database connection instance
    private int adminUserId; // Admin user's ID

    /**
     * Constructs the AdminController and initializes listeners.
     *
     * @param view        the AdminView instance
     * @param connection  the database connection
     * @param adminUserId the ID of the admin user
     */
    public AdminController(AdminView view, Connection connection, int adminUserId) {
        this.view = view;
        this.connection = connection;
        this.adminUserId = adminUserId;
        
        // Add action listeners
        view.getAddUserButton().addActionListener(this);
        view.getDeleteUserButton().addActionListener(this);
        view.getManageAttendanceButton().addActionListener(this);
        view.getUpdateDetailsButton().addActionListener(this);
        view.getSendMessageButton().addActionListener(this);
        view.getSaveUserButton().addActionListener(this);
        view.getSearchButton().addActionListener(this);
        
        refreshUserTable();
    }

    /**
     * Handles action events triggered by the AdminView buttons.
     *
     * @param e the ActionEvent triggered
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getAddUserButton()) {
            clearUserFields();
        } else if (e.getSource() == view.getSaveUserButton()) {
            addOrUpdateUser();
        } else if (e.getSource() == view.getDeleteUserButton()) {
            deleteUser();
        } else if (e.getSource() == view.getManageAttendanceButton()) {
            manageAttendance();
        } else if (e.getSource() == view.getUpdateDetailsButton()) {
            populateUserFieldsForUpdate();
        } else if (e.getSource() == view.getSendMessageButton()) {
            sendMessage();
        }
    }

    /**
     * Adds or updates user information in the database.
     */
    private void addOrUpdateUser() {
        try {
            User user = new User();
            user.setUserId(adminUserId);
            user.setFirstName(view.getFirstNameField().getText());
            user.setLastName(view.getLastNameField().getText());
            user.setAge(Integer.parseInt(view.getAgeField().getText()));
            user.setDob(Date.valueOf(view.getDobField().getText()));
            user.setAddress(view.getAddressField().getText());
            user.setPhone(view.getPhoneField().getText());
            user.setEmail(view.getEmailField().getText());
            user.setRole(view.getRoleField().getText());
            user.setCourse(view.getCourseField().getText());
            user.setBranch(view.getBranchField().getText());

            if (user.getUserId() == 0) {
                user.addUser(connection);
                JOptionPane.showMessageDialog(view, "User added successfully!");
            } else {
                user.updateUser(connection);
                JOptionPane.showMessageDialog(view, "User updated successfully!");
            }
            refreshUserTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error adding/updating user: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populates the user table with data from the database.
     *
     * @param rs the ResultSet containing user data
     * @throws SQLException if an SQL error occurs
     */
    private void populateUserTable(ResultSet rs) throws SQLException {
        String[] columns = { "User ID", "First Name", "Last Name", "Role", "Email" };
        java.util.List<Object[]> rows = new java.util.ArrayList<>();

        while (rs.next()) {
            Object[] row = new Object[5];
            row[0] = rs.getInt("user_id");
            row[1] = rs.getString("first_name");
            row[2] = rs.getString("last_name");
            row[3] = rs.getString("role");
            row[4] = rs.getString("email");
            rows.add(row);
        }

        Object[][] data = rows.toArray(new Object[0][]);
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getUsersTable().setModel(model);
    }

    /**
     * Deletes the selected user from the database.
     */
    private void deleteUser() {
        int selectedRow = view.getUsersTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Select a user to delete.");
            return;
        }
        int userId = getUserIdFromTable(selectedRow);
        if (userId <= 0) {
            JOptionPane.showMessageDialog(view, "Invalid user selected.");
            return;
        }

        try {
            User user = new User();
            user.setUserId(userId);
            user.deleteUser(connection);
            JOptionPane.showMessageDialog(view, "User deleted successfully!");
            refreshUserTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error deleting user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Manages attendance for a selected user.
     */
    private void manageAttendance() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        try {
            ResultSet rs = User.getAllUsers(connection);
            java.util.List<String> userList = new java.util.ArrayList<>();
            java.util.Map<String, Integer> userMap = new java.util.HashMap<>();

            while (rs.next()) {
                int uid = rs.getInt("user_id");
                String name = rs.getString("first_name") + " " + rs.getString("last_name") + " (ID:" + uid + ")";
                userList.add(name);
                userMap.put(name, uid);
            }

            JComboBox<String> userCombo = new JComboBox<>(userList.toArray(new String[0]));
            JTextField dateField = new JTextField("YYYY-MM-DD");
            JComboBox<String> statusCombo = new JComboBox<>(new String[]{"present", "absent", "leave"});

            panel.add(new JLabel("Select User:"));
            panel.add(userCombo);
            panel.add(new JLabel("Date (YYYY-MM-DD):"));
            panel.add(dateField);
            panel.add(new JLabel("Status:"));
            panel.add(statusCombo);

            int result = JOptionPane.showConfirmDialog(view, panel, "Manage Attendance", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                int selectedUserId = userMap.get((String) userCombo.getSelectedItem());
                String dateStr = dateField.getText().trim();
                String status = (String) statusCombo.getSelectedItem();

                Attendance att = new Attendance();
                att.setUserId(selectedUserId);
                att.setDate(java.sql.Date.valueOf(dateStr));
                att.setStatus(status);
                att.markAttendance(connection);

                JOptionPane.showMessageDialog(view, "Attendance marked/updated successfully!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error managing attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sends a message to a specified user.
     */
    private void sendMessage() {
        String messageText = view.getMessageArea().getText().trim();
        if (messageText.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Message cannot be empty.");
            return;
        }
        try {
            Messages msg = new Messages();
            msg.setSenderId(adminUserId);
            msg.setReceiverId(2); // Example receiver ID
            msg.setMessage(messageText);
            msg.sendMessage(connection);

            JOptionPane.showMessageDialog(view, "Message sent successfully!");
            view.getMessageArea().setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error sending message: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populates the fields with the admin user's details for updating.
     */
    private void populateUserFieldsForUpdate() {
        try {
            User adminUser = User.getUserById(connection, adminUserId);
            if (adminUser != null) {
                view.getFirstNameField().setText(adminUser.getFirstName());
                view.getLastNameField().setText(adminUser.getLastName());
                view.getAgeField().setText(String.valueOf(adminUser.getAge()));
                view.getDobField().setText(adminUser.getDob().toString());
                view.getAddressField().setText(adminUser.getAddress());
                view.getPhoneField().setText(adminUser.getPhone());
                view.getEmailField().setText(adminUser.getEmail());
                view.getRoleField().setText(adminUser.getRole());
                view.getCourseField().setText(adminUser.getCourse());
                view.getBranchField().setText(adminUser.getBranch());
            } else {
                JOptionPane.showMessageDialog(view, "Admin user not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error fetching admin details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Refreshes the user table to show updated data.
     */
    private void refreshUserTable() {
        try {
            ResultSet rs = User.getAllUsers(connection);
            populateUserTable(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error loading users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Clears the fields for entering user information.
     */
    private void clearUserFields() {
        view.getFirstNameField().setText("");
        view.getLastNameField().setText("");
        view.getAgeField().setText("");
        view.getDobField().setText("");
        view.getAddressField().setText("");
        view.getPhoneField().setText("");
        view.getEmailField().setText("");
        view.getRoleField().setText("");
        view.getCourseField().setText("");
        view.getBranchField().setText("");
    }

    /**
     * Retrieves the user ID from the selected table row.
     *
     * @param selectedRow the selected row in the table
     * @return the user ID as an integer
     */
    private int getUserIdFromTable(int selectedRow) {
        Object value = view.getUsersTable().getValueAt(selectedRow, 0);
        if (value instanceof Integer) {
            return (Integer) value;
        } else {
            try {
                return Integer.parseInt(value.toString());
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }
}
