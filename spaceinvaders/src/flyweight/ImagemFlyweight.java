package flyweight;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImagemFlyweight extends SpriteFlyweight{
	
	protected String nomeImagem;
	private Image imagem;
	
	public ImagemFlyweight(String imagem){
		nomeImagem = imagem;
	}
	
	@Override
	public Image desenhaImagem(){
		ImageIcon imgIcon = new ImageIcon(nomeImagem);
		return imagem = imgIcon.getImage();
	}

}
