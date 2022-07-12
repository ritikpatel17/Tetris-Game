
package tetris;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x, y;
    
    private int[][][] shapes;
    private Color[] availableColors = {Color.red,
        Color.blue,
        Color.green,
        Color.yellow,
        Color.orange,
        Color.cyan
    };    
    private int currentRotation;    
    protected Point rotationPoint;
    
    //Constructor
    public TetrisBlock(int[][] shape){
        this.shape = shape;        
        initShapes();
        Random r = new Random();
        currentRotation = (int) (r.nextInt(4));       
        shape = shapes[currentRotation];
        color = availableColors[r.nextInt(availableColors.length)];        
    }
    
    private void initShapes(){
        shapes = new int[4][][];
        shapes[0] = shape;
        for(int i = 1 ; i < 4 ; i++){
            int r = shape[0].length;
            int c = shape.length;
            shapes[i] = new int[r][c];
            for(int y = 0 ; y < r ; y++){
                for(int x = 0 ; x < c ; x++){
                    shapes[i][y][x] = shape[c-x-1][y];
                }
            }
            shape = shapes[i];            
        }
    }
    
    public void spawn(int column){

        y = -getHeight();
        x = (column - getWidth())/2; 
    }      
    
    //getters
    public int getHeight(){return shape.length;}
    public int getWidth(){return shape[0].length;}
    public Color getColor(){return color;}
    public int[][] getShape(){return shape;}
    public int getX(){return x;}
    public int getY(){return y;}
    
    public int getBottomEdge(){return y + getHeight();}
    public int getLeftEdge(){return getX();}
    public int getRightEdge(){return (x + getWidth());}
    
    //setters
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}

    //Block Movement
    public void moveDown(){y++;}
    public void moveRight(){x++;}
    public void moveLeft(){x--;}
    public void rotate(){
        currentRotation++;
        if(currentRotation==4)currentRotation=0;
        shape = shapes[currentRotation];
    }
    public void unRotate(){
        rotate();
        rotate();
        rotate();
    }
}
