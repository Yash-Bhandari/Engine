package paint;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener {

    public boolean[] keys = new boolean[5];
    public boolean shooting = false;
    private Component c;

    public Input(Component c) {
        this.c = c;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        adjust(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        adjust(e, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    // sets index in keys[] corresponding to KeyEvent e to boolean a
    private void adjust(KeyEvent e, boolean a) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
            keys[0] = a;
            break;
        case KeyEvent.VK_S:
            keys[1] = a;
            break;
        case KeyEvent.VK_A:
            keys[2] = a;
            break;
        case KeyEvent.VK_D:
            keys[3] = a;
            break;
        }
    }

    public void checkMouse() {
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        int screenLeft = c.getLocationOnScreen().x;
        int screenTop = c.getLocationOnScreen().y;
        if (mouseX > screenLeft && mouseX < screenLeft + Game.WIDTH)
            if (mouseY > screenTop && mouseY < screenTop + Game.HEIGHT)
                c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public int getMouseX() {
        return MouseInfo.getPointerInfo().getLocation().x - c.getLocationOnScreen().x;
    }

    public int getMouseY() {
        return MouseInfo.getPointerInfo().getLocation().y - c.getLocationOnScreen().y;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent e) {
        shooting = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shooting = false;

    }

}
