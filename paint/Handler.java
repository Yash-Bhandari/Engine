package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import gameObjects.Bullet;
import gameObjects.GameObject;

public class Handler {

	private LinkedList<GameObject> objects;
	private LinkedList<Bullet> bullets;

	public GameObject controlled;
	private Input input;

	public Handler(Input input) {
		objects = new LinkedList<GameObject>();
		bullets = new LinkedList<Bullet>();
		this.input = input;
	}

	public void add(GameObject go) {
		if (!go.inHandler) {
			objects.add(go);
			go.inHandler = true;
			if (go instanceof Bullet) {
				bullets.add((Bullet) go);
			}
		}
		if (go.isControlled())
			controlled = go;
	}

	public void updateObjects() {
		if (input.shooting) {
			add(Bullet.createBullet(5, controlled.getX(), controlled.getY(), Color.black, 5, controlled));
			input.shooting = false;
		}

		for (GameObject go : objects) {
			if (go.inUse())
				go.update(input);
		}

		for (Bullet b : bullets) {
			for (GameObject go : objects) {
				if (!(go instanceof Bullet)) {
					if (go.inside(b) && b.canHit(go)) {
						go.discard();
						b.discard();
						
					}
				}
			}
		}
	}

	public void renderObjects(Graphics g) {
		for (GameObject go : objects) {
			go.render(g);
		}
	}

	public void clear() {
		objects.clear();
	}

	public GameObject getControlled() {
		return controlled;
	}

	public int numObjects() {
		return objects.size();
	}
}
