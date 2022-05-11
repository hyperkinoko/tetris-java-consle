public enum MinoType {
    MINO_I,
    MINO_J,
    MINO_L,
    MINO_O,
    MINO_T,
    MINO_S,
    MINO_Z;

    public int[][][] getMinoData() {
        switch(this) {
            case MINO_T:
                return minoData_T;
            default:
                return minoData_T;
        }
    }

    private final int[][][] minoData_T = {
        {
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
        },           
        {
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 1, 0},
        },    
        {
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 0},
        }, 
        {
            {0, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 0, 0},
        }
    };
}
