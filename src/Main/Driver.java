package Main;

import Main.Algorithms.Astar;
import Main.Algorithms.Pathfinder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Driver implements Runnable {

    private Canvas canvas;
    private Pathfinder pathfinder;

    protected static int s = 50;
    public static int xMAX = 19; //24 : 18
    public static int yMAX = 19;

    public Driver() {
        JFrame frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas = new Canvas());
        canvas.addMouseListener(new MouseHandler());
        frame.setSize(970, 990); //1217 : 940
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(this).start();

    }

    @Override
    public void run() {
        BasicTimer basicTimer = new BasicTimer(60);
        pathfinder = new Astar();
        pathfinder.init();
        pathfinder.addNeighbour();

        while (true) {
            basicTimer.sync();
            update();
            render();
        }
    }

    private void update() {
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
        for (Node p : Astar.path) {
            g.setColor(Color.CYAN);
            g.fillRect(p.getX() * s, p.getY() * s, s, s);
        }
        for (Node w : Astar.wallset) {
            g.setColor(Color.BLACK);
            g.fillRect(w.getX() * s, w.getY() * s, s, s);
        }
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
        new Main.Driver();
    }
}
