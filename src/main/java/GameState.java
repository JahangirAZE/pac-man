import lombok.Getter;
import lombok.Setter;

public class GameState {
    @Setter
    @Getter
    private boolean gameOver;
    @Setter
    @Getter
    private boolean won;
    @Getter
    private int score;
    @Getter
    private int lives;
    @Getter
    private int dotsEaten;
    @Getter
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
}
