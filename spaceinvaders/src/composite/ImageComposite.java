package composite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class ImageComposite extends ScreenComponent{

	private Image imagem;
	
	public ImageComposite(Image imagem, int x, int y){
		super(x,y);
		this.imagem = imagem;
	}

	@Override
	public void draw(Graphics g, JPanel j) {
		// TODO Auto-generated method stub
		Graphics2D grafico = (Graphics2D) g;
		grafico.drawImage(imagem, x, y, j);
	}
	
}
