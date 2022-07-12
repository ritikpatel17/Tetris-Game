package tetris.tetrisBlocks;

import java.awt.Point;
import tetris.TetrisBlock;

public class TShape extends TetrisBlock{
    public TShape(){
        super(new int[][]{  {1,1,1},
                            {0,1,0}
    });
        super.rotationPoint = new Point(0,1);
    }
}