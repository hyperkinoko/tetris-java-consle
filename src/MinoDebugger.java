public class MinoDebugger {
    public static void main(String[] args) {
        for(MinoType minoType : MinoType.values()) {
            System.out.println(minoType);
            for(int i = 0; i < 4; i++) {
                Mino.debugPrint(i, minoType);
                System.out.println();
            }
        }
    }
}
