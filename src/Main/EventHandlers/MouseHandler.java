package Main.EventHandlers;

import Main.Algorithms.Pathfinder;
import Main.Driver;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    public void mouseClicked(MouseEvent e) {
        int x = (int) Math.ceil(e.getX() / Driver.s);
        int y = (int) Math.ceil(e.getY() / Driver.s);
        Pathfinder.addClickedWall(x, y);
        System.out.println("Mouse Clicked at X: " + x + " - Y: " + y);
        //pressed = true;
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
