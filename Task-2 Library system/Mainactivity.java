//Create the Main Class:
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        PatronManager patronManager = new PatronManager();
        BorrowingManager borrowingManager = new BorrowingManager();
        FineManager fineManager = new FineManager();
        ReportManager reportManager = new ReportManager();

        try {
            // Add books
            bookManager.addBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction");
            bookManager.addBook("1984", "George Orwell", "Dystopian");

            // Add patrons
            patronManager.addPatron("John Doe", "johndoe@example.com");
            patronManager.addPatron("Jane Smith", "janesmith@example.com");

            // Borrow a book
            borrowingManager.borrowBook(1, 1);

            // Return a book
            borrowingManager.returnBook(1);

            // Calculate fine
            double fine = fineManager.calculateFine(1);
            System.out.println("Fine: $" + fine);

            // Generate reports
            ResultSet availableBooks = reportManager.generateBookAvailabilityReport();
            while (availableBooks.next()) {
                System.out.println("Available Book: " + availableBooks.getString("title"));
            }

            ResultSet borrowingHistory = reportManager.generateBorrowingHistoryReport(1);
            while (borrowingHistory.next()) {
                System.out.println("Borrowed Book ID: " + borrowingHistory.getInt("bookID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
