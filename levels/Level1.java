package levels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import gameObjects.Circle;
import gameObjects.GameObject;
import gameObjects.Round;
import paint.Game;
import paint.Handler;
import paint.Input;

public class Level1 {
	static Handler handler;
	static int numKilled = 0;

	public static void start(Handler handler, Input input) {
		Level1.handler = handler;
		Circle first = Circle.createCircle(20, 200, 300, true, Color.white, 3.5);
		handler.add(first);
		addEnemy(300, 400, 1.3);
		addEnemy(100, 60, 2.4);
		// handler.add(new Circle(40, 400, 400, false, Color.CYAN, 2));
		// handler.add(new Bullet(5, 200, 100, Color.black, 5, Math.PI));
	}

	public static void loop() {
		if (handler.numKilled < 10 && handler.numEnemies < 5) {
			randEnemy();
		}
	}

	public static void render(Graphics g) {
		if (handler.numEnemies == 0) {
			g.drawString("Hey, ya won. That's pretty dope", 20, 20);
		}
	}

	private static void addEnemy(int x, int y, double angle) {
		handler.add(new Round(20, x, y, Color.red, 2, angle));
	}

	private static void randEnemy() {

		double angle = Math.random() * 2 * Math.PI;
		Dimension d = findDimension();
		int x = (int)d.getWidth();
		int y = (int)d.getHeight();
		handler.add(Round.createRound(20, x, y, Color.red, 2, angle));

	}

	private static Dimension findDimension() {
		int x = (int) (Math.random() * Game.WIDTH);
		int y = (int) (Math.random() * Game.HEIGHT);
		Circle player = (Circle) handler.getControlled();
		while (x > 50 && x < Game.WIDTH - 50 && y > 50 && y < Game.HEIGHT - 50
				&& (x > player.getX() - player.getRadius() * 2 && x < player.getX() - player.getRadius() * 2)
				&& (y > player.getY() - player.getRadius() * 2 && y < player.getY() + player.getRadius() * 2)) {
			x = (int) (Math.random() * Game.WIDTH);
			y = (int) (Math.random() * Game.HEIGHT);
		}
		return new Dimension(x, y);
	}

}
