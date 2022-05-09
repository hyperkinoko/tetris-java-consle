public class App {
    
    public static void main(String[] args) throws Exception {
        GameArea ga = new GameArea();
        new GameThread(ga).start();
    }
}
