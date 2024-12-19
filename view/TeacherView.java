package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the TeacherView class.
 * This class defines the graphical user interface for the teacher dashboard.
 */
public class TeacherView extends JFrame {

    // Button to view personal details.
    private JButton viewPersonalDetailsButton;

    // Button to update personal details.
    private JButton updatePersonalDetailsButton;

    // Button to manage attendance.
    private JButton manageAttendanceButton;

    // Table to display attendance.
    private JTable attendanceTable;

    // Table to display marks.
    private JTable marksTable;

    // Field for entering the subject.
    private JTextField subjectField;

    // Field for entering marks.
    private JTextField marksField;

    // Field for entering the student ID.
    private JTextField studentIdField;

    // Button to save entered marks.
    private JButton saveMarksButton;

    // Field for entering the first name.
    private JTextField firstNameField;

    // Field for entering the last name.
    private JTextField lastNameField;

    // Field for entering the age.
    private JTextField ageField;

    // Field for entering the date of birth.
    private JTextField dobField;

    // Field for entering the address.
    private JTextField addressField;

    // Field for entering the phone number.
    private JTextField phoneField;

    // Field for entering the email.
    private JTextField emailField;

    // Field for entering the course.
    private JTextField courseField;

    // Field for entering the branch.
    private JTextField branchField;

    // Button to save personal details.
    private JButton saveDetailsButton;

    /**
     * Constructs the TeacherView interface.
     * Initializes and arranges all components within the JFrame.
     */
    public TeacherView() {
        setTitle("Teacher Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        viewPersonalDetailsButton = new JButton("View Personal Details");
        updatePersonalDetailsButton = new JButton("Update Personal Details");
        topPanel.add(viewPersonalDetailsButton);
        topPanel.add(updatePersonalDetailsButton);

        // Attendance and Marks Panels
        JPanel attendancePanel = new JPanel(new BorderLayout());
        attendancePanel.setBorder(BorderFactory.createTitledBorder("Manage Attendance"));
        attendanceTable = new JTable();
        attendancePanel.add(new JScrollPane(attendanceTable), BorderLayout.CENTER);
        manageAttendanceButton = new JButton("Update Attendance");
        attendancePanel.add(manageAttendanceButton, BorderLayout.SOUTH);

        JPanel marksPanel = new JPanel(new BorderLayout());
        marksPanel.setBorder(BorderFactory.createTitledBorder("Enter Marks"));
        marksTable = new JTable();
        marksPanel.add(new JScrollPane(marksTable), BorderLayout.CENTER);

        JPanel marksInputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        marksInputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        marksInputPanel.add(studentIdField);
        marksInputPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        marksInputPanel.add(subjectField);
        marksInputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        marksInputPanel.add(marksField);
        saveMarksButton = new JButton("Save Marks");
        marksInputPanel.add(saveMarksButton);
        marksPanel.add(marksInputPanel, BorderLayout.SOUTH);

        // Personal Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        ageField = new JTextField();
        dobField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        courseField = new JTextField();
        branchField = new JTextField();
        saveDetailsButton = new JButton("Save Details");

        detailsPanel.add(new JLabel("First Name:"));
        detailsPanel.add(firstNameField);
        detailsPanel.add(new JLabel("Last Name:"));
        detailsPanel.add(lastNameField);
        detailsPanel.add(new JLabel("Age:"));
        detailsPanel.add(ageField);
        detailsPanel.add(new JLabel("DOB (YYYY-MM-DD):"));
        detailsPanel.add(dobField);
        detailsPanel.add(new JLabel("Address:"));
        detailsPanel.add(addressField);
        detailsPanel.add(new JLabel("Phone:"));
        detailsPanel.add(phoneField);
        detailsPanel.add(new JLabel("Email:"));
        detailsPanel.add(emailField);
        detailsPanel.add(new JLabel("Course:"));
        detailsPanel.add(courseField);
        detailsPanel.add(new JLabel("Branch:"));
        detailsPanel.add(branchField);
        detailsPanel.add(saveDetailsButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, attendancePanel, marksPanel);
        splitPane.setDividerLocation(450);

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.EAST);
    }

    /**
     * Gets the button to view personal details.
     * @return the personal details button
     */
    public JButton getViewPersonalDetailsButton() { return viewPersonalDetailsButton; }

    /**
     * Gets the button to update personal details.
     * @return the update personal details button
     */
    public JButton getUpdatePersonalDetailsButton() { return updatePersonalDetailsButton; }

    /**
     * Gets the button to manage attendance.
     * @return the manage attendance button
     */
    public JButton getManageAttendanceButton() { return manageAttendanceButton; }

    /**
     * Gets the button to enter marks.
     * @return the enter marks button
     */
    public JButton getEnterMarksButton() { return saveMarksButton; }

    /**
     * Gets the table displaying attendance.
     * @return the attendance table
     */
    public JTable getAttendanceTable() { return attendanceTable; }

    /**
     * Gets the table displaying marks.
     * @return the marks table
     */
    public JTable getMarksTable() { return marksTable; }

    /**
     * Gets the subject input field.
     * @return the subject field
     */
    public JTextField getSubjectField() { return subjectField; }

    /**
     * Gets the marks input field.
     * @return the marks field
     */
    public JTextField getMarksField() { return marksField; }

    /**
     * Gets the student ID input field.
     * @return the student ID field
     */
    public JTextField getStudentIdField() { return studentIdField; }

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
     * Gets the date of birth input field.
     * @return the date of birth field
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
     * Gets the button to save personal details.
     * @return the save details button
     */
    public JButton getSaveDetailsButton() { return saveDetailsButton; }
}
