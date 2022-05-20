import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    private final int SCORE_MAX = 999;
    private GameArea ga;
    private Mino mino;
    private Mino nextMino;
    private String playerName;
    private int score = 567;
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
        System.out.println("　　　　　Game Over!!");
        System.out.println();
        System.out.println(String.format("　　　あなたのスコア: %3d", score));
        System.out.println();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println();
        System.out.println("　　　　　ランキング");
        System.out.println();

        Ranking[] rankingList = new Ranking[10];
        rankingList[0] = new Ranking(123, 789, "キノコ");
        rankingList[1] = new Ranking(2, 789, "nktr");
        rankingList[2] = new Ranking(1, 789, "村田");
        rankingList[3] = new Ranking(34, 567, "Kinoko");
        rankingList[4] = new Ranking(21, 567, "tomotomo");
        rankingList[5] = new Ranking(13, 567, "YOSHIKI");
        rankingList[6] = new Ranking(32, 456, "yosuke");
        rankingList[7] = new Ranking(12, 345, "DAIDAI");
        rankingList[8] = new Ranking(3, 234, "moriya");
        rankingList[9] = new Ranking(85, 123, "キノコ");

        int rank = 0;
        while(rank < 10) {
            if(rankingList[rank].getScore() == score && rankingList[rank].getName().equals(playerName)) {
                break;
            }
            rank++;
        }
        System.out.println("　　あなたは" + (rank < 10?rank+1+"位":"ランク外") + "でした！！");
        System.out.println();

        for(int i = 0; i < rankingList.length; i++) {
            Ranking ranking = rankingList[i];
            String yourRankIsHere = (ranking.getScore() == score && ranking.getName().equals(playerName)) ? " <-- YOU!!" : "";
            System.out.println(String.format("　　 %2d位: %s %s", i+1, ranking, yourRankIsHere));
        }
        System.out.println();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    }
}
