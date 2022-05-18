import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private GameArea ga;
    private Mino mino;
    private int score;
    private int waitTime = 1000;

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
                if(ga.hasErasableLine()) {
                    score += ga.EraseLines();
                    waitTime -= 10;
                }
                this.mino = new Mino();
            } else {
                mino.moveDown();
                ga.reflectMinoToFiled(mino);
            }
            ga.drawField();

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
        System.out.println("あなたのスコア： " + score);
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        Score[] rankingData = APIClient.getRanking(new Score(score, "kinoko"));
        for(Score ranking : rankingData) {
            System.out.println(ranking);
        }
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    }
}
