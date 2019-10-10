package Main.Algorithms;

import Main.Node;
import static Main.Driver.xMAX;
import static Main.Driver.yMAX;

public class Astar extends Pathfinder {

    @Override
    public void init() {
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                list[i][j] = new Node(10, Integer.MAX_VALUE, i, j, false);


            }
        }
        start = list[0][0];
        end = list[xMAX - 1][yMAX - 1];
        removeWall(start);
        removeWall(end);
        start.fScore = 0;
        start.hScore = heuristic(start, end);
        openset.add(start);

    }

    @Override
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

    private static double heuristic(Node a, Node b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
}
