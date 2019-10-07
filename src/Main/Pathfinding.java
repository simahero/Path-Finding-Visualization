package Main;

import java.util.ArrayList;

import static Main.Driver.*;
import static Main.Entities.*;

public class Pathfinding {

    static ArrayList<Entities> openset = new ArrayList<>();
    static ArrayList<Entities> closedset = new ArrayList<>();
    static ArrayList<Entities> wallset = new ArrayList<>();
    static ArrayList<Entities> path = new ArrayList<>();

    static Entities start;
    static Entities end = list[4][4];
    static Entities current = null;


    public void update() {
        openset.add(list[5][5]);
        current = list[5][5];

        while (openset.size() != 0) {
            int winner = 0;
            current = openset.get(winner);
            for (int i = 0; i < openset.size(); i++) {
                if (openset.get(i).getfScore() < openset.get(winner).getfScore()) {
                    winner = i;
                }
                openset.remove(current);
                closedset.add(current);

                for (int j = 0; j < current.neightbours.size(); j++) {
                    if (closedset.contains(current.neightbours.get(j)) || current.neightbours.get(j).isWall()) {
                        continue;
                    } else {
                        if (!openset.contains(current.neightbours.get(j))) {
                            openset.add(current.neightbours.get(j));

                            int tempfscore = current.getfScore() + current.neightbours.get(j).getgScore();
                            if (tempfscore < current.neightbours.get(j).getfScore()) {
                                current.neightbours.get(j).setfScore(tempfscore);
                                current.neightbours.get(j).cameFrom = current;

                            }
                        }
                    }
                }

            }
        }
    }








        /*
        openset.add(Entities.list[0][0]);
        Entities current = Entities.list[0][0];
        Entities end = Entities.list[Driver.yMAX - 1][Driver.xMAX - 1];
        //Entities end = Entities.list[2][2];

        if (current == end) {
            running = false;
            getPath();
        } else if (!openset.isEmpty()) {
            running = true;
            int winner = 0;
            for (int i = 0; i < openset.size(); i++) {
                if (openset.get(i).getfScore() < openset.get(winner).getfScore()) {
                    winner = i;
                }
                current = openset.get(winner);
                closedset.add(current);
                openset.remove(winner);
                for (int j = 0; j < openset.size(); j++) {
                    if (openset.get(j) == current) {
                        openset.remove(i);
                    }
                }
                for (int k = 0; k < current.neightbours.size(); k++) {
                    if (closedset.contains(current.neightbours.get(k))) {
                        continue;
                    } else {
                        if (!openset.contains(current.neightbours.get(k))) {
                            int tempf = current.getfScore() + current.neightbours.get(k).getgScore();
                            openset.add(current.neightbours.get(k));
                            if (current.neightbours.get(k).getfScore() > tempf) {
                                current.neightbours.get(k).setfScore(tempf);
                                current.neightbours.get(k).cameFrom = current;
                            }
                        }
                    }
                }

            }
        }

         */


    public static ArrayList<Entities> addNeightbour(ArrayList<Entities> h, int i, int j) {
        if (i < Driver.yMAX) {
            h.add(Entities.list[i + 1][j]);
        }
        if (i > 0) {
            h.add(Entities.list[i - 1][j]);
        }
        if (j < xMAX) {
            h.add(Entities.list[i][j + 1]);
        }
        if (j > 0) {
            h.add(Entities.list[i][j - 1]);
        }
        return h;

    }

    public static void getPath() {
        Entities temp = list[10][15];
        path.add(temp);
        while (temp.cameFrom != null) {
            path.add(temp.cameFrom);
            temp = temp.cameFrom;
        }
    }

    public static void initialize() {
        for (int i = 0; i < yMAX; i++) {
            for (int j = 0; j < xMAX; j++) {
                Entities.list[i][j] = new Entities(false, j, i, Integer.MAX_VALUE, 10, Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < yMAX; i++) {
            for (int j = 0; j < xMAX; j++) {
                if (list[i][j].isWall()) {
                    wallset.add(list[i][j]);
                }
            }
        }
        Entities.list[0][0].setfScore(0);
        for (int i = 0; i < yMAX - 1; i++) {
            for (int j = 0; j < xMAX - 1; j++) {
                Entities.list[i][j].neightbours = addNeightbour(Entities.list[i][j].neightbours, i, j);
            }
        }
    }

    public static void addWall(int x, int y){
        if (!list[y][x].isWall) {
            list[y][x].setWall(true);
            wallset.add(list[y][x]);
        } else {
            list[y][x].setWall(false);
            wallset.remove(list[y][x]);
        }
    }

    public static void reset(){
        openset.removeAll(openset);
        closedset.removeAll(closedset);
        path.removeAll(path);
        openset.add(list[5][5]);
        current = list[5][5];
    }


}

