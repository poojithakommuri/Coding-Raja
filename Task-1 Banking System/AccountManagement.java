//Create the Account Management Class:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    public void createAccount(int userID, String accountType, double initialBalance) throws SQLException {
        String query = "INSERT INTO Account (userID, accountType, balance) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, accountType);
            stmt.setDouble(3, initialBalance);
            stmt.executeUpdate();
        }
    }

    public double getBalance(int accountID) throws SQLException {
        String query = "SELECT balance FROM Account WHERE accountID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        }
        return 0.0;
    }

    public void deposit(int accountID, double amount) throws SQLException {
        String query = "UPDATE Account SET balance = balance + ? WHERE accountID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);
            stmt.executeUpdate();
        }
        addTransaction(accountID, "Deposit", amount);
    }

    public void withdraw(int accountID, double amount) throws SQLException {
        String query = "UPDATE Account SET balance = balance - ? WHERE accountID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);
            stmt.executeUpdate();
        }
        addTransaction(accountID, "Withdrawal", amount);
    }

    public void transferFunds(int fromAccountID, int toAccountID, double amount) throws SQLException {
        withdraw(fromAccountID, amount);
        deposit(toAccountID, amount);
        addTransaction(fromAccountID, "Transfer Out", amount);
        addTransaction(toAccountID, "Transfer In", amount);
    }

    private void addTransaction(int accountID, String type, double amount) throws SQLException {
        String query = "INSERT INTO Transaction (accountID, type, amount) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountID);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        }
    }

    public ResultSet getTransactionHistory(int accountID) throws SQLException {
        String query = "SELECT * FROM Transaction WHERE accountID = ? ORDER BY date DESC";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, accountID);
        return stmt.executeQuery();
    }
}
