public class Mino {
    private int x = 4;
    private int y = 0;

    public int getX() {
        return x;
    }
    // public void setX(int x) {
    //     this.x = x;
    // }
    public int getY() {
        return y;
    }

    public boolean canMoveDown(GameArea ga) {
        return !ga.existsFixedBlock(x, y + 1);
    }

    public void moveDown() {
        this.y++;
    }

    
    
}
