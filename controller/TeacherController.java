package controller;

import view.TeacherView;
import model.User;
import model.Attendance;
import model.Marks;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;

/**
 * Handles the logic and interactions for the Teacher dashboard view.
 */
public class TeacherController implements ActionListener {

    private TeacherView view; // Reference to the TeacherView instance
    private Connection connection; // Database connection instance
    private int teacherUserId; // ID of the logged-in teacher user

    /**
     * Constructs the TeacherController and initializes listeners.
     *
     * @param view          the TeacherView instance
     * @param connection    the database connection
     * @param teacherUserId the ID of the teacher user
     */
    public TeacherController(TeacherView view, Connection connection, int teacherUserId) {
        this.view = view;
        this.connection = connection;
        this.teacherUserId = teacherUserId;

        view.getViewPersonalDetailsButton().addActionListener(this);
        view.getUpdatePersonalDetailsButton().addActionListener(this);
        view.getManageAttendanceButton().addActionListener(this);
        view.getEnterMarksButton().addActionListener(this);
    }

    /**
     * Handles action events triggered by the TeacherView buttons.
     *
     * @param e the ActionEvent triggered
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == view.getViewPersonalDetailsButton()) {
                viewPersonalDetails();
            } else if (e.getSource() == view.getUpdatePersonalDetailsButton()) {
                updatePersonalDetails();
            } else if (e.getSource() == view.getManageAttendanceButton()) {
                manageAttendance();
            } else if (e.getSource() == view.getEnterMarksButton()) {
                enterMarks();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays the personal details of the teacher.
     *
     * @throws Exception if an error occurs while fetching details
     */
    private void viewPersonalDetails() throws Exception {
        User teacher = User.getUserById(connection, teacherUserId);
        if (teacher != null) {
            view.getFirstNameField().setText(teacher.getFirstName());
            view.getLastNameField().setText(teacher.getLastName());
            view.getAgeField().setText(String.valueOf(teacher.getAge()));
            view.getDobField().setText(teacher.getDob().toString());
            view.getAddressField().setText(teacher.getAddress());
            view.getPhoneField().setText(teacher.getPhone());
            view.getEmailField().setText(teacher.getEmail());
            view.getCourseField().setText(teacher.getCourse());
            view.getBranchField().setText(teacher.getBranch());
        }
    }

    /**
     * Updates the personal details of the teacher.
     *
     * @throws Exception if an error occurs while updating details
     */
    private void updatePersonalDetails() throws Exception {
        User teacher = User.getUserById(connection, teacherUserId);
        teacher.setFirstName(view.getFirstNameField().getText());
        teacher.setLastName(view.getLastNameField().getText());
        teacher.setAge(Integer.parseInt(view.getAgeField().getText()));
        teacher.setDob(Date.valueOf(view.getDobField().getText()));
        teacher.setAddress(view.getAddressField().getText());
        teacher.setPhone(view.getPhoneField().getText());
        teacher.setEmail(view.getEmailField().getText());
        teacher.setCourse(view.getCourseField().getText());
        teacher.setBranch(view.getBranchField().getText());

        teacher.updateUser(connection);
        JOptionPane.showMessageDialog(view, "Details updated!");
    }

    /**
     * Manages the attendance of a student.
     */
    private void manageAttendance() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField userIdField = new JTextField();
        JTextField dateField = new JTextField("YYYY-MM-DD");
        JComboBox<String> statusCombo = new JComboBox<>(new String[] { "present", "absent", "leave" });

        panel.add(new JLabel("Student User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel("Status:"));
        panel.add(statusCombo);

        int result = JOptionPane.showConfirmDialog(view, panel, "Manage Attendance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int sUserId = Integer.parseInt(userIdField.getText().trim());
                String dateStr = dateField.getText().trim();
                String status = (String) statusCombo.getSelectedItem();

                Attendance att = new Attendance();
                att.setUserId(sUserId);
                att.setDate(Date.valueOf(dateStr));
                att.setStatus(status);
                att.markAttendance(connection);
                JOptionPane.showMessageDialog(view, "Attendance marked successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error marking attendance: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Enters marks for a student.
     *
     * @throws Exception if an error occurs while entering marks
     */
    private void enterMarks() throws Exception {
        int studentId = Integer.parseInt(view.getStudentIdField().getText());
        String subject = view.getSubjectField().getText();
        int marksValue = Integer.parseInt(view.getMarksField().getText());
        Marks marks = new Marks();
        marks.setUserId(studentId);
        marks.setSubject(subject);
        marks.setMarks(marksValue);
        marks.enterMarks(connection);
        JOptionPane.showMessageDialog(view, "Marks entered successfully!");
    }
}
