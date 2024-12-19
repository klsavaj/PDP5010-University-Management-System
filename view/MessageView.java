package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the MessageView class.
 * This class defines the graphical user interface for sending and receiving messages.
 */
public class MessageView extends JFrame {

    // Input field for receiver ID.
    private JTextField receiverIdField;

    // Text area for composing a message.
    private JTextArea messageArea;

    // Button to send a message.
    private JButton sendButton;

    // Table to display received messages.
    private JTable messagesTable;

    /**
     * Constructs the MessageView interface.
     * Initializes and arranges all components within the JFrame.
     */
    public MessageView() {
        setTitle("Messages");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        topPanel.setBorder(BorderFactory.createTitledBorder("Send Message"));
        topPanel.add(new JLabel("Receiver ID:"));
        receiverIdField = new JTextField();
        topPanel.add(receiverIdField);

        messageArea = new JTextArea(3, 20);
        topPanel.add(new JLabel("Message:"));
        topPanel.add(new JScrollPane(messageArea));

        sendButton = new JButton("Send Message");

        messagesTable = new JTable();
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Received Messages"));
        bottomPanel.add(new JScrollPane(messagesTable), BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(sendButton, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Gets the receiver ID input field.
     * @return the receiver ID field
     */
    public JTextField getReceiverIdField() { return receiverIdField; }

    /**
     * Gets the message composing area.
     * @return the message text area
     */
    public JTextArea getMessageArea() { return messageArea; }

    /**
     * Gets the send button.
     * @return the send message button
     */
    public JButton getSendButton() { return sendButton; }

    /**
     * Gets the table displaying received messages.
     * @return the messages table
     */
    public JTable getMessagesTable() { return messagesTable; }
}
