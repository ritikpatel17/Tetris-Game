package tetris.tetrisBlocks;

import java.awt.Point;
import tetris.TetrisBlock;

public class OShape extends TetrisBlock{
    public OShape(){
        super(new int[][]{  {1,1},
                            {1,1}
    });
        super.rotationPoint = new Point(0,0);
    }        
}