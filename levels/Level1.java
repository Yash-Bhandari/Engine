package levels;

import java.awt.Color;

import gameObjects.Circle;
import gameObjects.Round;
import paint.Handler;
import paint.Input;

public class Level1 {
	static Handler handler;
	static int numKilled = 0;

	public static void start(Handler handler, Input input) {
		Level1.handler = handler;
		Circle first = new Circle(20, 200, 300, true, Color.white, 3.5);
		handler.add(first);
		addEnemy(300, 400, 1.3);
		addEnemy(100, 60, 2.4);
		// handler.add(new Circle(40, 400, 400, false, Color.CYAN, 2));
		// handler.add(new Bullet(5, 200, 100, Color.black, 5, Math.PI));
	}

	public static void loop() {

	}

	private static void addEnemy(int x, int y, double angle) {
		handler.add(new Round(20, x, y, Color.red, 2, angle));
	}
}
