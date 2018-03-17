package DSGL;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener{

	public static boolean[] down;
	
	public static boolean typeNum = false;
	
	public Key() {
		down = new boolean[256];
		for(boolean b : down)
			b = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		down[e.getKeyCode()] = true;
		if(typeNum) {
			System.out.println(e.getKeyChar() + " : " + e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		down[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e){}

}
