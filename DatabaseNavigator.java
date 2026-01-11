import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

// Ye program database ke records ko navigate karne ke liye hai
// First, Next, Previous, Last buttons se data dekh sakte hain
public class DatabaseNavigator extends JFrame implements ActionListener{
    JLabel l1, l2, l3;
    JTextField tf1, tf2, tf3;
    JButton b1, b2, b3, b4;
    Connection con;  // database connection ke liye
    Statement st;
    ResultSet rs;    // query results yaha store honge

    DatabaseNavigator() {
        setLayout(null);  // absolute positioning use kar rahe hain
   
        // Labels banaye student details ke liye
        l1 = new JLabel("Roll Number");
        l2 = new JLabel("Name");
        l3 = new JLabel("Stipend");

        // Text fields jisme data dikhega
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
         
        // Navigation buttons - pehle, aage, peeche, last record ke liye
        b1 = new JButton("First");
        b2 = new JButton("Next");
        b3 = new JButton("Previous");
        b4 = new JButton("Last");

    
        // Pehli row - Roll Number label aur textfield
        l1.setBounds(100, 100, 100, 30);
        tf1.setBounds(210, 100, 100, 30);

        // Dusri row - Name
        l2.setBounds(100, 150, 100, 30);
        tf2.setBounds(210, 150, 100, 30);

        // Teesri row - Stipend
        l3.setBounds(100, 200, 100, 30);
        tf3.setBounds(210, 200, 100, 30);

        // Chauthi row - First aur Next buttons
        b1.setBounds(100, 250, 100, 30);
        b2.setBounds(210, 250, 100, 30);

        // Paanchvi row - Previous aur Last buttons
        b3.setBounds(100, 300, 100, 30);
        b4.setBounds(210, 300, 100, 30);

        // Sab components ko frame me add kiya
        add(l1); add(tf1);
        add(l2); add(tf2);
        add(l3); add(tf3);
        add(b1); add(b2);
        add(b3); add(b4);

        setSize(500, 500);  // window ka size set kiya
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // close button pe exit ho jaye


        // Buttons pe action listener lagaya
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this); 

        // Database se connection banaya
        String url = "jdbc:mysql://localhost:3306/zensar";
        String user = "root";
        String password = "aditya";  // apna password yaha dala
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // driver load kiya
            con = DriverManager.getConnection(url, user, password);
            // Scrollable resultset chahiye tha isliye TYPE_SCROLL_INSENSITIVE use kiya
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM stds_nkocet");  // students ka data fetch kiya
            
            // Pehla record load karke display kar diya
            if (rs.first()) {
                displayRecord();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
        }
    }

    // Ye method current record ko text fields me display karta hai
    private void displayRecord() {
        try {
            tf1.setText(rs.getString("stds_no"));
            tf2.setText(rs.getString("stds_name"));
            tf3.setText(rs.getString("stds_stipend"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Button click hone pe ye method call hota hai
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == b1) {
                // First button - pehle record pe jao
                rs.first();
                displayRecord();
            } else if (e.getSource() == b2) {
                // Next button - agle record pe jao
                if (!rs.isLast()) { // agar last record nahi hai to hi aage jao
                    rs.next();
                    displayRecord();
                }
            } else if (e.getSource() == b3) {
                // Previous button - pichle record pe jao
                if (!rs.isFirst()) { // agar first record nahi hai to hi peeche jao
                    rs.previous();
                    displayRecord();
                }
            } else if (e.getSource() == b4) {
                // Last button - last record pe jao
                rs.last();
                displayRecord();
            }
        }
        
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Program yaha se start hota hai
        DatabaseNavigator dn =new DatabaseNavigator();
dn.setVisible(true);
dn.setSize(600, 600);
dn.addWindowFocusListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
});
        
    }
}