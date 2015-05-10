package Ages;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.prefs.Preferences;

import javax.swing.JFrame;

import Ages.graphics.Screen;
import Ages.input.Keyboard;
import Ages.level.Level;
import Ages.level.RandomLevel;

public class Ages extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = ("Ages");
	public Preferences prefs;

	public Level level;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Ages() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		level = new RandomLevel(64,64);

		key = new Keyboard();
		addKeyListener(key);
		prefs = Preferences.userRoot().node("click");

		frame = new JFrame();
		
		
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();

	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Ages ages = new Ages();
		ages.frame.setResizable(false);
		ages.frame.setTitle("Ages");
		ages.frame.add(ages);
		ages.frame.pack();
		ages.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ages.frame.setLocationRelativeTo(null);
		ages.frame.setVisible(true);

		ages.start();
	}

	/*
	 * game loop
	 */
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("fps: " + frames + ", updates: " +
				// updates);
				frame.setTitle(title + "   |   " + frames + " fps | " + updates + " ups");
				updates = 0;
				frames = 0;
			}
		}
		stop();

	}

	int x = 0, y = 0;

	public void update() {

		key.update();
		if (key.up) y--;
		if (key.down) y++;
		if (key.left) x--;
		if (key.right) x++;

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		level.render(x, y, screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

}
