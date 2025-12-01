public class GameRenderer {
    public void render(java.awt.Graphics g, GameEngine engine) {
        renderMaze(g, engine.getMaze());
        renderPacMan(g, engine.getPacMan());
        renderGhosts(g, engine.getGhosts(), engine.getState().isPowerMode());
        renderUI(g, engine.getState(), engine.getMaze().getHeight());
    }

    private void renderMaze(java.awt.Graphics g, Maze maze) {
        int[][] grid = maze.getCurrentState();

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                int cell = grid[y][x];

                if (cell == Constants.WALL) {
                    g.setColor(java.awt.Color.BLUE);
                    g.fillRect(x * Constants.CELL_SIZE, y * Constants.CELL_SIZE,
                            Constants.CELL_SIZE, Constants.CELL_SIZE);
                } else if (cell == Constants.DOT) {
                    g.setColor(java.awt.Color.YELLOW);
                    g.fillOval(x * Constants.CELL_SIZE + Constants.CELL_SIZE/2 - 3,
                            y * Constants.CELL_SIZE + Constants.CELL_SIZE/2 - 3, 6, 6);
                } else if (cell == Constants.POWER_PELLET) {
                    g.setColor(java.awt.Color.WHITE);
                    g.fillOval(x * Constants.CELL_SIZE + Constants.CELL_SIZE/2 - 6,
                            y * Constants.CELL_SIZE + Constants.CELL_SIZE/2 - 6, 12, 12);
                }
            }
        }
    }

    private void renderPacMan(java.awt.Graphics g, PacMan pacMan) {
        Position pos = pacMan.getPosition();
        Direction dir = pacMan.getDirection();

        g.setColor(java.awt.Color.YELLOW);
        int mouth = (int)(System.currentTimeMillis() / 100 % 2) * 30;
        g.fillArc(pos.getX() * Constants.CELL_SIZE + 2,
                pos.getY() * Constants.CELL_SIZE + 2,
                Constants.CELL_SIZE - 4, Constants.CELL_SIZE - 4,
                dir.getAngle() * 90 + mouth, 360 - mouth * 2);
    }

    private void renderGhosts(java.awt.Graphics g, java.util.List<Ghost> ghosts, boolean powerMode) {
        for (Ghost ghost : ghosts) {
            Position pos = ghost.getPosition();

            g.setColor(powerMode ? java.awt.Color.BLUE : ghost.getColor());
            g.fillOval(pos.getX() * Constants.CELL_SIZE + 2,
                    pos.getY() * Constants.CELL_SIZE + 2,
                    Constants.CELL_SIZE - 4, Constants.CELL_SIZE - 4);

            // Eyes
            g.setColor(java.awt.Color.WHITE);
            g.fillOval(pos.getX() * Constants.CELL_SIZE + 8,
                    pos.getY() * Constants.CELL_SIZE + 8, 6, 8);
            g.fillOval(pos.getX() * Constants.CELL_SIZE + 16,
                    pos.getY() * Constants.CELL_SIZE + 8, 6, 8);
        }
    }

    private void renderUI(java.awt.Graphics g, GameState state, int mazeHeight) {
        g.setColor(java.awt.Color.WHITE);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

        int yPos = mazeHeight * Constants.CELL_SIZE + 25;
        g.drawString("Score: " + state.getScore(), 10, yPos);
        g.drawString("Lives: " + state.getLives(), 150, yPos);

        if (state.isPowerMode()) {
            g.drawString("POWER MODE!", 300, yPos);
        }

        if (state.isGameOver()) {
            g.setColor(java.awt.Color.RED);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 36));
            g.drawString("GAME OVER", 150, mazeHeight * Constants.CELL_SIZE / 2);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
            g.drawString("Press R to Restart", 180, mazeHeight * Constants.CELL_SIZE / 2 + 40);
        }

        if (state.isWon()) {
            g.setColor(java.awt.Color.GREEN);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 36));
            g.drawString("YOU WIN!", 170, mazeHeight * Constants.CELL_SIZE / 2);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));
            g.drawString("Press R to Restart", 180, mazeHeight * Constants.CELL_SIZE / 2 + 40);
        }
    }
}
