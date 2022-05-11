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
        int i = 0;
        while(true) {
            System.out.println(i);
            if(!mino.canMoveDown(ga)) {
                if(mino.getY() <= -2) {
                    break;
                }
                ga.fixMino(mino);
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
