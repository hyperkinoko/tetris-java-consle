import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private final int SCORE_MAX = 999;
    private GameArea ga;
    private Mino mino;
    private int score = 0;
    private int waitTime = 1000;
    
    public GameThread(GameArea ga) {
        this.ga = ga;
        this.mino = new Mino();
    }

    public Mino getCurrentMino() {
        return this.mino;
    }

    public int getScore() {
        return score;
    }

    public void run() {
        int i = 0;
        while(true) {
            System.out.println(i);
            if(!mino.canMoveDown(ga)) {
                if(mino.getY() <= -2) {
                    break;
                }
                ga.fixMino(mino);
                if(ga.hasErasableLine()) {
                    score += Math.pow(ga.EraseLines(), 2);
                    if(score > SCORE_MAX) {
                        score = SCORE_MAX;
                    }
                }
                this.mino = new Mino();
            } else {
                mino.moveDown();
                ga.reflectMinoToFiled(mino);
            }
            ga.drawField(score);
            
            if(waitTime > 1) {
                waitTime--;
            }
            
            try {
                Thread.sleep(waitTime);
            } catch(InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        gameOver();
    }

    private void gameOver() {
        System.out.println("Game Over!!");
    }
}
