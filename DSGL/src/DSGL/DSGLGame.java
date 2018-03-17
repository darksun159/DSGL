package DSGL;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFrame;

public class DSGLGame extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 4433797093142514011L;

	private JFrame frame;
	private Thread thread;
	private Key key;
	private Controller controller;
	
	private boolean running = false;
	private int UPS;
	private int width, height;
	
	private int currentUPS = 0, currentFPS = 0;
	
	public boolean SECOND = true;
	
	public DSGLGame(String title, int width, int height, boolean resizable, int UPS) {
		this.UPS = UPS;
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		
		key = new Key();
		
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setResizable(resizable);
		frame.add(this);
		frame.addKeyListener(key);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocus();
		
		thread = new Thread(this);
		start();
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		
		try {
			thread.join();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		long currentTime = System.currentTimeMillis();
		long lastTime = System.currentTimeMillis();
		int uTime = 1000/UPS;
		
		long secondCurrent = System.currentTimeMillis();
		long secondLast = System.currentTimeMillis();
		int cfps = 0, cups = 0;
		
		while(running) {
			secondCurrent = System.currentTimeMillis();
			if(secondCurrent-secondLast>=1000) {
				secondLast=secondCurrent;
				currentUPS = cups;
				currentFPS = cfps;
				cups = 0;
				cfps = 0;
			}
			
			currentTime = System.currentTimeMillis();
			if(currentTime-lastTime>=uTime) {
				lastTime=currentTime;
				cups++;
				
				if(cups == 0 || cups == 1) {
					SECOND = true;
				}
				else {
					SECOND = false;
				}
				
				update();
			}
			
			cfps++;
			render();
			try {
				Thread.sleep(2);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void render() {
		Dimension d = frame.getSize();
		width = d.width;
		height = d.height;
		
		if(getBufferStrategy() == null) {
			createBufferStrategy(2);
			return;
		}
		
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.drawString("You haven't drawn anything!", 10, 10);
		
		controller.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void update() {
		if(hasFocus()) {
			frame.requestFocus();
		}
		
		controller.update();
	}
	
	public int getCurrentUPS() {
		return currentUPS;
	}
	
	public int getCurrentFPS() {
		return currentFPS;
	}
	
	public Controller getGameController() {
		return controller;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Key getKeyListener() {
		return key;
	}
	
}
























