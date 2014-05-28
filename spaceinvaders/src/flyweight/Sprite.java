package flyweight;

import java.awt.Image;

public class Sprite extends SpriteFlyweight{
	
	protected ImagemFlyweight imagem;
	
	public Sprite(String nomeImagem){
		imagem = new ImagemFlyweight(nomeImagem);
	}

	@Override
	public Image desenhaImagem() {
		return imagem.desenhaImagem();
	}

}
