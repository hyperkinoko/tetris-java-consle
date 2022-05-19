import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private GameArea ga;
    private Mino mino;
    private int score;

    public GameThread(GameArea ga) {
        this.ga = ga;
        this.mino = new Mino();
        this.score = 997;
    }

    public Mino getCurrentMino() {
        return this.mino;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
                    if(score > 999) {
                        score = 999;
                    }
                }
                this.mino = new Mino();
            } else {
                mino.moveDown();
                ga.reflectMinoToFiled(mino);
            }
            ga.drawField(score);

            try {
                Thread.sleep(1000);
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
