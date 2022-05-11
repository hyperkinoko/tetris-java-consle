import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class App extends JFrame implements KeyListener {
    private GameArea ga;
    private GameThread gt;

    public App() {
        addKeyListener(this);
        this.ga = new GameArea();
        this.gt = new GameThread(ga);
        gt.start();
    }
    
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.setVisible(true);
    }

    private void onDownPressed() {
        Mino mino = this.gt.getCurrentMino();
        if(mino.canMoveDown(ga)) {
            mino.moveDown();
            ga.reflectMinoToFiled(mino);
            ga.drawField();
        }
    }

    private void onRightPressed() {
        Mino mino = this.gt.getCurrentMino();
        if(mino.canMoveRight(ga)) {
            mino.moveRight();
            ga.reflectMinoToFiled(mino);
            ga.drawField();
        }
    }
    
    private void onLeftPressed() {
        Mino mino = this.gt.getCurrentMino();
        if(mino.canMoveLeft(ga)) {
            mino.moveLeft();
            ga.reflectMinoToFiled(mino);
            ga.drawField();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                onDownPressed();
                break;

            case KeyEvent.VK_RIGHT:
                onRightPressed();
                break;
            case KeyEvent.VK_LEFT:
                onLeftPressed();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
