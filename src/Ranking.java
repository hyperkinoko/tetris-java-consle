public class Ranking {
    private int id;
    private int score;
    private String name;

    public Ranking(int id, int score, String name) {
        this.id = id;
        this.score = score;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("%03d -- %-10s", score, name);
    }
}
