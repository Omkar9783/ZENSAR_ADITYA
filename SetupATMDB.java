import java.sql.*;

public class SetupATMDB {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "zensar";
    private static final String USER = "root";
    private static final String PASS = "aditya";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            // Cleanup old databases if they exist
            System.out.println("Cleaning up old databases...");
            stmt.executeUpdate("DROP DATABASE IF EXISTS ZENSAR_JAVA");
            stmt.executeUpdate("DROP DATABASE IF EXISTS ATM_DB");

            // Create Database
            System.out.println("Creating database " + DB_NAME + "...");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.executeUpdate("USE " + DB_NAME);

            // Create Accounts Table
            System.out.println("Creating table accounts...");
            String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
                         "customer_number INT PRIMARY KEY, " +
                         "pin_number INT NOT NULL, " +
                         "checking_balance DOUBLE DEFAULT 0, " +
                         "saving_balance DOUBLE DEFAULT 0)";
            stmt.executeUpdate(sql);

            // Insert Dummy Data (Only if empty)
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM accounts");
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Inserting sample data...");
                stmt.executeUpdate("INSERT INTO accounts VALUES (12345, 1234, 1000.0, 5000.0)");
                stmt.executeUpdate("INSERT INTO accounts VALUES (67890, 5678, 2000.0, 10000.0)");
            } else {
                System.out.println("Table already has data, skipping insertion.");
            }

            System.out.println("Database setup complete!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
