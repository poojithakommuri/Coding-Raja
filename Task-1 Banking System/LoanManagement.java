//Create the Loan Management Class:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanManager {
    public void applyForLoan(int userID, double amount, double interestRate, int term) throws SQLException {
        String query = "INSERT INTO Loan (userID, amount, interestRate, term, startDate) VALUES (?, ?, ?, ?, CURDATE())";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setDouble(2, amount);
            stmt.setDouble(3, interestRate);
            stmt.setInt(4, term);
            stmt.executeUpdate();
        }
    }

    public ResultSet getLoanDetails(int userID) throws SQLException {
        String query = "SELECT * FROM Loan WHERE userID = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userID);
        return stmt.executeQuery();
    }

    public void makeLoanPayment(int loanID, double amount) throws SQLException {
        String query = "UPDATE Loan SET amount = amount - ? WHERE loanID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, loanID);
            stmt.executeUpdate();
        }
    }
}
