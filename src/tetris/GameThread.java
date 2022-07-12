package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread 
{
    private GameArea ga;  
    private GameForm gf;
    
    private int linesCleared;
    private int level;
    
    public GameThread(GameArea ga, GameForm gf)
    {
        this.ga = ga;
        this.gf = gf;
        gf.updateScore(linesCleared);
        gf.updateLevel(level);
    }
    
    @Override
    public void run()
    {
        while (true) 
        {
            ga.spawnBlock();            
            while(ga.moveBlockDown())
            try {
                Thread.sleep(100 + (5-level)*60);
            } 
            catch (InterruptedException ex) {
                return;
            }
            if(ga.isBlockOutOfBounds()){
                Tetris.gameOver(linesCleared);
                break;
            }
            ga.moveBlockToBackground();
            linesCleared+=ga.clearLines();
            gf.updateScore(linesCleared);
            level = (int)(linesCleared/4);
            gf.updateLevel(level);
        }        
    }
}