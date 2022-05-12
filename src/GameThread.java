import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private GameArea ga;
    private Mino mino;

    public GameThread(GameArea ga) {
        this.ga = ga;
        this.mino = new Mino();
    }

    public Mino getCurrentMino() {
        return this.mino;
    }

    public void run() {
        while(true) {
            if(!mino.canMoveDown(ga)) {
                if(mino.getY() <= -2) {
                    break;
                }
                ga.fixMino(mino);
                if(ga.hasErasableLine()) {
                    // スコアを計算する場合はこの戻り値を使う
                    // int lineCount = ga.eraseLines();
                    ga.eraseLines();
                }
                this.mino = new Mino();
            } else {
                mino.moveDown();
                ga.reflectMinoToFiled(mino);
            }
            ga.drawField();

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
