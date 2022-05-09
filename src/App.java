public class App {
    
    public static void main(String[] args) throws Exception {
        GameArea ga = new GameArea();
        Mino mino = new Mino();
        new GameThread(ga, mino).start();
    }
}
