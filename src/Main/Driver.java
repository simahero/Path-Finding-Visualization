package Main;

import Main.Algorithms.Astar;
import Main.Algorithms.Pathfinder;
import Main.EventHandlers.ButtonHandler;
import Main.EventHandlers.MouseHandler;
import Main.Mategenerator.MazeGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Driver implements Runnable {

    private Canvas canvas;
    private JPanel panel;
    public static Pathfinder pathfinder;

    ButtonHandler bh;
    JButton start;
    JButton stop;
    JButton clear;
    JSlider slider;

    public static Thread t;
    public static Driver d;

    public static int s = 50;
    public static int xMAX = 19; //24 : 18
    public static int yMAX = 19;
    public static boolean running = false;

    public Driver() {
        JFrame frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = s * xMAX + 201;
        c.ipady = s * yMAX + 1;
        frame.add(canvas = new Canvas(), c);
        canvas.addMouseListener(new MouseHandler());
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 0;
        //c.ipady = s * yMAX;
        frame.add(panel = new JPanel(), c);

        /*******************************************
         *             ADDIMG BUTTONS
         *******************************************/

                start = new JButton("GO!");
                start.setActionCommand("go");
                start.addActionListener(bh = new ButtonHandler());
                stop = new JButton("STOP!");
                stop.setActionCommand("stop");
                stop.addActionListener(bh);
                clear = new JButton("Add random walls!");
                clear.setActionCommand("clear");
                clear.addActionListener(bh);

        /*******************************************
         *             ADDIMG SLIDERS
         *******************************************/

                slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
                slider.addChangeListener(bh);
                //slider.setActionMap(new ActionMap());
                slider.setMajorTickSpacing(10);
                slider.setMinorTickSpacing(1);
                slider.setPaintLabels(true);

                //slider.addChangeListener();
            panel.add(BorderLayout.NORTH, stop);
            panel.add(BorderLayout.NORTH, start);
            panel.add(BorderLayout.NORTH, clear);
            panel.add(BorderLayout.EAST, slider);
        frame.setSize(1350, 1020); //1217 : 940
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(this).start();

    }

    @Override
    public void run() {
        BasicTimer basicTimer = new BasicTimer(20);
        MazeGenerator mg = new MazeGenerator();
        pathfinder = new Astar();
        pathfinder.init();
        //mg.addRandomWalls(Pathfinder.list);
        //pathfinder.addNeighbour();

        while (true) {
            basicTimer.sync();
            if (ButtonHandler.go) {
                pathfinder.addNeighbour();
                update();
            }
            render();
            if (ButtonHandler.clear){
                Pathfinder.reset();
                pathfinder.init();
                mg.addRandomWalls(Pathfinder.list);
                ButtonHandler.clear = false;
                pathfinder.addNeighbour();
            }
            System.out.println(MazeGenerator.e);

        }
    }

    public void update() {
        pathfinder.search();
    }

    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, xMAX * s, yMAX * s);
        for (Node e : Pathfinder.openset) {
            g.setColor(Color.GREEN);
            g.fillRect(e.getX() * s, e.getY() * s, s, s);
        }
        for (Node f : Pathfinder.closedset) {
            g.setColor(Color.RED);
            g.fillRect(f.getX() * s, f.getY() * s, s, s);
        }
        for (Node p : Pathfinder.path) {
            g.setColor(Color.CYAN);
            g.fillRect(p.getX() * s, p.getY() * s, s, s);
        }
        for (Node w : Pathfinder.wallset) {
            g.setColor(Color.BLACK);
            g.fillRect(w.getX() * s, w.getY() * s, s, s);
        }
        g.setColor(Color.CYAN);
        g.fillRect(Pathfinder.end.getX()*s, Pathfinder.end.getY()*s, s, s);
        g.setColor(Color.BLACK);
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                g.drawRect(i * s, j * s, s, s);
            }
        }
        g.dispose();
        bs.show();
    }


    public static void main(String[] args) {
        new Driver();
    }
}
