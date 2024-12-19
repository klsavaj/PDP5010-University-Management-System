package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Attendance model class.
 * This class manages attendance data, including marking and viewing attendance.
 */
public class Attendance {

    /**
     * The unique ID of the attendance record.
     */
    private int attendanceId;

    /**
     * The ID of the user associated with the attendance record.
     */
    private int userId;

    /**
     * The date of the attendance record.
     */
    private Date date;

    /**
     * The status of the attendance (e.g., 'present', 'absent', 'leave').
     */
    private String status;  // 'present', 'absent', 'leave'

    /**
     * Gets the unique ID of the attendance record.
     * 
     * @return the attendance ID.
     */
    public int getAttendanceId() { return attendanceId; }

    /**
     * Sets the unique ID of the attendance record.
     * 
     * @param attendanceId the attendance ID to set.
     */
    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }

    /**
     * Gets the user ID associated with the attendance record.
     * 
     * @return the user ID.
     */
    public int getUserId() { return userId; }

    /**
     * Sets the user ID associated with the attendance record.
     * 
     * @param userId the user ID to set.
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the date of the attendance record.
     * 
     * @return the date of the attendance.
     */
    public Date getDate() { return date; }

    /**
     * Sets the date of the attendance record.
     * 
     * @param date the date to set.
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * Gets the status of the attendance (e.g., 'present', 'absent', 'leave').
     * 
     * @return the attendance status.
     */
    public String getStatus() { return status; }

    /**
     * Sets the status of the attendance (e.g., 'present', 'absent', 'leave').
     * 
     * @param status the status to set.
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Marks attendance for the user by inserting a record into the database.
     * 
     * @param connection the database connection.
     * @throws SQLException if a database access error occurs.
     */
    public void markAttendance(Connection connection) throws SQLException {
        String query = "INSERT INTO attendance (user_id, date, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setDate(2, date);
            stmt.setString(3, status);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves the attendance records for a specified user.
     * 
     * @param connection the database connection.
     * @param userId the ID of the user whose attendance records are to be retrieved.
     * @return a list of Attendance objects representing the user's attendance records.
     * @throws SQLException if a database access error occurs.
     */
    public static List<Attendance> viewAttendance(Connection connection, int userId) throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                att.setAttendanceId(rs.getInt("attendance_id"));
                att.setUserId(rs.getInt("user_id"));
                att.setDate(rs.getDate("date"));
                att.setStatus(rs.getString("status"));
                attendanceList.add(att);
            }
        }
        return attendanceList;
    }
}
