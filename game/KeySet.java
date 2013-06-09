package game;

import static java.awt.event.KeyEvent.*;

final class KeySet
{
    static final KeySet WASD = new KeySet(VK_W, VK_A, VK_S, VK_D);
    static final KeySet IJKL = new KeySet(VK_I, VK_J, VK_K, VK_L);
    static final KeySet ARROWS = new KeySet(VK_UP, VK_LEFT, VK_DOWN, VK_RIGHT);
    static final KeySet TFGH = new KeySet(VK_T, VK_F, VK_G, VK_H);
    static final KeySet NULL = new KeySet(0, 0, 0, 0);

    final int UP;
    final int LEFT;
    final int DOWN;
    final int RIGHT;

    KeySet(int up, int left, int down, int right)
    {
        UP = up;
        LEFT = left;
        DOWN = down;
        RIGHT = right;
    }
}
