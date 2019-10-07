package Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    static int mouseB;
    static Point p;
    static boolean pressed;

    public void mouseClicked(MouseEvent e) {
        int x = (int) Math.ceil(e.getX() / Driver.s);
        int y = (int) Math.ceil(e.getY() / Driver.s);
        Pathfinding.addWall(x, y);
        System.out.println("Mouse Clicked at X: " + x + " - Y: " + y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
