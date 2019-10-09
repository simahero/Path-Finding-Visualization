package Main;

import java.util.ArrayList;
import java.util.Random;

import static Main.Driver.xMAX;
import static Main.Driver.yMAX;

public class MazeGenerator {

    public MazeGenerator(Node[][] n) {
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                Random r = new Random();
                if (!n[i][j].isVisited()) {
                    notvisited.add(n[i][j]);
                    double d = r.nextDouble();
                    if (d < 0.9) {
                        Astar.list[i][j].isWall = true;
                        Astar.wallset.add(Astar.list[i][j]);
                    }
                }
            }
        }
        addSecondNeightbour();
    }

    static ArrayList<Node> visited = new ArrayList<>();
    static ArrayList<Node> notvisited = new ArrayList<>();

    public void init() {
        Node current = Astar.start;
        current.visited = true;
        visited.add(current);
        notvisited.remove(current);
        for (int i = 0; i < current.secondNeightbours.size();) {
            if (notvisited.contains(current.secondNeightbours.get(i))) {
                Node next = current.secondNeightbours.get(i);
                next.visited = true;
                visited.add(next);
                notvisited.remove(next);
                removeWall(current, next);
                current = next;
            } else {
                i++;
            }
        }
    }


    public static void addSecondNeightbour() {
        for (int i = 0; i < xMAX; i = i + 2) {
            for (int j = 0; j < yMAX; j = j + 2) {
                ArrayList<Node> n = Astar.list[i][j].secondNeightbours;


                if (i < xMAX - 2) {
                    n.add(Astar.list[i + 2][j]);
                }

                if (i > 1) {
                    n.add(Astar.list[i - 2][j]);
                }

                if (j < yMAX - 2) {
                    n.add(Astar.list[i][j + 2]);
                }

                if (j > 1) {
                    n.add(Astar.list[i][j - 2]);
                }
            }
        }
    }

    public void removeWall(Node a, Node b) {
        int x;
        int y;

        if (a.getX() < b.getX()){
            x = a.getX() + 1;
        } else if (a.getX() > b.getX()){
            x = b.getX() + 1;
        } else {
            x = a.getX();
        }

        if (a.getY() < b.getY()){
            y = a.getY() + 1;
        } else if (a.getY() > b.getY()){
            y = b.getY() + 1;
        } else {
            y = a.getY();
        }


        Astar.list[x][y].isWall = false;
        Astar.wallset.remove(Astar.list[x][y]);
        a.isWall = false;
        b.isWall = false;
        Astar.wallset.remove(a);
        Astar.wallset.remove(b);
    }

}
