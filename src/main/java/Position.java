public record Position(int x, int y) {

    public Position getNextPosition(Direction direction) {
        return new Position(x + direction.getDx(), y + direction.getDy());
    }

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Position) {
            return equals((Position) object);
        }
        return false;
    }
}
