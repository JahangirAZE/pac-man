abstract class Entity {
    protected Position position;
    protected Direction direction;

    public Entity(int x, int y, Direction dir) {
        this.position = new Position(x, y);
        this.direction = dir;
    }

    public Position getPosition() { return position; }
    public Direction getDirection() { return direction; }

    public abstract void move(Maze maze);
}
