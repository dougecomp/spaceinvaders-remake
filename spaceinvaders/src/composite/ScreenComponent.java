package composite;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public abstract class ScreenComponent {

	protected int x;
	protected int y;
	
	public ScreenComponent(int x, int y){
		
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw(Graphics g, JPanel j);
	
}
