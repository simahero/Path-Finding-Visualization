package Main;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;

public class Pathfinding {

    static ArrayList<Entities> openset = new ArrayList<>();
    static ArrayList<Entities> closedset = new ArrayList<>();
    static ArrayList<Entities> wallset = new ArrayList<>();
    static ArrayList<Entities> path = new ArrayList<>();


    public void update() {

        Entities current = Entities.list[0][0];
        //Entities end = Entities.list[Driver.yMAX - 1][Driver.xMAX - 1];
        Entities end = Entities.list[2][2];

        if (current == end) {
            getPath();
            Driver.running = false;
        } else {
            int winner = 0;
            for (int i = 0; i < openset.size(); i++) {
                if (openset.get(i).getfScore() < openset.get(winner).getfScore()) {
                    winner = i;
                }
                current = openset.get(winner);
                closedset.add(current);
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
    }


    public static ArrayList<Entities> addNeightbour(ArrayList<Entities> h, int i, int j) {
        if (i < Driver.yMAX) {
            h.add(Entities.list[i + 1][j]);
        }
        if (i > 0) {
            h.add(Entities.list[i - 1][j]);
        }
        if (j < Driver.xMAX) {
            h.add(Entities.list[i][j + 1]);
        }
        if (j > 0) {
            h.add(Entities.list[i][j - 1]);
        }
        return h;

    }

    public static ArrayList<Entities> getPath(){
        Entities temp = Entities.list[Driver.yMAX - 1][Driver.xMAX - 1];
        path.add(temp);
        while (temp.cameFrom != null){
            path.add(temp.cameFrom);
            temp = temp.cameFrom;
        }
        return path;
    }


}

