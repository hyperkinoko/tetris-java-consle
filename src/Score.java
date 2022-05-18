public class Score {
    private final int score;
    private final String name;
    
    public Score(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return score + " -- " + name;
    }
}
