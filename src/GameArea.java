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
    
    public void reflectMinoToFiled(Mino mino) {
        reflectFixedFieldToField();
        field[mino.getY()][mino.getX()] = 1;
    }
}
