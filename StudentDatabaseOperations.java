import java.sql.*;

// Ye program students ka database banata hai aur usme data insert karta hai
// zensar database me stds_nkocet table create hoti hai
public class StudentDatabaseOperations {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/";
        String user="root";
        String password="aditya";  // MySQL ka password
        String dbName = "zensar";
try{

    Class.forName("com.mysql.cj.jdbc.Driver");  // MySQL driver load kiya

    Connection con = DriverManager.getConnection(url,user,password);
    System.out.println("Database connected successfully");

    Statement stmt=con.createStatement();
    // Cleanup old databases
    stmt.executeUpdate("drop database if exists ZENSAR_JAVA");
    stmt.executeUpdate("drop database if exists ATM_DB");

    // Pehle database banaya agar exist nahi karta
    stmt.executeUpdate("create database if not exists " + dbName);
    stmt.executeUpdate("use " + dbName);  // database use karne ke liye
    stmt.executeUpdate("drop table if exists stds_nkocet");  // purana table delete kar diya
    
    // Table create kiya - roll no, name aur stipend ke columns ke saath
    String createTableQuery="create table if not exists stds_nkocet (stds_no int primary key, stds_name varchar(100), stds_stipend double)";  
    stmt.executeUpdate(createTableQuery);
    System.out.println("Table created successfully");

    // Prepared statement use kiya data insert karne ke liye - SQL injection se bachne ke liye
    PreparedStatement pst = con.prepareStatement("insert into stds_nkocet(stds_no,stds_name,stds_stipend) values(?,?,?)");  


    // Pehla student - roll no 401
    pst.setInt(1,401);
    pst.setString(2,"omkar");
    pst.setDouble(3,10000);
    pst.executeUpdate();


    pst.setInt(1,402);
    pst.setString(2,"aditya");
    pst.setDouble(3,20000);
    pst.executeUpdate();

    pst.setInt(1,403);
    pst.setString(2,"pratik");
    pst.setDouble(3,30000);
    pst.executeUpdate();

    pst.setInt(1,404);
    pst.setString(2,"anas");
    pst.setDouble(3,40000);     
    pst.executeUpdate();

    pst.setInt(1,405);
    pst.setString(2,"rohan");
    pst.setDouble(3,50000);     
    pst.executeUpdate();

    pst.setInt(1,406);
    pst.setString(2,"rohit");
    pst.setDouble(3,60000);   
    pst.executeUpdate();      

    pst.setInt(1,407);
    pst.setString(2,"patange");
    pst.setDouble(3,70000);         
    pst.executeUpdate();

    pst.setInt(1,408);
    pst.setString(2,"tarang");
    pst.setDouble(3,80000);
    pst.executeUpdate();        

    pst.setInt(1,409);
    pst.setString(2,"suraj");
    pst.setDouble(3,90000);
    pst.executeUpdate();         

    // Last student - roll no 410
    pst.setInt(1,410);
    pst.setString(2,"chetan");
    pst.setDouble(3,100000);     
    pst.executeUpdate();         

    System.out.println("Data inserted successfully");  // sab kuch ho gaya to success message

}
catch(Exception e){
    e.printStackTrace();  // agar koi error aaye to print kar do
}
    }
    
}
