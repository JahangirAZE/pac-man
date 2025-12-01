import lombok.Getter;

@Getter
public enum Direction {
    RIGHT(0, 1, 0),
    DOWN(1, 0, 1),
    LEFT(2, -1, 0),
    UP(3, 0, -1);

    private final int angle;
    private final int dx;
    private final int dy;

    Direction(int angle, int dx, int dy) {
        this.angle = angle;
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction fromKeyCode(int keyCode) {
        return switch (keyCode) {
            case 39 -> RIGHT;
            case 40 -> DOWN;
            case 37 -> LEFT;
            case 38 -> UP;
            default -> null;
        };
    }
}
