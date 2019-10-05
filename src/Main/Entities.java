package Main;

import java.awt.*;
import java.util.ArrayList;

public class Entities {

    private boolean isWall;
    private int x;
    private int y;
    private int fScore;
    private int gScore;
    ArrayList<Entities> neightbours = new ArrayList<>();
    Entities cameFrom;
    static Entities[][] list = new Entities[Driver.yMAX][Driver.xMAX];

    public Entities(boolean isWall, int x, int y, int fScore, int gScore, int hScore) {
        this.isWall = isWall;
        this.x = x;
        this.y = y;
        this.fScore = fScore;
        this.gScore = gScore;
        this.hScore = hScore;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public void setfScore(int fScore) {
        this.fScore = fScore;
    }

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public void sethScore(int hScore) {
        this.hScore = hScore;
    }

    private int hScore;

    public boolean isWall() {
        return isWall;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getfScore() {
        return fScore;
    }

    public int getgScore() {
        return gScore;
    }

    public int gethScore() {
        return hScore;
    }
}
