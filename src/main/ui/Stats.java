package ui;

import java.awt.*;
import javax.swing.JFrame;
import model.*;


public class Stats extends Canvas {
    Record record;

    public Stats(Record record) {
        this.record = record;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < record.getMostRecentLog().getLength(); i++) {
            g.drawString(record.getMostRecentLog().get(i).getName(), 100, 100);
        }
    }
}
