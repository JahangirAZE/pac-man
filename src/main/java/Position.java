public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public Position getNextPosition(Direction dir) {
        return new Position(x + dir.getDx(), y + dir.getDy());
    }

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            return equals((Position) obj);
        }
        return false;
    }
}
