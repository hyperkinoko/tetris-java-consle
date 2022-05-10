public class Mino {
    private int x = 4;
    private int y = 0;

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
    
    
}
