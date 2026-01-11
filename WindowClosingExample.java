import java.awt.*;
import java.awt.event.*;

public class WindowClosingExample extends Frame {
    Label L;

    WindowClosingExample() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }

        );

        L = new Label("Window Closing Example");
        this.add(L);

        setTitle("ZENSAR");
        setSize(300, 300);
        setVisible(true);

    }

    public static void main(String[] args) {
        new WindowClosingExample();
    }
}