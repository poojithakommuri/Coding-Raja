//Create the Patron Management Class
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatronManager {
    public void addPatron(String name, String contactInfo) throws SQLException {
        String query = "INSERT INTO Patron (name, contactInfo) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, contactInfo);
            stmt.executeUpdate();
        }
    }

    public ResultSet getPatron(int patronID) throws SQLException {
        String query = "SELECT * FROM Patron WHERE patronID = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, patronID);
        return stmt.executeQuery();
    }
}
