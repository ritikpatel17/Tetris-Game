package tetris.tetrisBlocks;

import java.awt.Point;
import tetris.TetrisBlock;

public class ZShape extends TetrisBlock{
    public ZShape(){
        super(new int[][]{  {1,1,0},
                            {0,1,1}
    });
        super.rotationPoint = new Point(1,1);
    }        
}