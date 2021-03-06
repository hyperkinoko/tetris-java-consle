public class GameArea {
    private final int FIELD_WIDTH = 12;
    private final int FIELD_HEIGHT = 21;
    private int[][] fixedField = new int[FIELD_HEIGHT][FIELD_WIDTH];
    private int[][] field = new int[FIELD_HEIGHT][FIELD_WIDTH];

    public GameArea() {
        initFixedField();
        reflectFixedFieldToField();
    }

    private void initFixedField() {
        for(int i = 0; i < fixedField.length; i++) {
            fixedField[i][0] = 1;
            fixedField[i][fixedField[i].length - 1] = 1;
        }
        for(int i = 0; i < fixedField[i].length; i++) {
            fixedField[fixedField.length - 1][i] = 1;
        }
    }

    private void reflectFixedFieldToField() {
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                field[i][j] = fixedField[i][j];
            }
        }
    }

    public void drawField() {
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] == 0 ? "・" : "回");
            }
            System.out.println();
        } 
    }  
    
    public boolean existsFixedBlock(int row, int col) {
        if(col < 0 || col > FIELD_WIDTH - 1) {
            return true;
        }
        if(row > FIELD_HEIGHT - 1) {
            return true;
        }
        if(row < 0) {
            return false;
        }
        if(fixedField[row][col] == 1) {
            return true;
        }
        return false;
    }

    public void reflectMinoToFiled(Mino mino) {
        reflectFixedFieldToField();
        for(int row = 0; row < Mino.MINO_SIZE; row++) {
            if(mino.getY() + row < 0 || mino.getY() + row >= FIELD_HEIGHT - 1) {
                continue;
            }
            for(int col = 0; col < Mino.MINO_SIZE; col++) {
                if(mino.getX() + col < 0 || mino.getX() + col >= FIELD_WIDTH - 1) {
                    continue;
                }
                if(mino.existsBlock(row, col)) {
                    field[mino.getY() + row][mino.getX() + col] = 1;
                }
            }
        }
    }

    public void fixMino(Mino mino) {
        for(int row = 0; row < Mino.MINO_SIZE; row++) {
            if(mino.getY() + row < 0 || mino.getY() + row >= FIELD_HEIGHT - 1) {
                continue;
            }
            for(int col = 0; col < Mino.MINO_SIZE; col++) {
                if(mino.getX() + col < 0 || mino.getX() + col >= FIELD_WIDTH - 1) {
                    continue;
                }
                if(mino.existsBlock(row, col)) {
                    fixedField[mino.getY() + row][mino.getX() + col] = 1;
                }
            }
        }
    }

    private boolean isErasable(int[] line) {
        for(int col = 1; col < FIELD_WIDTH - 1; col++) {
            if(line[col] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean hasErasableLine() {
        for(int row = FIELD_HEIGHT - 2; row >= 0; row--) {
            if(isErasable(fixedField[row])) {
                return true;
            }
        }
        return false;
    }

    public int EraseLines() {
        int index = FIELD_HEIGHT - 2;
        for(int row = FIELD_HEIGHT - 2; row >= 0; row--) {
           if(!isErasable(fixedField[row])) {
                // 配列をindex番目にコピー
                for(int col = 1; col < FIELD_WIDTH - 1; col++) {
                    fixedField[index][col] = fixedField[row][col];
                }
                index--;
           }
        }

        int lineCount = 0;
        while(index >= 0) {
            // 新しい配列を入れる（1行分）
            fixedField[index][0] = 1;
            for(int col = 1; col < FIELD_WIDTH - 1; col++) {
                fixedField[index][col] = 0;
            }
            fixedField[index][FIELD_WIDTH - 1] = 1;
            index--;
            lineCount++;
        } 
        return lineCount;
    }


}
