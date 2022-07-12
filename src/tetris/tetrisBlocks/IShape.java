package tetris.tetrisBlocks;

import java.awt.Point;
import tetris.TetrisBlock;

public class IShape extends TetrisBlock{
    public IShape(){
        super(new int[][]{{1,1,1,1}
    });
        super.rotationPoint = new Point(0,1);
    }    
}