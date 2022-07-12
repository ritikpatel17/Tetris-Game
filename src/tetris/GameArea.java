
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;
import tetris.tetrisBlocks.*;


public class GameArea extends JPanel{
    private int gridRows;
    private int gridColumns;    
    private final int gridCellSize;
    private TetrisBlock block;
    private Color[][] background;
    private TetrisBlock[] blocks ;
    NextBlockDisplay nbd;
    
    GameArea(JPanel gridLayout , int columns , NextBlockDisplay nbd){
        
        this.setBounds(gridLayout.getBounds());
        this.setBackground(gridLayout.getBackground());
        this.setBorder(gridLayout.getBorder());
        
        this.gridColumns = columns;
        this.gridCellSize = gridLayout.getBounds().width /columns;
        this.gridRows = gridLayout.getBounds().height / gridCellSize;
        background = new Color[gridRows][gridColumns];

        
        blocks = new TetrisBlock[] {new JShape(), 
                                    new LShape() ,
                                    new IShape() , 
                                    new OShape() , 
                                    new SShape() , 
                                    new ZShape() , 
                                    new TShape()};
        
        this.nbd = nbd;
        nbd.setBlock(blocks[(int)(Math.random()*blocks.length)]);
//        gridLayout.setVisible(false);
//        this.nbd.setVisible(false);
    }
    public void initBackgroundArray(){
        background = new Color[gridRows][gridColumns];   
    }
    public void spawnBlock() {
        Random r = new Random();
        block = nbd.block;
        nbd.setBlock(blocks[r.nextInt(blocks.length)]);
        block.spawn(gridColumns);
    }
    
    public boolean isBlockOutOfBounds(){
        if(block.getY()<0){
            block = null;
            return true;
        }        
        return false;
    }
    
    public boolean moveBlockDown(){
        
        if(!checkBottom()){
        return false;
        }
        
        block.moveDown();
        repaint();
        return true;
    }
    
    public void moveBlockRight(){
        if(block==null)return;
        if(!checkRight())return;
        block.moveRight();
        repaint();
    }
    
    public void moveBlockLeft(){
        if(block==null)return;
        if(!checkLeft())return;
        block.moveLeft();
        repaint();
    }
    
    public void dropBlock(){
        if(block==null)return;
        while(checkBottom())
            block.moveDown();
        repaint();
    }
    
    public void rotateBlock(){
        if(block==null)return;
        block.rotate();
        if(block.getLeftEdge()<0)block.setX(0); 
        if(block.getRightEdge() >= gridColumns)block.setX(gridColumns-block.getWidth());
        if(block.getBottomEdge() >= gridRows)block.setY(gridRows - block.getHeight());
        if(!checkRotation()){
            block.unRotate();return;
        }
        repaint();
    }
    //Checks for stopping block movements
    private boolean checkBottom(){
        if(block.getBottomEdge()== gridRows){
            return false;
        }
        
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int col = 0 ; col < w ;col++){
            for(int row =h-1 ; row >= 0 ; row--){
                if(shape[row][col]!= 0){
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if(y<0)break;
                    if(background[y][x] !=null)return false;
                    break;                    
                }
            }
        }
        return true;
    }
    private boolean checkLeft(){
        if(block.getLeftEdge()== 0){
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int row = 0 ; row < h ;row++){
            for(int col = 0 ; col < w ; col++){
                if(shape[row][col]!= 0){
                    int x = col + block.getX() -1;
                    int y = row + block.getY() ;
                    if(y<0)break;
                    if(background[y][x] !=null)return false;
                    break;                    
                }
            }
        }
        return true;
    }
    private boolean checkRight(){
        if(block.getRightEdge()== gridColumns){
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int row = 0 ; row < h ;row++){
            for(int col = w-1 ; col >= 0 ; col--){
                if(shape[row][col]!= 0){
                    int x = col + block.getX() +1;
                    int y = row + block.getY() ;
                    if(y<0)break;
                    if(background[y][x] !=null)return false;
                    break;                    
                }
            }
        }
        return true;
    }
    private boolean checkRotation(){       

        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        for(int r = 0 ; r < h ; r++){
            for(int c = 0 ; c < w ; c++){
                if(block.getY()<0)return false;
                if(shape[r][c]==1){
                    if (background[yPos + r][xPos + c] != null)
                    return false;
                }
            }
        }
        return true;
    }
    
    public int clearLines(){
        boolean lineFilled;
        int linesCleared = 0;
        for(int r = gridRows -1 ; r >=0 ;r--){
            lineFilled = true;
            for(int c = 0 ; c < gridColumns ; c++){
                if(background[r][c]==null){
                    lineFilled=false;break;
                }
            }
            if(lineFilled){
                linesCleared++;
                clearLine(r);
                shiftDown(r);
                clearLine(0);
                repaint();
                r++;
            }
        }
        if(linesCleared>0){
            Tetris.playClear();
        }
        
        return linesCleared;
    }
    
    private void clearLine(int r){
        for(int i = 0 ; i < gridColumns ; i++){
            background[r][i]=null;
        }
    }
    private void shiftDown(int r){
        for(int row = r ; row > 0 ; row--){
            for(int col = 0 ; col <gridColumns ; col++){
                background[row][col] = background[row-1][col];
            }
        }
    }
    
    public void moveBlockToBackground(){
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        Color color = block.getColor();
        
        for (int r = 0 ; r < h ; r++){
            for(int c = 0 ; c < w ; c++){
                if(shape[r][c] == 1){
                    background[r + yPos][c + xPos] = color;
                }                
            }
        }          
    }
    
    private void drawBlock(Graphics g , TetrisBlock block) {
        for (int row = 0 ; row < block.getHeight() ; row++){
            for(int col = 0 ; col < block.getWidth(); col++){
                if(block.getShape()[row][col] == 1){
                    
                    int x = (col + block.getX())*gridCellSize;
                    int y = (row + block.getY())*gridCellSize;
                    
                    drawGridSquare(x,y,block.getColor(),g);   
                }                
            }
        }      
    }
    private void drawBackground(Graphics g) {
        
        Color color;
        for (int row = 0 ; row < gridRows; row++){
            for(int col = 0 ; col < gridColumns; col++){                
                color = background[row][col];
                
                if(color != null){
                    
                    int x = (col)*gridCellSize;
                    int y = (row)*gridCellSize;
                    
                    drawGridSquare(x,y,color,g);
  
                }                
            }
        }    
    }
        private void drawGridSquare(int x , int y , Color color , Graphics g) {
                    g.setColor(color);
                    g.fillRect(x,y, gridCellSize , gridCellSize);
                    g.setColor(Color.BLACK);
                    g.drawRect(x,y, gridCellSize , gridCellSize);    
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        drawBackground(g);
        drawBlock(g,block);
        //fillGrid(g);
    }
}


