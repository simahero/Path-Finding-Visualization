package Main;

import java.util.ArrayList;

public class Pathfinding {

    static ArrayList<Entities> openset = new ArrayList<>();
    static ArrayList<Entities> closedset = new ArrayList<>();
    static ArrayList<Entities> wallset = new ArrayList<>();


    public void update() {

        Entities.list[0][0].setfScore(0);
        openset.add(Entities.list[0][0]);
        Entities current = Entities.list[0][0];

        while (current != Entities.list[Driver.yMAX-1][Driver.xMAX-1]) {
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
}
