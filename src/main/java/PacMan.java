import java.util.Objects;

public class PacMan extends Entity {
    private Direction nextDirection;

    public PacMan(int x, int y) {
        super(x, y, Direction.RIGHT);
        this.nextDirection = Direction.RIGHT;
    }

    public void setNextDirection(Direction dir) {
        if (Objects.nonNull(dir)) {
            this.nextDirection = dir;
        }
    }

    @Override
    public void move(Maze maze) {
        Position nextPos = position.getNextPosition(nextDirection);
        if (maze.isWalkable(nextPos)) {
            direction = nextDirection;
        }

        Position newPos = position.getNextPosition(direction);
        if (maze.isWalkable(newPos)) {
            position = newPos;
        }
    }

    public void reset() {
        position = new Position(1, 1);
        direction = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
    }
}
