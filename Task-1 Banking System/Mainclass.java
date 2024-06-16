//Create the Main Class:
import java.sql.ResultSet;
import java.sql.SQLException;

public class OnlineBankingSystem {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        AccountManager accountManager = new AccountManager();
        LoanManager loanManager = new LoanManager();

        try {
            // Create users
            userManager.createUser("john_doe", "password123", 1234);
            userManager.createUser("jane_smith", "password456", 5678);

            // Authenticate users
            if (userManager.authenticateUser("john_doe", "password123")) {
                System.out.println("John Doe authenticated successfully!");
            }

            // Verify PIN code
            if (userManager.verifyPinCode("john_doe", 1234)) {
                System.out.println("John Doe's PIN code verified successfully!");
            }

            // Get user ID
            int johnUserID = userManager.getUserID("john_doe");
            int janeUserID = userManager.getUserID("jane_smith");

            // Create accounts
            accountManager.createAccount(johnUserID, "Savings", 5000);
            accountManager.createAccount(janeUserID, "Checking", 3000);

            // Deposit funds
            accountManager.deposit(1, 200);
            System.out.println("Balance after deposit: $" + accountManager.getBalance(1));

            // Withdraw funds
            accountManager.withdraw(1, 100);
            System.out.println("Balance after withdrawal: $" + accountManager.getBalance(1));

            // Transfer funds
            accountManager.transferFunds(1, 2, 150);
            System.out.println("Balance after transfer (Account 1): $" + accountManager.getBalance(1));
            System.out.println("Balance after transfer (Account 2): $" + accountManager.getBalance(2));

            // Get transaction history
            ResultSet transactionHistory = accountManager.getTransactionHistory(1);
            while (transactionHistory.next()) {
                System.out.println("Transaction: " + transactionHistory.getString("type") + " - $" + transactionHistory.getDouble("amount"));
            }

            // Apply for a loan
            loanManager.applyForLoan(johnUserID, 10000, 5.5, 60);

            // Get loan details
            ResultSet loanDetails = loanManager.getLoanDetails(johnUserID);
            while (loanDetails.next()) {
                System.out.println("Loan Amount: $" + loanDetails.getDouble("amount") + ", Interest Rate: " + loanDetails.getDouble("interestRate") + "%");
            }

            // Make loan payment
            loanManager.makeLoanPayment(1, 500);
            loanDetails = loanManager.getLoanDetails(johnUserID);
            while (loanDetails.next()) {
                System.out.println("Remaining Loan Amount: $" + loanDetails.getDouble("amount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
