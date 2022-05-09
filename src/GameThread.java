import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private GameArea ga;
    private Mino mino;

    public GameThread(GameArea ga, Mino mino) {
        this.ga = ga;
        this.mino = mino;
    }

    public void run() {
        int i = 0;
        while(true) {
            System.out.println(i);
            mino.moveDown();
            ga.reflectMinoToFiled(mino);
            ga.drawField();
            i++;

            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
