package controller;

import view.StudentView;
import model.User;
import model.Attendance;
import model.Marks;
import model.Fees;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * Handles the logic and interactions for the Student dashboard view.
 */
public class StudentController implements ActionListener {

    private StudentView view; // Reference to the StudentView instance
    private Connection connection; // Database connection instance
    private int studentUserId; // ID of the logged-in student user

    /**
     * Constructs the StudentController and initializes listeners.
     *
     * @param view           the StudentView instance
     * @param connection     the database connection
     * @param studentUserId  the ID of the student user
     */
    public StudentController(StudentView view, Connection connection, int studentUserId) {
        this.view = view;
        this.connection = connection;
        this.studentUserId = studentUserId;
        view.getViewPersonalDetailsButton().addActionListener(this);
        view.getViewMarksButton().addActionListener(this);
        view.getViewAttendanceButton().addActionListener(this);
        view.getViewFeesButton().addActionListener(this);
    }

    /**
     * Handles action events triggered by the StudentView buttons.
     *
     * @param e the ActionEvent triggered
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == view.getViewPersonalDetailsButton()) {
                viewPersonalDetails();
            } else if (e.getSource() == view.getViewMarksButton()) {
                viewMarks();
            } else if (e.getSource() == view.getViewAttendanceButton()) {
                viewAttendance();
            } else if (e.getSource() == view.getViewFeesButton()) {
                viewFees();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays the personal details of the student.
     *
     * @throws Exception if an error occurs while fetching details
     */
    private void viewPersonalDetails() throws Exception {
        User student = User.getUserById(connection, studentUserId);
        if (student != null) {
            view.setPersonalDetails(
                student.getFirstName() + " " + student.getLastName(),
                student.getAge(),
                student.getDob().toString(),
                student.getAddress(),
                student.getPhone(),
                student.getEmail(),
                student.getCourse(),
                student.getBranch()
            );
        }
    }

    /**
     * Displays the marks of the student in a dialog.
     *
     * @throws Exception if an error occurs while fetching marks
     */
    private void viewMarks() throws Exception {
        try {
            List<Marks> marksList = Marks.viewAllMarksForUser(connection, studentUserId);
            showMarksDialog(marksList);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error fetching marks: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows a dialog displaying the marks of the student.
     *
     * @param marksList the list of marks to display
     */
    private void showMarksDialog(List<Marks> marksList) {
        String[] columns = {"Subject", "Marks"};
        Object[][] data = new Object[marksList.size()][2];
        for (int i = 0; i < marksList.size(); i++) {
            data[i][0] = marksList.get(i).getSubject();
            data[i][1] = marksList.get(i).getMarks();
        }
        JTable table = new JTable(data, columns);
        JOptionPane.showMessageDialog(view, new JScrollPane(table), "Your Marks", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays the attendance of the student in a dialog.
     *
     * @throws Exception if an error occurs while fetching attendance
     */
    private void viewAttendance() throws Exception {
        try {
            List<Attendance> attList = Attendance.viewAttendance(connection, studentUserId);
            String[] columns = {"Date", "Status"};
            Object[][] data = new Object[attList.size()][2];
            for (int i = 0; i < attList.size(); i++) {
                data[i][0] = attList.get(i).getDate().toString();
                data[i][1] = attList.get(i).getStatus();
            }
            JTable table = new JTable(data, columns);
            JOptionPane.showMessageDialog(view, new JScrollPane(table), "Your Attendance", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error fetching attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays the fees details of the student in a dialog.
     *
     * @throws Exception if an error occurs while fetching fees
     */
    private void viewFees() throws Exception {
        try {
            Fees fees = Fees.viewFees(connection, studentUserId);
            if (fees != null) {
                String msg = "Total Fees: " + fees.getTotalFees() + "\nPaid: " + fees.getPaid() + "\nDue: " + fees.getDue();
                JOptionPane.showMessageDialog(view, msg, "Your Fees", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "No fees found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error fetching fees: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
