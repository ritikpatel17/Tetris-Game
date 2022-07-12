
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class NextBlockDisplay extends JPanel{
    
    JPanel layout;
    TetrisBlock block;
    int gridCellSize;
    NextBlockDisplay(JPanel layout , int gridCellSize){
        this.layout = layout;
        this.setBounds(layout.getBounds());
        this.setBackground(layout.getBackground());
        this.setBorder(layout.getBorder());
        this.gridCellSize = gridCellSize;
        layout.setVisible(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBlock(g);
        //fillGrid(g);
    }
    private void drawBlock(Graphics g){
        int offSetX = getWidth() - (block.getWidth())*gridCellSize;
        int offSetY = getHeight() - (block.getHeight())*gridCellSize;
        for (int row = 0 ; row < block.getHeight() ; row++){
            for(int col = 0 ; col < block.getWidth(); col++){
                if(block.getShape()[row][col] == 1){
                    
                    int x = col*gridCellSize + offSetX/2;
                    int y = row*gridCellSize + offSetY/2;                    
                    drawGridSquare(x,y,block.getColor(),g);   
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
    void setBlock(TetrisBlock block){
        this.block = block;
        repaint();
    }    
}
