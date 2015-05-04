package Ages;

import graphics.Screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;


public class Ages extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = 1L;
	public static int width = 300;
	public static int height = width / 16*9; 
	public static int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;

	public Ages() {
		Dimension size = new Dimension(width * scale ,height * scale);
		setPreferredSize(size);
		screen = new Screen(width,height);
		
		
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
		while(running) {
			update();
			render();
		}
	}
	
	public void update() {
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		screen.render();
		
		for(int i=0;i<pixels.length;i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(),null);
		
		g.dispose();
		bs.show();
	}

	

}
