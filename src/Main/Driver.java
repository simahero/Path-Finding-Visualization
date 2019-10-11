package Main;

import Main.Algorithms.Astar;
import Main.Algorithms.Dijkstra;
import Main.Algorithms.Pathfinder;
import Main.EventHandlers.ButtonHandler;
import Main.EventHandlers.MouseHandler;
import Main.Mategenerator.MazeGenerator;
import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Driver implements Runnable {

    private JFrame frame;
    private Canvas canvas;
    private JPanel panel;
    public static Pathfinder pathfinder;

    ButtonHandler bh;
    JButton start;
    JButton stop;
    JButton addwalls;
    JLabel wall = new JLabel("Likelyhood of walls in %", SwingConstants.CENTER);
    JLabel diagnals = new JLabel("Allow diagnal moves?", SwingConstants.CENTER);
    JButton wipeboard;
    public static JCheckBox allowdiagnal;
    JSlider slider;
    JComboBox algorithm;

    public static Thread t;
    public static Driver d;

    public static int s = 47;
    public static int xMAX = 16; //24 : 18
    public static int yMAX = 16;
    int screenheight = 792;
    int screenwidth = screenheight / 2 * 3;


    public Driver() {
        frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 792;
        c.ipady = 800;
        frame.add(canvas = new Canvas(), c);
        canvas.addMouseListener(new MouseHandler());
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 225;
        c.ipady = 0;
        frame.add(panel = new JPanel(), c);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(new GridBagLayout());

        /*******************************************
         *             ADDIMG BUTTONS
         *******************************************/

        start = new JButton("GO!");
        start.setActionCommand("go");
        start.addActionListener(bh = new ButtonHandler());
        stop = new JButton("STOP!");
        stop.setActionCommand("stop");
        stop.addActionListener(bh);
        addwalls = new JButton("Add random walls!");
        addwalls.setActionCommand("clear");
        addwalls.addActionListener(bh);
        wipeboard = new JButton("Clear!");
        wipeboard.setActionCommand("wipeboard");
        wipeboard.addActionListener(bh);

        /*******************************************
         *             ADDIMG SLIDERS
         *******************************************/

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
        slider.addChangeListener(bh);
        //slider.setActionMap(new ActionMap());
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);

        /*******************************************
         *             ADDIMG CHECKBOXES
         *******************************************/

        allowdiagnal = new JCheckBox();
        allowdiagnal.addItemListener(bh);
        allowdiagnal.setSelected(true);

        /*******************************************
         *             ADDIMG COMBOBOX
         *******************************************/

        String[] algorithms = {"A* algorithm", "Dijkstra's algorithm"};
        algorithm = new JComboBox<>(algorithms);
        algorithm.setSelectedIndex(0);
        algorithm.addActionListener(bh);


        c.ipadx = 0;
        c.ipady = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 0, 1, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(start, c);
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        panel.add(stop, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        panel.add(wipeboard, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        panel.add(wall, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        panel.add(slider, c);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        panel.add(addwalls, c);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        panel.add(allowdiagnal, c);
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        panel.add(diagnals, c);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;
        panel.add(algorithm, c);
        frame.setSize(screenwidth, screenheight); //1217 : 940
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(this).start();

    }

    @Override
    public void run() {
        BasicTimer basicTimer = new BasicTimer(20);
        MazeGenerator mg = new MazeGenerator();
        Pathfinder.selectItem();
        pathfinder.init();

        while (true) {
            basicTimer.sync();
            screenheight = frame.getHeight();
            screenwidth = screenheight / 2 * 3;
            frame.setSize(screenwidth, screenheight);
            //s = (frame.getHeight()/yMAX+6);
            if (ButtonHandler.go) {
                pathfinder.addNeighbour();
                if (ButtonHandler.allowdiagnals) {
                    pathfinder.addNeighbourDiagnal();
                }
                update();
            }
            render();
            if (ButtonHandler.clear) {
                Pathfinder.reset();
                pathfinder.init();
                mg.addRandomWalls(Pathfinder.list);
                ButtonHandler.clear = false;
            }
            if (ButtonHandler.wipeboard) {
                Pathfinder.wipeboard();
                ButtonHandler.wipeboard = false;
            }
            System.out.println(ButtonHandler.selectedItem);
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
        g.fillRect(Pathfinder.end.getX() * s, Pathfinder.end.getY() * s, s, s);
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
