public class Mino {
    public static final int MINO_SIZE = 4;

    private int x = 4;
    private int y = 0;
    private int angle = 0;
    private MinoType minoType = MinoType.MINO_T;

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
