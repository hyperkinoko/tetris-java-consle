import java.util.Random;

public class Mino {
    public static final int MINO_SIZE = 4;

    private int x;
    private int y;
    private int angle;
    private MinoType minoType;

    public Mino() {
        Random r = new Random();
        int minoTypesCount = MinoType.values().length;
        this.x = 4;
        this.y = -3;
        this.angle = r.nextInt(4);
        this.minoType = MinoType.from(r.nextInt(minoTypesCount));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canMoveDown(GameArea ga) {
        return !ga.existsFixedBlock(x, y + 1);
    }

    public void moveDown() {
        this.y++;
    }

    public void moveRight() {
        this.x++;
    }

    public void moveLeft() {
        this.x--;
    }

    public boolean existsBlock(int row, int col) {
        return this.minoType.getMinoData()[angle][row][col] == 1;
    }
    
    public static void debugPrint(int angle, MinoType minoType) {
        int[][][] minoData = minoType.getMinoData();

        for(int row = 0; row < MINO_SIZE; row++) {
            for(int col = 0; col < MINO_SIZE; col++) {
                System.out.print(minoData[angle][row][col] == 1 ? "回" : "・");
            }
            System.out.println();
        }
    }
}
