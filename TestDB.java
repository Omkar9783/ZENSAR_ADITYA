import java.sql.*;

public class TestDB {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "omkar@123";

        System.out.println("Connecting to database...");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection Successful!");

            // Optional: Print DB version to be sure
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                System.out.println("MySQL Version: " + rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}