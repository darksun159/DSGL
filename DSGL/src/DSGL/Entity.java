package DSGL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Entity implements Renderable, Updatable{
	
	protected Point pos = new Point(0,0);
	protected BufferedImage sprite;
	
	public void update() {}
	
	public void render(Graphics g) {
		
		if(sprite != null) {
			g.drawImage(sprite, pos.x, pos.y, null);
		}
		else {
			g.setColor(Color.ORANGE);
			g.fillRect(pos.x, pos.y, 30,30);
		}
		
	}
	
	public Point getPosition() {
		return pos;
	}
	
	public int getX() {
		return pos.x;
	}
	
	public int getY() {
		return pos.y;
	}
	
}
