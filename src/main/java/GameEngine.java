public class GameEngine {
    private final Maze maze;
    private final PacMan pacMan;
    private final java.util.List<Ghost> ghosts;
    private final GameState state;

    public GameEngine() {
        this.maze = new Maze();
        this.pacMan = new PacMan(1, 1);
        this.ghosts = new java.util.ArrayList<>();
        this.state = new GameState();

        initializeGhosts();
    }

    private void initializeGhosts() {
        ghosts.add(new Ghost(9, 9, java.awt.Color.RED));
        ghosts.add(new Ghost(8, 9, java.awt.Color.PINK));
        ghosts.add(new Ghost(10, 9, java.awt.Color.CYAN));
        ghosts.add(new Ghost(9, 10, java.awt.Color.ORANGE));
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
        int cell = maze.getCell(pos.getX(), pos.getY());

        if (cell == Constants.DOT) {
            maze.setCell(pos.getX(), pos.getY(), Constants.EMPTY);
            state.addScore(Constants.POINTS_DOT);
            state.eatDot();
        } else if (cell == Constants.POWER_PELLET) {
            maze.setCell(pos.getX(), pos.getY(), Constants.EMPTY);
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

    public Maze getMaze() { return maze; }
    public PacMan getPacMan() { return pacMan; }
    public java.util.List<Ghost> getGhosts() { return ghosts; }
    public GameState getState() { return state; }
}
