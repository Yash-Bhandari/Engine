package paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import gameObjects.GameObject;
import levels.Level1;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 3119695923651282663L;

    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;
    
    public static int moved;

    private Thread thread;
    private boolean running = false;
    public Handler handler;
    public Input input;
    

    public boolean keys = false;

    public Game() {
        new Panel(WIDTH, HEIGHT, "Paint", this);
        input = new Input(this);
        handler = new Handler(input);
        this.addKeyListener(input);
        this.addMouseListener(input);
        Level1.start(handler, input);
    }

    public static void main(String[] args) {
       new Game();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double numUps = 60.0;
        double ns = 1000000000 / numUps;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        moved = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                System.out.println(handler.numObjects() + " objects");
                System.out.println(GameObject.index + " recycled");
                frames = 0;
                System.out.println();
            }
        }
    }

    public void update() {
        handler.updateObjects();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        input.checkMouse();
        g.setColor(Color.gray);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.renderObjects(g);

        g.dispose();
        bs.show();
    }

}
