package model;

public class Player {
    private int score;
    private static Player instance;
    public Player(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void addScore(int score) {
        this.score += score;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player(0);
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                '}';
    }
}

