package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Marks model class.
 * This class manages marks data, including entering and viewing marks.
 */
public class Marks {

    // Unique ID for the marks record.
    private int markId;

    // User ID associated with the marks record.
    private int userId;

    // Subject of the marks.
    private String subject;

    // Marks obtained by the user.
    private int marks;

    /**
     * Gets the unique ID of the marks record.
     * @return the mark ID
     */
    public int getMarkId() { return markId; }

    /**
     * Sets the unique ID of the marks record.
     * @param markId the mark ID to set
     */
    public void setMarkId(int markId) { this.markId = markId; }

    /**
     * Gets the user ID associated with the marks record.
     * @return the user ID
     */
    public int getUserId() { return userId; }

    /**
     * Sets the user ID associated with the marks record.
     * @param userId the user ID to set
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the subject of the marks.
     * @return the subject
     */
    public String getSubject() { return subject; }

    /**
     * Sets the subject of the marks.
     * @param subject the subject to set
     */
    public void setSubject(String subject) { this.subject = subject; }

    /**
     * Gets the marks obtained by the user.
     * @return the marks
     */
    public int getMarks() { return marks; }

    /**
     * Sets the marks obtained by the user.
     * @param marks the marks to set
     */
    public void setMarks(int marks) { this.marks = marks; }

    /**
     * Inserts a new marks record into the database.
     * @param connection the database connection
     * @throws SQLException if a database error occurs
     */
    public void enterMarks(Connection connection) throws SQLException {
        String query = "INSERT INTO marks (user_id, subject, marks) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, subject);
            stmt.setInt(3, marks);
            stmt.executeUpdate();
        }
    }

    /**
     * Fetches all marks records for a specified user.
     * @param connection the database connection
     * @param userId the user's ID
     * @return a list of Marks objects representing the user's marks records
     * @throws SQLException if a database error occurs
     */
    public static List<Marks> viewAllMarksForUser(Connection connection, int userId) throws SQLException {
        List<Marks> marksList = new ArrayList<>();
        String query = "SELECT * FROM marks WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Marks m = new Marks();
                m.setMarkId(rs.getInt("mark_id"));
                m.setUserId(rs.getInt("user_id"));
                m.setSubject(rs.getString("subject"));
                m.setMarks(rs.getInt("marks"));
                marksList.add(m);
            }
        }
        return marksList;
    }
}
