package model;

import java.sql.*;

/**
 * Represents the User model class.
 * This class handles user information, including adding, updating, and deleting users.
 */
public class User {

    // Unique ID for the user.
    private int userId;

    // First name of the user.
    private String firstName;

    // Last name of the user.
    private String lastName;

    // Age of the user.
    private int age;

    // Date of birth of the user.
    private Date dob;

    // Address of the user.
    private String address;

    // Phone number of the user.
    private String phone;

    // Email of the user.
    private String email;

    // Role of the user (e.g., 'student', 'teacher', 'admin').
    private String role;

    // Course associated with the user.
    private String course;

    // Branch associated with the user.
    private String branch;

    /**
     * Gets the unique ID of the user.
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique ID of the user.
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the first name of the user.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age of the user.
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the date of birth of the user.
     * @return the date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the user.
     * @param dob the date of birth to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Gets the address of the user.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone number of the user.
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the user.
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email of the user.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role of the user.
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the course of the user.
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the course of the user.
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Gets the branch of the user.
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets the branch of the user.
     * @param branch the branch to set
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Adds a new user to the database.
     * @param connection the database connection
     * @throws SQLException if a database error occurs
     */
    public void addUser(Connection connection) throws SQLException {
        String query = "INSERT INTO users (first_name, last_name, age, dob, address, phone, email, role, course, branch) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.setDate(4, dob);
            stmt.setString(5, address);
            stmt.setString(6, phone);
            stmt.setString(7, email);
            stmt.setString(8, role);
            stmt.setString(9, course);
            stmt.setString(10, branch);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.userId = rs.getInt(1);
            }
        }
    }

    /**
     * Updates an existing user in the database.
     * @param connection the database connection
     * @throws SQLException if a database error occurs
     */
    public void updateUser(Connection connection) throws SQLException {
        String query = "UPDATE users SET first_name = ?, last_name = ?, age = ?, dob = ?, address = ?, phone = ?, email = ?, course = ?, branch = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.setDate(4, dob);
            stmt.setString(5, address);
            stmt.setString(6, phone);
            stmt.setString(7, email);
            stmt.setString(8, course);
            stmt.setString(9, branch);
            stmt.setInt(10, userId);
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a user from the database.
     * @param connection the database connection
     * @throws SQLException if a database error occurs
     */
    public void deleteUser(Connection connection) throws SQLException {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    /**
     * Fetches a user by ID from the database.
     * @param connection the database connection
     * @param userId the user ID
     * @return the User object or null if not found
     * @throws SQLException if a database error occurs
     */
    public static User getUserById(Connection connection, int userId) throws SQLException {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return constructUser(rs);
            }
        }
        return null;
    }

    /**
     * Fetches a user by email from the database (used for login).
     * @param connection the database connection
     * @param email the email of the user
     * @return the User object or null if not found
     * @throws SQLException if a database error occurs
     */
    public static User getUserByEmail(Connection connection, String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return constructUser(rs);
            }
        }
        return null;
    }

    /**
     * Fetches all users from the database.
     * @param connection the database connection
     * @return a ResultSet containing all users
     * @throws SQLException if a database error occurs
     */
    public static ResultSet getAllUsers(Connection connection) throws SQLException {
        String query = "SELECT user_id, first_name, last_name, role, email FROM users";
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    /**
     * Constructs a User object from a ResultSet.
     * @param rs the ResultSet containing user data
     * @return the constructed User object
     * @throws SQLException if a database error occurs
     */
    private static User constructUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAge(rs.getInt("age"));
        user.setDob(rs.getDate("dob"));
        user.setAddress(rs.getString("address"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        user.setCourse(rs.getString("course"));
        user.setBranch(rs.getString("branch"));
        return user;
    }
}
