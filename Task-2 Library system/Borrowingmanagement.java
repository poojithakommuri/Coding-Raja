//Create the Borrowing Management Class:
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowingManager {
    public void borrowBook(int bookID, int patronID) throws SQLException {
        String borrowQuery = "INSERT INTO BorrowingRecord (bookID, patronID, borrowDate) VALUES (?, ?, ?)";
        String updateQuery = "UPDATE Book SET availability = FALSE WHERE bookID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement borrowStmt = conn.prepareStatement(borrowQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            conn.setAutoCommit(false);
            borrowStmt.setInt(1, bookID);
            borrowStmt.setInt(2, patronID);
            borrowStmt.setDate(3, new Date(System.currentTimeMillis()));
            borrowStmt.executeUpdate();
            updateStmt.setInt(1, bookID);
            updateStmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int recordID) throws SQLException {
        String returnQuery = "UPDATE BorrowingRecord SET returnDate = ? WHERE recordID = ?";
        String updateQuery = "UPDATE Book SET availability = TRUE WHERE bookID = (SELECT bookID FROM BorrowingRecord WHERE recordID = ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement returnStmt = conn.prepareStatement(returnQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            conn.setAutoCommit(false);
            returnStmt.setDate(1, new Date(System.currentTimeMillis()));
            returnStmt.setInt(2, recordID);
            returnStmt.executeUpdate();
            updateStmt.setInt(1, recordID);
            updateStmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
