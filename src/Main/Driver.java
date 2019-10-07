package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;

import static Main.Entities.list;

public class Driver implements Runnable {

    private JFrame frame;
    static Graphics g;
    private static Canvas canvas;
    private static JPanel panel = new JPanel();
    Pathfinding pathfinding;
    static boolean running;
    MouseHandler mh = new MouseHandler();

    public static int s = 50;
    public static int xMAX = 24;
    public static int yMAX = 18;

    public Driver() throws IOException {
        frame = new JFrame("Pathfinding");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas = new Canvas());
        canvas.addMouseListener(mh);
        frame.setSize(1217, 940);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(this).start();

    }

    public static void setRunning(boolean a){
        running = a;
    }


    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, xMAX, yMAX);
        for (Entities e : Pathfinding.openset){
            g.setColor(Color.GREEN);
            g.fillRect(e.getX()*s, e.getY()*s, s, s);
        }
        for (Entities f : Pathfinding.closedset){
            g.setColor(Color.RED);
            g.fillRect(f.getX()*s, f.getY()*s, s, s);
        }
        for (Entities p : Pathfinding.path){
            g.setColor(Color.CYAN);
            g.fillRect(p.getX()*s, p.getY()*s, s, s);
        }
        for (Entities w : Pathfinding.wallset){
            g.setColor(Color.BLACK);
            g.fillRect(w.getX()*s, w.getY()*s, s, s);
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                g.drawRect(i * s, j*s, s, s);
            }
        }
        g.dispose();
        bs.show();
    }

    public void initialize() {
        Pathfinding.initialize();
        pathfinding = new Pathfinding();
    }

    public void update() {
        pathfinding.update();
    }

    @Override
    public void run() {
        BasicTimer timer = new BasicTimer(240);
        initialize();
        while (true) {
            timer.sync();
            render();
            update();
            System.out.println(Pathfinding.path.size());
            if (Pathfinding.openset.size() == 0){
                Pathfinding.getPath();
            }
            for (int i = 0; i < Pathfinding.path.size(); i++){
                if (Pathfinding.path.get(i).isWall){
                    Pathfinding.openset.removeAll(Pathfinding.openset);
                    Pathfinding.closedset.removeAll(Pathfinding.closedset);
                    Pathfinding.path = null;
                    Pathfinding.openset.add(list[5][5]);
                    Pathfinding.current = list[5][5];
                    Thread.currentThread().interrupt();
                    new Thread(this).start();
                    Pathfinding.getPath();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main.Driver();
    }

}
