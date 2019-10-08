package Main;

import java.util.ArrayList;
import java.util.Random;

import static Main.Driver.xMAX;
import static Main.Driver.yMAX;

public class Astar implements Pathfinding {

    static Node start;
    static Node end;
    static Node[][] list = new Node[xMAX][yMAX];
    static ArrayList<Node> openset = new ArrayList<>();
    static ArrayList<Node> closedset = new ArrayList<>();
    static ArrayList<Node> path = new ArrayList<>();
    static ArrayList<Node> wallset = new ArrayList<>();


    public static void init() {
        Random r = new Random();
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                list[i][j] = new Node(10, Integer.MAX_VALUE, i, j);
                double d = r.nextDouble();
                if (d < 0.4) {
                    list[i][j].isWall = true;
                    wallset.add(list[i][j]);
                }
            }
        }
        start = list[0][0];
        end = list[xMAX - 1][yMAX - 1];
        start.isWall = false;
        wallset.remove(start);
        end.isWall = false;
        wallset.remove(end);
        start.fScore = 0;
        start.hScore = heuristic(start, end);
        openset.add(start);

    }

    public void search() {
        if (openset.size() > 0) {
            int winner = 0;
            for (int i = 0; i < openset.size(); i++) {
                if (openset.get(i).fScore < openset.get(winner).fScore) {
                    winner = i;
                }
            }
            Node current = openset.get(winner);
            if (current == end) {
                getPath(end);
            } else {
                closedset.add(current);
                openset.remove(current);

                for (int k = 0; k < current.neightbours.size(); k++) {
                    Node nghtbr = current.neightbours.get(k);
                    if (closedset.contains(nghtbr)) {
                        continue;
                    }
                    double tempG = current.gScore + heuristic(nghtbr, current);
                    if (!openset.contains(nghtbr)) {
                        openset.add(nghtbr);
                    } else if (tempG >= nghtbr.gScore) {
                        continue;
                    }
                    nghtbr.cameFrom = current;
                    nghtbr.gScore = tempG;
                    nghtbr.fScore = nghtbr.gScore + heuristic(nghtbr, end);
                }
            }
        }
    }

    public void getPath(Node end) {
        Node temp = end;
        path.add(temp);
        while (temp.cameFrom != null) {
            path.add(temp.cameFrom);
            temp = temp.cameFrom;
        }
    }

    @Override
    public void addWall() {

    }

    public static void addNeightbour() {
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                ArrayList<Node> n = list[i][j].neightbours;


                if (i < xMAX - 1) {
                    if (!list[i + 1][j].isWall) {
                        n.add(list[i + 1][j]);
                    }
                }
                if (i > 0) {
                    if (!list[i - 1][j].isWall) {
                        n.add(list[i - 1][j]);
                    }
                }
                if (j < yMAX - 1) {
                    if (!list[i][j + 1].isWall) {
                        n.add(list[i][j + 1]);
                    }
                }
                if (j > 0) {
                    if (!list[i][j - 1].isWall) {
                        n.add(list[i][j - 1]);
                    }
                }
                if (i < xMAX - 1 && j < yMAX - 1) {
                    if (!list[i + 1][j + 1].isWall) {
                        n.add(list[i + 1][j + 1]);
                    }
                }
                if (i > 0 && j < yMAX - 1) {
                    if (!list[i - 1][j + 1].isWall) {
                        n.add(list[i - 1][j + 1]);
                    }
                }
                if (i < xMAX - 1 && j > 0) {
                    if (!list[i + 1][j - 1].isWall) {
                        n.add(list[i + 1][j - 1]);
                    }
                }
                if (j > 0 && i > 0) {
                    if (!list[i - 1][j - 1].isWall) {
                        n.add(list[i - 1][j - 1]);
                    }
                }
            }
        }
    }

    public static double heuristic(Node a, Node b) {
        double h = Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
        return h;
    }
}
