public class GameState {
    private int score;
    private int lives;
    private int dotsEaten;
    private boolean gameOver;
    private boolean won;
    private boolean powerMode;
    private int powerModeTimer;

    public GameState() {
        reset();
    }

    public void reset() {
        score = 0;
        lives = Constants.INITIAL_LIVES;
        dotsEaten = 0;
        gameOver = false;
        won = false;
        powerMode = false;
        powerModeTimer = 0;
    }

    public void addScore(int points) { score += points; }
    public void eatDot() { dotsEaten++; }
    public void loseLife() { lives--; }
    public void activatePowerMode() {
        powerMode = true;
        powerModeTimer = Constants.POWER_MODE_DURATION;
    }

    public void updatePowerMode() {
        if (powerMode) {
            powerModeTimer--;
            if (powerModeTimer <= 0) {
                powerMode = false;
            }
        }
    }

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getDotsEaten() { return dotsEaten; }
    public boolean isGameOver() { return gameOver; }
    public boolean isWon() { return won; }
    public boolean isPowerMode() { return powerMode; }

    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
    public void setWon(boolean won) { this.won = won; }
}
