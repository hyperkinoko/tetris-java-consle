public class MinoDebugger {
    public static void main(String[] args) {
        Mino mino = new Mino();
        for(int i = 0; i < 4; i++) {
            mino.debugPrint(i, MinoType.MINO_T);
        }
    }
}
