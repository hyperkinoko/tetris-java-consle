import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class App extends JFrame implements KeyListener {
    private GameArea ga;
    private GameThread thread;

    public App(String playerName) {
        addKeyListener(this);
        this.ga = new GameArea();
        this.thread = new GameThread(ga, playerName);
        this.thread.start();
    }
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String playerName = "";
        while(playerName == null || playerName.equals("")) {
            System.out.print("名前を入力してください > ");
            playerName = sc.nextLine();
        }
        App app = new App(playerName);
        app.setVisible(true);
    }

    private void onDownPressed() {
        Mino mino = thread.getCurrentMino();
        if(mino.canMoveDown(ga)) {
            mino.moveDown();
            ga.reflectMinoToFiled(mino);
            ga.drawField(thread.getScore(), thread.getNextMino(), thread.getPlayerName());
        }
    }

    private void onRightPressed() {
        Mino mino = thread.getCurrentMino();
        if(mino.canMoveRight(ga)) {
            mino.moveRight();
            ga.reflectMinoToFiled(mino);
            ga.drawField(thread.getScore(), thread.getNextMino(), thread.getPlayerName());
        }
    }
    
    private void onLeftPressed() {
        Mino mino = thread.getCurrentMino();
        if(mino.canMoveLeft(ga)) {
            mino.moveLeft();
            ga.reflectMinoToFiled(mino);
            ga.drawField(thread.getScore(), thread.getNextMino(), thread.getPlayerName());
        }
    }

    private void onUpPressed() {
        Mino mino = thread.getCurrentMino();
        if(mino.canRotate(ga)) {
            mino.rotate();
            ga.reflectMinoToFiled(mino);
            ga.drawField(thread.getScore(), thread.getNextMino(), thread.getPlayerName());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                onUpPressed();
                break;
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
