import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

public class FlowLayoutExample extends JFrame {

    JLabel l1, l2;
    JTextField tf1, tf2;
    JButton b1, b2, b3;
    JFrame jf;

    FlowLayoutExample() {
        jf = new JFrame("Flow Layout Example");
        jf.setSize(300, 150);
        l1 = new JLabel("Enter Name");
        l2 = new JLabel("Enter City");

        // by default size of text field is 20
        tf1 = new JTextField(15);
        tf2 = new JTextField(15);

        b1 = new JButton("Clear");
        b2 = new JButton("Submit");
        b3 = new JButton("Exit");

        jf.setLayout(new FlowLayout());

        jf.add(l1);
        jf.add(l2);
        jf.add(tf1);
        jf.add(tf2);
        jf.add(b1);
        jf.add(b2);
        jf.add(b3);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new FlowLayoutExample();
    }
}
