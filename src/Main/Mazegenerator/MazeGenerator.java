package Main.Mazegenerator;

import Main.Algorithms.Pathfinder;
import Main.Node;
import java.util.Random;
import static Main.Driver.xMAX;
import static Main.Driver.yMAX;

public class MazeGenerator {

    public static double e = 0.3;

    //SPANNING RANDOM WALLS
    public void addRandomWalls(Node[][] n) {
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                Random r = new Random();
                double d = r.nextDouble();
                if (d < e) {
                    Pathfinder.addWall(n[i][j]);
                }
            }
        }
        Pathfinder.removeWall(n[0][0]);
        Pathfinder.removeWall(n[xMAX - 1][yMAX - 1]);
    }
}
