//Create the Fine Calculation Class:

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class FineManager {
    private static final double FINE_RATE = 0.50; // 50 cents per day

    public double calculateFine(int recordID) throws SQLException {
        String query = "SELECT borrowDate, returnDate FROM BorrowingRecord WHERE recordID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recordID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Date borrowDate = rs.getDate("borrowDate");
                Date returnDate = rs.getDate("returnDate");
                long diffInMillies = Math.abs(returnDate.getTime() - borrowDate.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                return diff * FINE_RATE;
            }
        }
        return 0.0;
    }
}
