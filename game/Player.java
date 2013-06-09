package game;

import java.awt.Color;

class Player
{
    static final int RIGHT = 90;
    static final int UP = 0;
    static final int LEFT = 270;
    static final int DOWN = 180;

    final String name;
    Color color;
    Point point;
    boolean isDead;
    int direction;
    int score;
    boolean turned;
    boolean invincible; // extra
    int invincibilityTimer;

    Player(String name, Color color, Point point)
    {
        this.name = name;
        this.color = color;
        this.point = point;
        this.isDead = false;
        this.direction = UP;
        this.score = 0;
        this.turned = false;
        this.invincible = false; // extra
    }

    void move()
    {
        if (direction == RIGHT)
            point.x++;
        else if (direction == UP)
            point.y--;
        else if (direction == LEFT)
            point.x--;
        else if (direction == DOWN)
            point.y++;

        turned = false;
    }

    final void setDirection(int newDirection)
    {
        if (!turned && Math.abs(direction - newDirection) != 180)
        {
            direction = newDirection;
            turned = true;
        }
    }

    public String toString()
    {
        return name + " " + color + " " + point + " " + isDead;
    }
}