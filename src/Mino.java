import java.util.Random;

public class Mino {
    public static final int MINO_SIZE = 4;

    private int x = 4;
    private int y = 0;
    private int angle = 3;
    private MinoType minoType = MinoType.MINO_J;

    public Mino() {
        this.x = 4;
        this.y = -3;
        this.angle = new Random().nextInt(4);
        this.minoType = MinoType.from(new Random().nextInt(MinoType.values().length));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canMoveDown(GameArea ga) {
        for(int row = 0; row < MINO_SIZE; row++) {
            for(int col = 0; col < MINO_SIZE; col++) {
                if(this.minoType.getMinoData()[angle][row][col] == 1) {
                    if(ga.existsFixedBlock(y + row + 1, x + col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canMoveRight(GameArea ga) {
        for(int row = 0; row < MINO_SIZE; row++) {
            for(int col = 0; col < MINO_SIZE; col++) {
                if(this.minoType.getMinoData()[angle][row][col] == 1) {
                    if(ga.existsFixedBlock(y + row, x + col + 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean canMoveLeft(GameArea ga) {
        for(int row = 0; row < MINO_SIZE; row++) {
            for(int col = 0; col < MINO_SIZE; col++) {
                if(this.minoType.getMinoData()[angle][row][col] == 1) {
                    if(ga.existsFixedBlock(y + row, x + col - 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
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
