package tetris.tetrisBlocks;

import java.awt.Point;
import tetris.TetrisBlock;

public class SShape extends TetrisBlock{
    public SShape(){
        super(new int[][]{  {0,1,1},
                            {1,1,0}
    });
        super.rotationPoint = new Point(1,1);
    }        
}