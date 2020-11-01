package touro.snake;

import java.util.Objects;

/**
 * Super class of all objects in the Garden.
 */
public class Square {
    private final int x;
    private final int y;

    public Square(Square square) {
        this.x = square.x;
        this.y = square.y;
    }

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @param square
     * @return the distance between two Squares using the distance formula
     */
    public double distance(Square square) {
        if (square == null) {
            return 0;
        }
        // https://www.khanacademy.org/math/geometry/hs-geo-analytic-geometry/hs-geo-distance-and-midpoints/v/distance-formula
        return Math.sqrt(Math.abs(square.x - x) * Math.abs(square.x - x) +
                Math.abs(square.y - y) * Math.abs(square.y - y));
    }

    /**
     * @param direction
     * @return a new Square moved in the supplied Direction
     */
    public Square moveTo(Direction direction) {
        switch (direction) {
            case North:
                return new Square(x, y - 1);
            case East:
                return new Square(x + 1, y);
            case South:
                return new Square(x, y + 1);
            case West:
                return new Square(x - 1, y);
            default:
                throw new RuntimeException(direction + " is not a known Direction");
        }
    }

    /**
     * @param square
     * @return the Direction needed to move this Square to reach the square parameter ONLY
     * if the two squares are adjacent, otherwise this method throws a RuntimeException.
     */
    public Direction directionTo(Square square) {
        if (this.x == square.x && this.y == square.y-1) {
            return Direction.South;
        }
        if (this.x == square.x && this.y == square.y+1) {
            return Direction.North;
        }
        if (this.x == square.x-1 && this.y == square.y) {
            return Direction.East;
        }
        if (this.x == square.x+1 && this.y == square.y) {
            return Direction.West;
        }

        throw new RuntimeException(square + " is not a direct neighbor to " + this);
    }

    /**
     * @return true if this Square is in the bounds of the Garden, otherwise false.
     */
    public boolean inBounds() {
        return x >= 0 && x < Garden.WIDTH && y >= 0 && y < Garden.HEIGHT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square segment = (Square) o;
        return x == segment.x &&
                y == segment.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
