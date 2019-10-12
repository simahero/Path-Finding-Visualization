package Main.Mazegenerator;

import Main.Node;

public class RecursiveDivison {

    String orientation;

    public void chooseOrientation(int w, int h){
        if (w > h){
            orientation = "VERTICAL";
        } else if (w < h){
            orientation = "HORIZONTAL";
        } else {
            int n = (int)Math.round(Math.random()*2);
            if (n == 0){
                orientation = "VERTICAL";
            } else {
                orientation = "HORIZONTAL";
            }
        }
    }

    public void divison(Node[][] grid, int x, int y, int width, int height, String orientation){
        if (width < 2 || height < 2){
            return;
        } else {

        }

    }


}
