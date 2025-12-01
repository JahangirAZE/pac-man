import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.CYAN;
import static java.awt.Color.ORANGE;
import static java.awt.Color.PINK;
import static java.awt.Color.RED;

@Getter
public class GameEngine {
    private final Maze maze;
    private final PacMan pacMan;
    private final List<Ghost> ghosts;
    private final GameState state;

    public GameEngine() {
        this.maze = new Maze();
        this.pacMan = new PacMan(1, 1);
        this.ghosts = new ArrayList<>();
        this.state = new GameState();

        initializeGhosts();
    }

    private void initializeGhosts() {
        ghosts.add(new Ghost(9, 9, RED));
        ghosts.add(new Ghost(8, 9, PINK));
        ghosts.add(new Ghost(10, 9, CYAN));
        ghosts.add(new Ghost(9, 10, ORANGE));
    }

    public void update() {
        if (state.isGameOver() || state.isWon()) return;

        pacMan.move(maze);
        handlePacManCollision();

        state.updatePowerMode();

        for (Ghost ghost : ghosts) {
            ghost.move(maze);
            checkGhostCollision(ghost);
        }

        if (state.getDotsEaten() >= maze.getTotalDots()) {
            state.setWon(true);
        }
    }

    private void handlePacManCollision() {
        Position pos = pacMan.getPosition();
        int cell = maze.getCell(pos.x(), pos.y());

        if (cell == Constants.DOT) {
            maze.setCell(pos.x(), pos.y(), Constants.EMPTY);
            state.addScore(Constants.POINTS_DOT);
            state.eatDot();
        } else if (cell == Constants.POWER_PELLET) {
            maze.setCell(pos.x(), pos.y(), Constants.EMPTY);
            state.addScore(Constants.POINTS_POWER_PELLET);
            state.eatDot();
            state.activatePowerMode();
        }
    }

    private void checkGhostCollision(Ghost ghost) {
        if (ghost.getPosition().equals(pacMan.getPosition())) {
            if (state.isPowerMode()) {
                state.addScore(Constants.POINTS_GHOST);
                ghost.reset();
            } else {
                state.loseLife();
                if (state.getLives() <= 0) {
                    state.setGameOver(true);
                } else {
                    pacMan.reset();
                }
            }
        }
    }

    public void reset() {
        maze.reset();
        pacMan.reset();
        for (Ghost ghost : ghosts) {
            ghost.reset();
        }
        state.reset();
    }
}
