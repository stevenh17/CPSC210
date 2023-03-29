package ui;

import javax.swing.*;
import java.awt.*;

public class StatCircle extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(0, 0, 250, 250);
    }
}
