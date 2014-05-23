package model;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;


public class Nave {
	
	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private Image imagem;
	private List<Tiro> tiros;
	private boolean isVisible;
	
	public Nave(){
		ImageIcon referencia = new ImageIcon("res\\nave1.gif");
		imagem = referencia.getImage();
		tiros = new ArrayList<Tiro>();
		
		this.x = 375;
		this.y = 520;
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	public void mover(){
		System.out.println(x + ", " + y);
		x += dx; //1 e 443
		
		if(this.x < 1)
			this.x = 1;
		if(this.x > 743)
			this.x = 743;

	}
	
	public void atira(){
		this.tiros.add(new Tiro(x+largura/2, y-altura+50));
	}
	
	public void keyPressed(KeyEvent button){
		
		int codeButton = button.getKeyCode();
		if(codeButton == KeyEvent.VK_SPACE){
			atira();
		}
		if(codeButton == KeyEvent.VK_LEFT){
			dx = -1;
		}
		if(codeButton == KeyEvent.VK_RIGHT){
			dx = 1;
		}
	}
	
	public void keyReleased(KeyEvent button){
		
		int codeButton = button.getKeyCode();
		
		if(codeButton == KeyEvent.VK_UP){
			dy = 0;
		}
		if(codeButton == KeyEvent.VK_DOWN){
			dy = 0;
		}
		if(codeButton == KeyEvent.VK_LEFT){
			dx = 0;
		}
		if(codeButton == KeyEvent.VK_RIGHT){
			dx = 0;
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public Image getImagem() {
		return imagem;
	}
	
	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
