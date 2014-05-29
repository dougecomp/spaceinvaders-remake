package flyweight;

import java.awt.Image;

public class Sprite extends SpriteFlyweight{
	
	protected ImagemFlyweight imagem;
	protected PontoFlyweight ponto;
	
	public Sprite(String nomeImagem){
		imagem = new ImagemFlyweight(nomeImagem);
	}

	public Sprite(String nomeImagem, int x, int y) {
		imagem = new ImagemFlyweight(nomeImagem);
		ponto = new PontoFlyweight(x, y);
	}
	
	@Override
	public Image desenhaImagem() {
		return imagem.desenhaImagem();
	}

	public PontoFlyweight getPonto() {
		return ponto;
	}

	public void setPonto(PontoFlyweight ponto) {
		this.ponto = ponto;
	}
	
}
