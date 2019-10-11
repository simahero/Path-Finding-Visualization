package Main.EventHandlers;


import Main.Algorithms.Pathfinder;
import Main.Driver;
import Main.Mategenerator.MazeGenerator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Path;

public class ButtonHandler implements ActionListener, ChangeListener, ItemListener {

    public static boolean start = false;
    public static boolean addwalls = false;
    public static boolean wipeboard = false;
    public static boolean allowdiagnals = false;
    public static String selectedItem = "A* algorithm";

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (e.getActionCommand().equals("start")) {
                start = true;
                addwalls = false;
            }
            if (e.getActionCommand().equals("stop")) {
                start = false;
            }
            if (e.getActionCommand().equals("addwalls")) {
                start = false;
                addwalls = true;
            }
            if (e.getActionCommand().equals("wipeboard")) {
                start = false;
                wipeboard = true;
            }
        }

        if (e.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            selectedItem = (String) cb.getSelectedItem();
            Pathfinder.selectItem();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (source == Driver.slider) {
            MazeGenerator.e = (double) ((JSlider) e.getSource()).getValue() / 100;
        } else if (source == Driver.fpssetter){
            Driver.fps = ((JSlider) e.getSource()).getValue();
        }
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
