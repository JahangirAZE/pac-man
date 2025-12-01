import lombok.Getter;

@Getter
abstract class Entity {
    protected Position position;
    protected Direction direction;

    public Entity(int x, int y, Direction direction) {
        this.position = new Position(x, y);
        this.direction = direction;
    }

    public abstract void move(Maze maze);
}
