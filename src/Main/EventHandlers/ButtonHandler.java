package Main.EventHandlers;

import Main.Algorithms.Pathfinder;
import Main.Driver;
import Main.Mazegenerator.MazeGenerator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ButtonHandler implements ActionListener, ChangeListener, ItemListener {

    public static boolean start = false;
    public static boolean addwalls = false;
    public static boolean wipeboard = false;
    public static boolean allowdiagnals = false;
    public static String selectedItem = "A* algorithm";

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            //START THE ALGORITHM
            if (e.getActionCommand().equals("start")) {
                start = true;
                addwalls = false;
            }
            //STOP THE ALGORITHM
            if (e.getActionCommand().equals("stop")) {
                start = false;
            }
            //ADDING RANDOM WALLS
            if (e.getActionCommand().equals("addwalls")) {
                start = false;
                addwalls = true;
            }
            //CLEAR THE BOARD
            if (e.getActionCommand().equals("wipeboard")) {
                start = false;
                wipeboard = true;
            }
        }

        //SELECT THE ALGORITHM
        if (e.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            selectedItem = (String) cb.getSelectedItem();
            Pathfinder.selectItem();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        //APPLY SLIDER VALUES
        JSlider source = (JSlider)e.getSource();
        if (source == Driver.slider) {
            MazeGenerator.e = (double) ((JSlider) e.getSource()).getValue() / 100;
        } else if (source == Driver.fpssetter){
            Driver.fps = ((JSlider) e.getSource()).getValue();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //ALLOW DIAGONAL MOVES?
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
