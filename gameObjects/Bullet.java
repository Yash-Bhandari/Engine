package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import paint.Game;

public class Bullet extends Circle {
	
	public GameObject owner;
	private double radians;
	
    public static Bullet createBullet(int radius, int x, int y, Color color, double speed, GameObject owner) {
        Bullet go;
        go = (Bullet)GameObject.repurpose(Bullet.class, x, y, false, color, speed);
        if (go != null) {
        	go.owner = owner;
        	go.radians = owner.angle;
            go.setRadius(radius);
            go.xSpeed = Math.cos(go.radians) * go.speed;
            go.ySpeed = -Math.sin(go.radians) * go.speed;
            //System.out.println("X speed : " + go.xSpeed + ", Y speed : " + go.ySpeed);
            return go;
        }
        else return new Bullet(radius, x, y, color, speed, owner);
    }
    
    protected void move() {
        x += xSpeed;
        y += ySpeed;
        //System.out.println("X speed : " + xSpeed + ", Y speed : " + ySpeed);
    }
    
    public Bullet(int radius, int x, int y, Color color, double speed, GameObject owner) {
        super(radius, x, y, false, color, speed);
        this.owner = owner;
        this.radians = owner.angle;
        this.xSpeed = Math.cos(radians) * speed;
        this.ySpeed = -Math.sin(radians) * speed;
    }
    
    public boolean canHit(GameObject go) {
    	return !(go == owner);
    }
    
    @Override
    void checkBounds() {
        if (x < width / 2 && inUse)
            discard();
        if (x > Game.WIDTH - width / 2 - 1 && inUse)
            discard();
        if (y < height / 2 && inUse)
            discard();
        if (y > Game.HEIGHT - height / 2 - 1 && inUse)
            discard();
    }

}
