package Main;

import java.util.ArrayList;

public class Node {

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

    public Node(int gScore, int fScore, int x, int y, boolean visited) {
        this.gScore = gScore;
        this.fScore = fScore;
        this.x = x;
        this.y = y;
        this.visited = visited;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public void sethScore(int hScore) {
        this.hScore = hScore;
    }

    public void setfScore(int fScore) {
        this.fScore = fScore;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public void setNeightbours(ArrayList<Node> neightbours) {
        this.neightbours = neightbours;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getCameFrom() {
        return cameFrom;
    }

    public boolean isWall() {
        return isWall;
    }

    public ArrayList<Node> getNeightbours() {
        return neightbours;
    }

}
