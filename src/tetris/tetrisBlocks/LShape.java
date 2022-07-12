
package tetris.tetrisBlocks;

import java.awt.Point;
import tetris.TetrisBlock;

public class LShape extends TetrisBlock{
    public LShape(){
        super(new int[][]{  {1,0},
                            {1,0},
                            {1,1}
    });
        super.rotationPoint = new Point(2,0);
    }        
}

