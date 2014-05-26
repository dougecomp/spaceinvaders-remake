package composite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class StringComposite extends ScreenComponent{

	String texto;
	
	public StringComposite(String texto, int x, int y) {
		super(x, y);
		this.texto = texto;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g, JPanel j) {
		// TODO Auto-generated method stub		
		Graphics2D grafico = (Graphics2D) g;
		grafico.setColor(Color.GREEN);
		grafico.drawString(texto, x, y);
	}
	
	

}
