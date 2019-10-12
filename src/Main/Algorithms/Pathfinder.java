package Main.Algorithms;

import Main.Driver;
import Main.EventHandlers.ButtonHandler;
import Main.Node;
import java.util.ArrayList;
import static Main.Driver.xMAX;
import static Main.Driver.yMAX;


public abstract class Pathfinder {

    //BASIC VALUES FOR PATHFINDER
    static public Node start;
    static public Node end;
    static public Node[][] list = new Node[xMAX][yMAX];
    static public ArrayList<Node> openset = new ArrayList<>();
    static public ArrayList<Node> closedset = new ArrayList<>();
    static public ArrayList<Node> path = new ArrayList<>();
    static public ArrayList<Node> wallset = new ArrayList<>();

    public abstract void init();

    //DELETE THE NEIGHBOURS
    public static void deleteNeightbours(){
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                ArrayList<Node> n = list[i][j].neightbours;
                n.clear();
            }
        }
    }

    //ADDING NEIGHBOURS IF THEY AREN'T WALLS
    public void addNeighbour() {
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
            }
        }
    }

    //ADDING NEIGHBOURS DIAGONAL IF THEY AREN'T WALLS
    public void addNeighbourDiagnal(){
        for (int i = 0; i < xMAX; i++) {
            for (int j = 0; j < yMAX; j++) {
                ArrayList<Node> n = list[i][j].neightbours;
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

    //MAIN METHOD FOR FINDING THE PATH
    public abstract void search();

    //RECURSIVELY GETTING THE PATH
    public void getPath(Node end) {
        Node temp = end;
        path.add(temp);
        while (temp.cameFrom != null) {
            path.add(temp.cameFrom);
            temp = temp.cameFrom;
        }
    }

    //ADDING WALLS
    public static void addWall(Node notWall){
        notWall.isWall = true;
        wallset.add(notWall);
    }

    //REMOVING WALLS
    public static void removeWall(Node wall){
        wall.isWall = false;
        wallset.remove(wall);

    }

    //RESET ALL THE VALUES
    public static void reset(){
        openset.clear();
        closedset.clear();
        path.clear();
        wallset.clear();
    }

    //RESET ALL THE VALUES
    public static void wipeboard(){
        openset.clear();
        closedset.clear();
        path.clear();
        for (Node n : wallset){
            n.isWall = false;
        }
        wallset.clear();
        openset.add(start);
        deleteNeightbours();
    }

    //ADD WALLS BY CLICKING
    public static void addClickedWall(int x, int y){
        if (list[x][y].isWall){
            removeWall(list[x][y]);
        } else {
            addWall(list[x][y]);
        }
    }

    // SELECTING THE ALGORITHM
    public static void selectItem(){
        Driver.pathfinder = null;
        if (ButtonHandler.selectedItem.equals("A* algorithm")) {
            Driver.pathfinder = new Astar();
        } else if (ButtonHandler.selectedItem.equals("Dijkstra's algorithm")) {
            Driver.pathfinder = new Dijkstra();
        }
    }

}