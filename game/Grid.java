package game;

import java.awt.Color;

final class Grid
{
    Color[][] colors;
    int width;
    int height;

    Grid(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.colors = new Color[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                colors[i][j] = MyPanel.BACKGROUND;
    }

    boolean isValid(Point point)
    {
        if (!inBounds(point))
            return false;

        if (MyPanel.SPECIALS)
            return colors[point.y][point.x].equals(MyPanel.BACKGROUND)
                    || colors[point.y][point.x].equals(MyPanel.PORTAL_COLOR)
                    || colors[point.y][point.x]
                            .equals(MyPanel.INVINCIBILITY_COLOR)
                    || colors[point.y][point.x].equals(MyPanel.BOMB_COLOR);
        else
            return colors[point.y][point.x].equals(MyPanel.BACKGROUND);
    }

    boolean inBounds(Point point)
    {
        return point.y >= 0 && point.x >= 0 && point.y < height
                && point.x < width;
    }

    void setColor(Point point, Color color)
    {
        colors[point.y][point.x] = color;
    }
}
