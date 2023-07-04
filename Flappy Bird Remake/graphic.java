package flappyBird;

import javax.swing.*;
import java.awt.*;

public class graphic extends JPanel {
    protected void paintComponent(Graphics g) { //class which repaints the main game graphics
        super.paintComponent(g);
        FlappyBird.frame.repaint(g);
    }
}
