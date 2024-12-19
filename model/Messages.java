package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Messages model class.
 * This class handles sending and viewing messages between users.
 */
public class Messages {

    // Unique ID for the message.
    private int messageId;

    // ID of the sender.
    private int senderId;

    // ID of the receiver.
    private int receiverId;

    // The content of the message.
    private String message;

    // Timestamp when the message was sent.
    private Timestamp sentDate;

    /**
     * Gets the unique ID of the message.
     * @return the message ID
     */
    public int getMessageId() { return messageId; }

    /**
     * Sets the unique ID of the message.
     * @param messageId the message ID to set
     */
    public void setMessageId(int messageId) { this.messageId = messageId; }

    /**
     * Gets the sender's ID.
     * @return the sender ID
     */
    public int getSenderId() { return senderId; }

    /**
     * Sets the sender's ID.
     * @param senderId the sender ID to set
     */
    public void setSenderId(int senderId) { this.senderId = senderId; }

    /**
     * Gets the receiver's ID.
     * @return the receiver ID
     */
    public int getReceiverId() { return receiverId; }

    /**
     * Sets the receiver's ID.
     * @param receiverId the receiver ID to set
     */
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }

    /**
     * Gets the content of the message.
     * @return the message content
     */
    public String getMessage() { return message; }

    /**
     * Sets the content of the message.
     * @param message the message content to set
     */
    public void setMessage(String message) { this.message = message; }

    /**
     * Gets the timestamp when the message was sent.
     * @return the sent date
     */
    public Timestamp getSentDate() { return sentDate; }

    /**
     * Sets the timestamp when the message was sent.
     * @param sentDate the sent date to set
     */
    public void setSentDate(Timestamp sentDate) { this.sentDate = sentDate; }

    /**
     * Sends a new message by inserting a record into the database.
     * @param connection the database connection
     * @throws SQLException if a database error occurs
     */
    public void sendMessage(Connection connection) throws SQLException {
        String query = "INSERT INTO messages (sender_id, receiver_id, message) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, senderId);
            stmt.setInt(2, receiverId);
            stmt.setString(3, message);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves all messages for a specified user.
     * @param connection the database connection
     * @param userId the ID of the user to retrieve messages for
     * @return a list of Messages objects
     * @throws SQLException if a database error occurs
     */
    public static List<Messages> viewMessages(Connection connection, int userId) throws SQLException {
        List<Messages> messageList = new ArrayList<>();
        String query = "SELECT * FROM messages WHERE receiver_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Messages msg = new Messages();
                msg.setMessageId(rs.getInt("message_id"));
                msg.setSenderId(rs.getInt("sender_id"));
                msg.setReceiverId(rs.getInt("receiver_id"));
                msg.setMessage(rs.getString("message"));
                msg.setSentDate(rs.getTimestamp("sent_date"));
                messageList.add(msg);
            }
        }
        return messageList;
    }
}
