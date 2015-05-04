package view;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Ages extends Canvas implements Runnable{
	
	public static int width = 300;
	public static int height = width / 16*9; 
	public static int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	public Ages() {
		Dimension size = new Dimension(width * scale ,height * scale);
		setPreferredSize(size);
		
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
	}
	
	/*
	 * game loop
	 */
	public void run() {
		while(running) {
			
		}
	}

	

}
