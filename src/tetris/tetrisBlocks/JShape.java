package tetris.tetrisBlocks;

import java.awt.Point;
import tetris.TetrisBlock;

public class JShape extends TetrisBlock{
    public JShape(){
        super(new int[][]{  {0,1},
                            {0,1},
                            {1,1}
    });
        super.rotationPoint = new Point(2,1);
    }
}