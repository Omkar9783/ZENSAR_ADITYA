import java.sql.*;
public class CreateDatabase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "aditya";
        String dbName="zensar";


        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url , user, password);
        System.out.println("DATABASE CONNECTED SUCCESSFULLY");

        Statement stmt=conn.createStatement();
        String sqlQuery="Create Database " + dbName;

        stmt.executeUpdate(sqlQuery);
System.out.println("DATABASE CREATED SUCCESSFULLY");

conn.close();   



        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
