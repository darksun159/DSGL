package DSGL;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller{
	
	private LinkedList<Renderable> renderables = new LinkedList<Renderable>();
	private LinkedList<Updatable> updatables = new LinkedList<Updatable>();
	
	public Controller() {}
	
	public void addEntity(Entity e) {
		renderables.add(e);
		updatables.add(e);
	}
	
	public void removeEntity(Entity e) {
		renderables.remove(e);
		updatables.remove(e);
	}
	
	public void addRenderable(Renderable r) {
		renderables.add(r);
	}
	
	public void removeRenderable(Renderable r) {
		renderables.remove(r);
	}
	
	public void addUpdatable(Updatable u) {
		updatables.add(u);
	}
	
	public void removeUpdatable(Updatable u) {
		updatables.remove(u);
	}
	
	public void update() {
		for(Updatable u : updatables) {
			u.update();
		}
	}
	
	public void render(Graphics g) {
		for(Renderable r : renderables) {
			r.render(g);
		}
	}
	
}








