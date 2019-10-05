package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import static Main.Pathfinding.*;

public class Driver implements Runnable {

    private JFrame frame;
    private static Canvas canvas;

    Pathfinding pathfinding;

    public int s = 50;
    public static int xMAX = 24;
    public static int yMAX = 18;

    public Driver() throws IOException {
        frame = new JFrame("Pathfinding");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas = new Canvas());
        frame.setSize(1217, 940);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(this).start();

    }


    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        for (Entities e : openset){
            g.setColor(Color.GREEN);
            g.drawRect(e.getX(), e.getY(), s, s);
        }
        for (Entities f : closedset){
            g.setColor(Color.GREEN);
            g.drawRect(f.getX(), f.getY(), s, s);
        }
        for (Entities w : wallset){
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
        for (int i = 0; i < yMAX; i++) {
            for (int j = 0; j < xMAX; j++) {
                Entities.list[i][j] = new Entities(false, i, j, 10, Integer.MAX_VALUE, 0);
            }
        }

        pathfinding = new Pathfinding();
    }

    public void update() {
        //pathfinding.update();
        wallset.add(new Entities(false, 5, 6, 0, 0, 0));
    }

    @Override
    public void run() {
        BasicTimer timer = new BasicTimer(6000);
        initialize();
        while (true) {
            timer.sync();
            render();
            update();
        }
    }


    public static void main(String[] args) throws IOException {
        new Main.Driver();
    }

}
