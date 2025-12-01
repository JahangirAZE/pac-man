import lombok.Getter;

public class Maze {
    private final int[][] grid = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,2,1},
            {1,3,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,3,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,2,1,2,1,1,1,1,1,2,1,2,1,1,2,1},
            {1,2,2,2,2,1,2,2,2,1,2,2,2,1,2,2,2,2,1},
            {1,1,1,1,2,1,1,1,0,1,0,1,1,1,2,1,1,1,1},
            {1,1,1,1,2,1,0,0,0,0,0,0,0,1,2,1,1,1,1},
            {1,1,1,1,2,1,0,1,1,4,1,1,0,1,2,1,1,1,1},
            {0,0,0,0,2,0,0,1,0,0,0,1,0,0,2,0,0,0,0},
            {1,1,1,1,2,1,0,1,1,1,1,1,0,1,2,1,1,1,1},
            {1,1,1,1,2,1,0,0,0,0,0,0,0,1,2,1,1,1,1},
            {1,1,1,1,2,1,0,1,1,1,1,1,0,1,2,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,2,1,1,1,2,1,2,1,1,1,2,1,1,2,1},
            {1,3,2,1,2,2,2,2,2,2,2,2,2,2,2,1,2,3,1},
            {1,1,2,1,2,1,2,1,1,1,1,1,2,1,2,1,2,1,1},
            {1,2,2,2,2,1,2,2,2,1,2,2,2,1,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    @Getter
    private int[][] currentState;
    @Getter
    private int totalDots;

    public Maze() {
        reset();
    }

    public void reset() {
        currentState = new int[grid.length][grid[0].length];
        totalDots = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                currentState[y][x] = grid[y][x];
                if (grid[y][x] == Constants.DOT || grid[y][x] == Constants.POWER_PELLET) {
                    totalDots++;
                }
            }
        }
    }

    public int getCell(int x, int y) {
        if (y >= 0 && y < currentState.length && x >= 0 && x < currentState[0].length) {
            return currentState[y][x];
        }
        return Constants.WALL;
    }

    public void setCell(int x, int y, int value) {
        if (y >= 0 && y < currentState.length && x >= 0 && x < currentState[0].length) {
            currentState[y][x] = value;
        }
    }

    public boolean isWalkable(Position pos) {
        return getCell(pos.x(), pos.y()) != Constants.WALL;
    }

    public int getWidth() { return currentState[0].length; }
    public int getHeight() { return currentState.length; }
}
