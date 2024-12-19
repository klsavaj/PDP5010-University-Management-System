package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the StudentView class.
 * This class defines the graphical user interface for the student dashboard.
 */
public class StudentView extends JFrame {

    // Button to view personal details.
    private JButton viewPersonalDetailsButton;

    // Button to view marks.
    private JButton viewMarksButton;

    // Button to view attendance.
    private JButton viewAttendanceButton;

    // Button to view fees.
    private JButton viewFeesButton;

    // Table to display marks.
    private JTable marksTable;

    // Table to display attendance.
    private JTable attendanceTable;

    // Table to display fees.
    private JTable feesTable;

    // Label to display the name of the student.
    private JLabel nameLabel;

    // Label to display the age of the student.
    private JLabel ageLabel;

    // Label to display the date of birth of the student.
    private JLabel dobLabel;

    // Label to display the address of the student.
    private JLabel addressLabel;

    // Label to display the phone number of the student.
    private JLabel phoneLabel;

    // Label to display the email of the student.
    private JLabel emailLabel;

    // Label to display the course of the student.
    private JLabel courseLabel;

    // Label to display the branch of the student.
    private JLabel branchLabel;

    /**
     * Constructs the StudentView interface.
     * Initializes and arranges all components within the JFrame.
     */
    public StudentView() {
        setTitle("Student Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(1, 4));
        viewPersonalDetailsButton = new JButton("View Personal Details");
        viewMarksButton = new JButton("View Marks");
        viewAttendanceButton = new JButton("View Attendance");
        viewFeesButton = new JButton("View Fees");

        topPanel.add(viewPersonalDetailsButton);
        topPanel.add(viewMarksButton);
        topPanel.add(viewAttendanceButton);
        topPanel.add(viewFeesButton);

        // Personal details panel
        JPanel personalDetailsPanel = new JPanel(new GridLayout(8, 2));
        personalDetailsPanel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        nameLabel = new JLabel();
        ageLabel = new JLabel();
        dobLabel = new JLabel();
        addressLabel = new JLabel();
        phoneLabel = new JLabel();
        emailLabel = new JLabel();
        courseLabel = new JLabel();
        branchLabel = new JLabel();

        personalDetailsPanel.add(new JLabel("Name:"));
        personalDetailsPanel.add(nameLabel);
        personalDetailsPanel.add(new JLabel("Age:"));
        personalDetailsPanel.add(ageLabel);
        personalDetailsPanel.add(new JLabel("DOB:"));
        personalDetailsPanel.add(dobLabel);
        personalDetailsPanel.add(new JLabel("Address:"));
        personalDetailsPanel.add(addressLabel);
        personalDetailsPanel.add(new JLabel("Phone:"));
        personalDetailsPanel.add(phoneLabel);
        personalDetailsPanel.add(new JLabel("Email:"));
        personalDetailsPanel.add(emailLabel);
        personalDetailsPanel.add(new JLabel("Course:"));
        personalDetailsPanel.add(courseLabel);
        personalDetailsPanel.add(new JLabel("Branch:"));
        personalDetailsPanel.add(branchLabel);

        // Marks table
        marksTable = new JTable();
        JPanel marksPanel = new JPanel(new BorderLayout());
        marksPanel.setBorder(BorderFactory.createTitledBorder("Marks"));
        marksPanel.add(new JScrollPane(marksTable), BorderLayout.CENTER);

        // Attendance table
        attendanceTable = new JTable();
        JPanel attendancePanel = new JPanel(new BorderLayout());
        attendancePanel.setBorder(BorderFactory.createTitledBorder("Attendance"));
        attendancePanel.add(new JScrollPane(attendanceTable), BorderLayout.CENTER);

        // Fees table
        feesTable = new JTable();
        JPanel feesPanel = new JPanel(new BorderLayout());
        feesPanel.setBorder(BorderFactory.createTitledBorder("Fees"));
        feesPanel.add(new JScrollPane(feesTable), BorderLayout.CENTER);

        JSplitPane bottomSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, attendancePanel, feesPanel);
        bottomSplit.setDividerLocation(450);

        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, personalDetailsPanel, marksPanel);
        mainSplit.setDividerLocation(200);

        // Combine everything
        add(topPanel, BorderLayout.NORTH);
        add(mainSplit, BorderLayout.CENTER);
        add(bottomSplit, BorderLayout.SOUTH);
    }

    /**
     * Updates the personal details labels.
     * @param name the name of the student
     * @param age the age of the student
     * @param dob the date of birth of the student
     * @param address the address of the student
     * @param phone the phone number of the student
     * @param email the email of the student
     * @param course the course of the student
     * @param branch the branch of the student
     */
    public void setPersonalDetails(String name, int age, String dob, String address, String phone, String email, String course, String branch) {
        nameLabel.setText(name);
        ageLabel.setText(String.valueOf(age));
        dobLabel.setText(dob);
        addressLabel.setText(address);
        phoneLabel.setText(phone);
        emailLabel.setText(email);
        courseLabel.setText(course);
        branchLabel.setText(branch);
    }

    /**
     * Gets the button to view personal details.
     * @return the personal details button
     */
    public JButton getViewPersonalDetailsButton() { return viewPersonalDetailsButton; }

    /**
     * Gets the button to view marks.
     * @return the marks button
     */
    public JButton getViewMarksButton() { return viewMarksButton; }

    /**
     * Gets the button to view attendance.
     * @return the attendance button
     */
    public JButton getViewAttendanceButton() { return viewAttendanceButton; }

    /**
     * Gets the button to view fees.
     * @return the fees button
     */
    public JButton getViewFeesButton() { return viewFeesButton; }

    /**
     * Gets the table displaying marks.
     * @return the marks table
     */
    public JTable getMarksTable() { return marksTable; }

    /**
     * Gets the table displaying attendance.
     * @return the attendance table
     */
    public JTable getAttendanceTable() { return attendanceTable; }

    /**
     * Gets the table displaying fees.
     * @return the fees table
     */
    public JTable getFeesTable() { return feesTable; }
}
