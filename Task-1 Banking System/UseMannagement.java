//Create the User Management Class:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    public void createUser(String username, String password, int pinCode) throws SQLException {
        String query = "INSERT INTO User (username, password, pinCode) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, pinCode);
            stmt.executeUpdate();
        }
    }

    public boolean authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM User WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean verifyPinCode(String username, int pinCode) throws SQLException {
        String query = "SELECT * FROM User WHERE username = ? AND pinCode = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, pinCode);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public int getUserID(String username) throws SQLException {
        String query = "SELECT userID FROM User WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("userID");
            }
        }
        return -1;
    }
}
