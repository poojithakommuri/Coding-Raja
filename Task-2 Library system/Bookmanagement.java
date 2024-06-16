//Create the Book Management Class:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookManager {
    public void addBook(String title, String author, String genre) throws SQLException {
        String query = "INSERT INTO Book (title, author, genre) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, genre);
            stmt.executeUpdate();
        }
    }

    public void updateAvailability(int bookID, boolean availability) throws SQLException {
        String query = "UPDATE Book SET availability = ? WHERE bookID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, availability);
            stmt.setInt(2, bookID);
            stmt.executeUpdate();
        }
    }

    public ResultSet searchBooks(String keyword) throws SQLException {
        String query = "SELECT * FROM Book WHERE title LIKE ? OR author LIKE ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + keyword + "%");
        stmt.setString(2, "%" + keyword + "%");
        return stmt.executeQuery();
    }
}
