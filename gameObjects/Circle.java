package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import paint.Handler;
import paint.Input;

public class Circle extends GameObject {
	private int radius;

	public static Circle createCircle(int radius, int x, int y, boolean controlled, Color color, double speed) {
		Circle go;
		go = (Circle) GameObject.repurpose(Circle.class, x, y, controlled, color, speed);
		if (go != null) {
			go.setRadius(radius);
			return go;
		}
		go = new Circle(radius, x, y, controlled, color, speed);
		return go;
	}

	public Circle(int radius, int x, int y, boolean controlled, Color color, double speed) {
		super(x, y, controlled, color, speed);
		this.width = 2 * radius;
		this.height = 2 * radius;
		this.radius = radius;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		// g.drawOval((int)x-radius, (int)y-radius, radius, radius);
		g.setColor(color);
		g.fillOval((int) x - radius, (int) y - radius, radius * 2, radius * 2);
		super.render(g);
	}

	protected void drawPointer(Graphics g) {
		g.setColor(Color.black);
		double distance = distance(mouseX, x, mouseY, y);
		g.drawLine((int) (x + (mouseX - x) / distance * 20), (int) (y + (mouseY - y) / distance * 20), (int) x,
				(int) y);
	}

	@Override
	public boolean inside(GameObject go) {
		if (go instanceof Circle) {
			Circle b = (Circle) go;
			if (Math.sqrt((b.getX() - x) * (b.getX() - x)
					+ (b.getY() - y) * (b.getY() - y)) <= radius + b.getRadius())
				return true;
		}
		return false;
	}

	void checkBounds() {

	}

	public void setRadius(int r) {
		radius = r;
		width = radius * 2;
		height = radius * 2;
	}

	protected void setSpeed(Double radians) {
		this.xSpeed = speed * Math.cos(radians);
		this.ySpeed = speed * Math.sin(radians);
	}

	public int getRadius() {
		return radius;
	}
}
