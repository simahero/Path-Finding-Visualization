package Main.Mategenerator;

import Main.Algorithms.Astar;
import Main.Algorithms.Pathfinder;
import Main.Node;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

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
                    if (d < 0) {
                        Pathfinder.addWall(n[i][j]);
                    }
                }
            }
        }
        addSecondNeightbour();
    }

    static public ArrayList<Node> visited = new ArrayList<>();
    static public ArrayList<Node> notvisited = new ArrayList<>();
    static public Stack<Node> stack = new Stack<>();

    public void init() {
        Node current = Astar.start;
        current.visited = true;
        visited.add(current);
        notvisited.remove(current);
        //stack.push(current);
        for (int i = 0; i < 100000; i++){
            if (current.secondNeightbours.size() != 0) {
                int random = (int) Math.round(Math.random() * current.secondNeightbours.size());
                int randomindex = random;
                if (random < 0) {
                    randomindex = random + 1;
                } else if (random == current.secondNeightbours.size()) {
                    randomindex = random - 1;
                }
                Node next = current.secondNeightbours.get(randomindex);
                stack.push(current);
                removeWall(current, next);
                next.visited = true;
                visited.add(next);
                notvisited.remove(next);
                current = next;
            } else if (stack.size() > 0) {
                current = stack.pop();
            }

        }
    }



    public static void addSecondNeightbour() {
        for (int i = 0; i < xMAX; i = i + 3) {
            for (int j = 0; j < yMAX; j = j + 3) {
                ArrayList<Node> n = Astar.list[i][j].secondNeightbours;


                if (i < xMAX - 3) {
                    n.add(Astar.list[i + 3][j]);
                }

                if (i > 2) {
                    n.add(Astar.list[i - 3][j]);
                }

                if (j < yMAX - 3) {
                    n.add(Astar.list[i][j + 3]);
                }

                if (j > 2) {
                    n.add(Astar.list[i][j - 3]);
                }
            }
        }
    }

    public void removeWall(Node a, Node b) {
        int x;
        int y;

        if (a.getX() < b.getX()) {
            x = a.getX() + 1;
        } else if (a.getX() > b.getX()) {
            x = b.getX() + 1;
        } else {
            x = a.getX();
        }

        if (a.getY() < b.getY()) {
            y = a.getY() + 1;
        } else if (a.getY() > b.getY()) {
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

        if (a.getX() < b.getX()) {
            x = a.getX() + 2;
        } else if (a.getX() > b.getX()) {
            x = b.getX() + 2;
        } else {
            x = a.getX();
        }

        if (a.getY() < b.getY()) {
            y = a.getY() + 2;
        } else if (a.getY() > b.getY()) {
            y = b.getY() + 2;
        } else {
            y = a.getY();
        }
        Astar.list[x][y].isWall = false;
        Astar.wallset.remove(Astar.list[x][y]);
    }

}
