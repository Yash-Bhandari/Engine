package gameObjects;

import java.awt.Color;

import paint.Game;

public class Round extends Circle {

    public static Round createRound(int radius, int x, int y, Color color, double speed, double radians) {
        Round go;
        go = (Round)GameObject.repurpose(Round.class, x, y, false, color, speed);
        if (go != null) {
            go.setRadius(radius);
            go.setSpeed(radians);
            //System.out.println("X speed : " + go.xSpeed + ", Y speed : " + go.ySpeed);
            return go;
        }
        else return new Round(radius, x, y, color, speed, radians);
    }
	
	public Round(int radius, int x, int y, Color color, double speed, double radians) {
		super(radius, x, y, false, color, speed);
		setSpeed(radians);
	}
	
    @Override
    void checkBounds() {
        if (x < width / 2 && inUse)
            xSpeed = -xSpeed;
        if (x > Game.WIDTH - width / 2 - 1 && inUse)
            xSpeed = -xSpeed;
        if (y < height / 2 && inUse)
            ySpeed = -ySpeed;
        if (y > Game.HEIGHT - height / 2 - 1 && inUse)
            ySpeed = -ySpeed;
    }

}
