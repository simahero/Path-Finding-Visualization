package Main.Algorithms;

import Main.Node;

import java.util.ArrayList;

import static Main.Driver.xMAX;
import static Main.Driver.yMAX;


public abstract class Pathfinding{

    static public Node start;
    static public Node end;
    static public Node[][] list = new Node[xMAX][yMAX];
    static public ArrayList<Node> openset = new ArrayList<>();
    static public ArrayList<Node> closedset = new ArrayList<>();
    static public ArrayList<Node> path = new ArrayList<>();
    static public ArrayList<Node> wallset = new ArrayList<>();

    public abstract void search();

    public abstract void addWall();

    public abstract void init();

    public abstract void addNeightbour();

    public abstract void getPath(Node end);




}