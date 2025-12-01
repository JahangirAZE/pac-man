import java.awt.Color;
import java.util.Random;

public class Ghost extends Entity {
    private final Color color;
    private final Random random;
    private final Position homePosition;

    public Ghost(int x, int y, java.awt.Color color) {
        super(x, y, Direction.values()[new Random().nextInt(4)]);
        this.color = color;
        this.random = new Random();
        this.homePosition = new Position(x, y);
    }

    public Color getColor() { return color; }

    @Override
    public void move(Maze maze) {
        if (random.nextInt(10) < 3) {
            direction = Direction.values()[random.nextInt(4)];
        }

        Position newPos = position.getNextPosition(direction);

        if (maze.isWalkable(newPos)) {
            position = newPos;
        } else {
            direction = Direction.values()[random.nextInt(4)];
        }
    }

    public void reset() {
        position = new Position(homePosition.getX(), homePosition.getY());
        direction = Direction.values()[random.nextInt(4)];
    }
}
