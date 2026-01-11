import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {
    TextField tf;
    String[] labels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "C", "0", "=", "/"
    };
    Button[] buttons = new Button[16];

    // Variables to store calculation state
    String s1 = "", s2 = "", operator = "";

    Calculator() {
        // Use GridLayout with 4 rows and 4 columns, and gaps
        GridLayout gl = new GridLayout(4, 4, 10, 10);

        // Main layout for the frame (BorderLayout is default, but let's be explicit)
        setLayout(new BorderLayout(10, 10));

        tf = new TextField();
        add(tf, BorderLayout.NORTH);

        Panel p = new Panel();
        p.setLayout(gl);

        for (int i = 0; i < 16; i++) {
            buttons[i] = new Button(labels[i]);
            buttons[i].addActionListener(this);
            p.add(buttons[i]);
        }

        add(p, BorderLayout.CENTER);

        setTitle("Calculator");
        setSize(300, 300);
        setVisible(true);

        // Window closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') {
            if (!operator.isEmpty()) {
                s2 += cmd;
            } else {
                s1 += cmd;
            }
            tf.setText(s1 + operator + s2);
        } else if (cmd.equals("C")) {
            s1 = s2 = operator = "";
            tf.setText("");
        } else if (cmd.equals("=")) {
            try {
                double result = calculate(Double.parseDouble(s1), Double.parseDouble(s2), operator);
                tf.setText(String.valueOf(result));
                s1 = String.valueOf(result);
                s2 = "";
                operator = "";
            } catch (Exception ex) {
                tf.setText("Error");
            }
        } else {
            if (!s1.isEmpty()) {
                operator = cmd;
                tf.setText(s1 + operator);
            }
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
