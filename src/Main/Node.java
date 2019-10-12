package Main;

import java.util.ArrayList;

public class Node {


    /*******************************************
     *        BASIC VALUES OF A NODE
     *******************************************/
    public double gScore;
    public double hScore;
    public double fScore;
    public int x;
    public int y;
    public Node cameFrom;
    public boolean isWall;
    public boolean visited;
    public ArrayList<Node> neightbours = new ArrayList<>();
    public ArrayList<Node> secondNeightbours = new ArrayList<>();

    //CONSTRUCTOR
    public Node(int gScore, int fScore, int x, int y, boolean isWall) {
        this.gScore = gScore;
        this.fScore = fScore;
        this.x = x;
        this.y = y;
        this.isWall = isWall;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
