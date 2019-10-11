package Main.EventHandlers;


import Main.Driver;
import Main.Mategenerator.MazeGenerator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ButtonHandler implements ActionListener, ChangeListener, ItemListener {

    public static boolean go = false;
    public static boolean clear = false;
    public static boolean wipeboard = false;
    public static boolean allowdiagnals = false;

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
        if (e.getActionCommand().equals("wipeboard")){
            go = false;
            wipeboard = true;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        MazeGenerator.e = (double)((JSlider) e.getSource()).getValue()/100;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (source == Driver.allowdiagnal){
            if (Driver.allowdiagnal.isSelected()){
                allowdiagnals = true;
            } else {
                allowdiagnals = false;
            }
        }

    }
}
