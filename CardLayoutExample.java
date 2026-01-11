import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// CardLayout ka example - do panels ke beech switch karne ke liye
// Numbers button pe click karo to numbers dikhenge, Alphabets pe alphabets
public class CardLayoutExample extends JFrame implements ActionListener{

    JFrame jf;
    JPanel pp;  // parent panel
    CardLayout cd;  // card layout manager

    CardLayoutExample(){
        jf = new JFrame("CardLayout Example");
        cd = new CardLayout();
        
        // Ek main parent panel banaya jo 2 child panels ko contain karega
        pp = new JPanel();

        // 2 child panels banaye
        JPanel cp1 = new JPanel();  // numbers ke liye
        JPanel cp2 = new JPanel();  // alphabets ke liye

        // 2 main buttons banaye switching ke liye
        JButton b1= new JButton("Numbers");
        JButton b2 = new JButton("Alphabets");

        // Numbers panel ke liye 3 buttons
        JButton b3 = new JButton("1");
        JButton b4 = new JButton("2");
        JButton b5 = new JButton("3");

        // b3,b4 aur b5 ko cp1 me add kiya
        cp1.add(b3);
        cp1.add(b4);
        cp1.add(b5);

        // Alphabets panel ke liye 4 buttons
        JButton b6 = new JButton("A");
        JButton b7 = new JButton("B");
        JButton b8 = new JButton("C");
        JButton b9 = new JButton("D");
        
        // b6,b7,b8,b9 ko cp2 me add kiya
        cp2.add(b6);
        cp2.add(b7);
        cp2.add(b8);
        cp2.add(b9);

        // Parent panel ka layout CardLayout set kiya
        pp.setLayout(cd);

        // Dono child panels ko parent me add kiya with names
        pp.add(cp1,"Numbers");
        pp.add(cp2, "Alphabets");
        
        // Main buttons pe action listener lagaya
        b1.addActionListener(this);
        b2.addActionListener(this);

        // JFrame ka layout FlowLayout set kiya
        jf.setLayout(new FlowLayout());

        // Main buttons ko frame me add kiya - ye hamesha visible rahenge
        jf.add(b1);
        jf.add(b2);

        // Parent panel ko frame me add kiya
        jf.add(pp);

        jf.setSize(300, 200);
        jf.setVisible(true);

    }



    public static void main(String[] args) {
        new CardLayoutExample();  // program start
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Button click hone pe corresponding panel show karo
        if (e.getActionCommand().equals("Numbers")) {
            cd.show(pp, "Numbers");  // numbers panel dikhao
        } else {
            cd.show(pp, "Alphabets");  // alphabets panel dikhao
        }
    }
}
