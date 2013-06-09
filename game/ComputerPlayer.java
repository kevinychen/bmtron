package game;

import java.awt.Color;

class ComputerPlayer extends Player
{
    final MyPanel panel;
    Grid grid;

    ComputerPlayer(MyPanel panel, String name, Color color, Point point)
    {
        super(name, color, point);
        this.panel = panel;
    }

    void move()
    {
        setDirection(favorableDirection());
        super.move();
    }

    int favorableDirection()
    {
        grid = panel.grid;

        int leftDirection = Point.getDirection(direction, Player.LEFT);
        int rightDirection = Point.getDirection(direction, Player.RIGHT);
        int backDirection = Point.getDirection(direction, Player.DOWN);

        Point forward = point.getAdjacentPoint(direction);
        Point forward_left = point.getAdjacentPoint(direction)
                .getAdjacentPoint(leftDirection);
        Point forward_right = point.getAdjacentPoint(direction)
                .getAdjacentPoint(rightDirection);
        Point left = point.getAdjacentPoint(leftDirection);
        Point right = point.getAdjacentPoint(rightDirection);
        Point back_left = point.getAdjacentPoint(backDirection)
                .getAdjacentPoint(leftDirection);
        Point back_right = point.getAdjacentPoint(backDirection)
                .getAdjacentPoint(rightDirection);

        boolean leftWay = grid.isValid(left);
        boolean rightWay = grid.isValid(right);
        boolean frontWay = grid.isValid(forward);

        if ((leftWay ? 1 : 0) + (rightWay ? 1 : 0) + (frontWay ? 1 : 0) == 1)
        {
            if (leftWay)
                return leftDirection;
            if (rightWay)
                return rightDirection;
            return direction;
        }

        int forwardArea, leftArea, rightArea;
        forwardArea = leftArea = rightArea = Integer.MAX_VALUE;
        if ((!grid.isValid(forward_left) || !grid.isValid(forward_right)))
        {
            forwardArea = area(forward);
            leftArea = area(left);
            rightArea = area(right);
        }

        if (forwardArea < leftArea || forwardArea < rightArea)
            frontWay = false;
        if (leftArea < forwardArea || leftArea < rightArea)
            leftWay = false;
        if (rightArea < forwardArea || rightArea < leftArea)
            rightWay = false;

        if ((leftWay ? 1 : 0) + (rightWay ? 1 : 0) + (frontWay ? 1 : 0) == 1)
        {
            if (leftWay)
                return leftDirection;
            if (rightWay)
                return rightDirection;
            return direction;
        }

        if (!grid.isValid(back_left) && leftWay)
            return leftDirection;
        if (!grid.isValid(back_right) && rightWay)
            return rightDirection;

        if (!frontWay)
        {
            if (Math.random() < .5)
                return leftDirection;
            else
                return rightDirection;
        }

        return direction;
    }

    private int area(Point point)
    {
        boolean[][] field = new boolean[grid.height][grid.width];
        for (int i = 0; i < grid.height; i++)
            for (int j = 0; j < grid.width; j++)
                field[i][j] = grid.colors[i][j].equals(MyPanel.BACKGROUND);

        count = 0;
        if (grid.isValid(point))
            help(field, point);
        return count;
    }

    static int count;

    private void help(boolean[][] field, Point point)
    {
        field[point.y][point.x] = false;
        count++;

        if (point.y > 0 && field[point.y - 1][point.x])
            help(field, point.getAdjacentPoint(Player.UP));
        if (point.y < field.length - 1 && field[point.y + 1][point.x])
            help(field, point.getAdjacentPoint(Player.DOWN));
        if (point.x > 0 && field[point.y][point.x - 1])
            help(field, point.getAdjacentPoint(Player.LEFT));
        if (point.x < field[point.y].length - 1 && field[point.y][point.x + 1])
            help(field, point.getAdjacentPoint(Player.RIGHT));
    }
}
