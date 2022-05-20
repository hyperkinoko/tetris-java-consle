import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private final int SCORE_MAX = 999;
    private GameArea ga;
    private Mino mino;
    private Mino nextMino;
    private String playerName;
    private int score = 0;
    private int waitTime = 1000;
    
    public GameThread(GameArea ga, String playerName) {
        this.ga = ga;
        this.mino = new Mino();
        this.nextMino = new Mino();
        this.playerName = playerName;
    }

    public Mino getCurrentMino() {
        return this.mino;
    }

    public Mino getNextMino() {
        return this.nextMino;
    }

    public String getPlayerName() {
        return this.playerName;
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
                this.mino = nextMino;
                this.nextMino = new Mino();
            } else {
                mino.moveDown();
                ga.reflectMinoToFiled(mino);
            }
            ga.drawField(score, nextMino, playerName);
            
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
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println();
        System.out.println("　　　　　　　Game Over!!");
        System.out.println();
        System.out.println(String.format("あなたのスコア: %3d", score));
        System.out.println();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println();
        System.out.println("　　　　　　　ランキング");
        System.out.println();
        for(Ranking ranking: rankingList) {
            
        }
        System.out.println(String.format("　　　あなたのスコア: %3d", score));
        System.out.println();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    }
}
