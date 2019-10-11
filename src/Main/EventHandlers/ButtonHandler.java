package Main.EventHandlers;


import Main.Mategenerator.MazeGenerator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener, ChangeListener {

    public static boolean go = false;
    public static boolean clear = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("go")){
            go = true;
            clear = false;
        }
        if (e.getActionCommand().equals("stop")){
            go = false;
        }
        if (e.getActionCommand().equals("clear")){
            go = false;
            clear = true;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        MazeGenerator.e = (double)((JSlider) e.getSource()).getValue()/100;
    }
}
