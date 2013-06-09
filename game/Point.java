package game;

final class Point
{
    int x, y;

    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    Point getAdjacentPoint(int direction)
    {
        if (direction == Player.UP)
            return new Point(x, y - 1);
        if (direction == Player.LEFT)
            return new Point(x - 1, y);
        if (direction == Player.DOWN)
            return new Point(x, y + 1);
        if (direction == Player.RIGHT)
            return new Point(x + 1, y);

        throw new IllegalArgumentException();
    }

    static int getDirection(int direction, int turn)
    {
        return (direction + turn) % 360;
    }

    public boolean equals(Object other)
    {
        return x == ((Point) other).x && y == ((Point) other).y;
    }

    public int hashCode()
    {
        return x + y;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}