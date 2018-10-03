package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import paint.Input;

public class Square extends GameObject {

    public static Square createSquare(int side, int x, int y, boolean controlled, Color color, double speed) {
        Square go;
        go = (Square) GameObject.repurpose(Square.class, x, y, controlled, color, speed);
        if (go != null) {
            go.width = side;
            return go;
        }
        go = new Square(side, x, y, controlled, color, speed);
        return go;
    }

    public Square(int side, int x, int y, boolean controlled, Color color, double speed) {
        super(x, y, controlled, color, speed);
        this.width = side;
        this.height = side;
    }

    public void update(Input input) {
        if (controlled) {
            controls(input);
        }
        move();
        super.checkBounds();
    }

    protected void drawPointer(Graphics g) {
        // g.drawOval(mouseX - 5, mouseY - 5, 10, 10);
        double distance = distance(mouseX, x, mouseY, y);
        g.drawLine((int) (x + (mouseX - x) / distance * 20), (int) (y + (mouseY - y) / distance * 20), (int) x,
                (int) y);

    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) (x - width / 2), (int) (y - height / 2), width, height);
        super.render(g);
    }


	@Override
	public boolean inside(GameObject go) {
		// TODO Auto-generated method stub
		return false;
	}

}
