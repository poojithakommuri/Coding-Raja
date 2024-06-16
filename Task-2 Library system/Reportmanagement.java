//Create the Report Management Class:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportManager {
    public ResultSet generateBookAvailabilityReport() throws SQLException {
        String query = "SELECT * FROM Book WHERE availability = TRUE";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    }

    public ResultSet generateBorrowingHistoryReport(int patronID) throws SQLException {
        String query = "SELECT * FROM BorrowingRecord WHERE patronID = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, patronID);
        return stmt.executeQuery();
    }

    public ResultSet generateFineReport(int patronID) throws SQLException {
        String query = "SELECT recordID, returnDate, borrowDate FROM BorrowingRecord WHERE patronID = ? AND returnDate IS NOT NULL";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, patronID);
        return stmt.executeQuery();
    }
}
