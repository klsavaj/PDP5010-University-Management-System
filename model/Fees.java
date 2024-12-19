package model;

import java.sql.*;

/**
 * Handles fees management, including calculations and database storage.
 */
public class Fees {

    /** Unique ID for the fee record. */
    private int feeId;

    /** User ID associated with the fee record. */
    private int userId;

    /** Total fees for the user. */
    private double totalFees;

    /** Paid amount by the user. */
    private double paid;

    /** Outstanding due amount for the user. */
    private double due;

    /**
     * Gets the fee record ID.
     * @return feeId
     */
    public int getFeeId() { return feeId; }

    /**
     * Sets the fee record ID.
     * @param feeId the fee ID to set
     */
    public void setFeeId(int feeId) { this.feeId = feeId; }

    /**
     * Gets the user ID.
     * @return userId
     */
    public int getUserId() { return userId; }

    /**
     * Sets the user ID.
     * @param userId the user ID to set
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the total fees.
     * @return totalFees
     */
    public double getTotalFees() { return totalFees; }

    /**
     * Sets the total fees.
     * @param totalFees the total fees to set
     */
    public void setTotalFees(double totalFees) { this.totalFees = totalFees; }

    /**
     * Gets the paid amount.
     * @return paid
     */
    public double getPaid() { return paid; }

    /**
     * Sets the paid amount.
     * @param paid the paid amount to set
     */
    public void setPaid(double paid) { this.paid = paid; }

    /**
     * Gets the due amount.
     * @return due
     */
    public double getDue() { return due; }

    /**
     * Sets the due amount.
     * @param due the due amount to set
     */
    public void setDue(double due) { this.due = due; }

    /**
     * Inserts a new fee record into the database.
     * @param connection the database connection
     * @throws SQLException if a database error occurs
     */
    public void calculateFees(Connection connection) throws SQLException {
        String query = "INSERT INTO fees (user_id, total_fees, paid, due) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setDouble(2, totalFees);
            stmt.setDouble(3, paid);
            stmt.setDouble(4, due);
            stmt.executeUpdate();
        }
    }

    /**
     * Fetches the fee record for a user.
     * @param connection the database connection
     * @param userId the user's ID
     * @return the Fees object or null if not found
     * @throws SQLException if a database error occurs
     */
    public static Fees viewFees(Connection connection, int userId) throws SQLException {
        String query = "SELECT * FROM fees WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fees fees = new Fees();
                fees.setFeeId(rs.getInt("fee_id"));
                fees.setUserId(rs.getInt("user_id"));
                fees.setTotalFees(rs.getDouble("total_fees"));
                fees.setPaid(rs.getDouble("paid"));
                fees.setDue(rs.getDouble("due"));
                return fees;
            }
        }
        return null;
    }
}
