package flyweight;

import java.util.ArrayList;

import config.Configuracoes;

public class FlyweightFactory {

	protected ArrayList<SpriteFlyweight> flyweights;
	private int resolucao = Configuracoes.getInstance().getResolucao();

	public enum Sprites {
		NAVE, INIMIGO, CENARIO, TIRO, GAMEOVER
	}

	public FlyweightFactory() {
		flyweights = new ArrayList<SpriteFlyweight>();

		/* --------------  Imagens de alta resolução fase 1-------------- */
		flyweights.add(new Sprite("res\\nave1.gif"));
		flyweights.add(new Sprite("res\\3D_UFO_spins.gif"));
		flyweights.add(new Sprite("res\\background 800x600.png"));
		flyweights.add(new Sprite("res\\tiro2.png"));
		flyweights.add(new Sprite("res\\game_over800.png"));
		/* --------------  Imagens de alta resolução -------------- */
		
		/* --------------  Imagens de baixa resolução fase 1-------------- */
		flyweights.add(new Sprite("res\\nave_2.gif"));
		flyweights.add(new Sprite("res\\3D_UFO_spins_2.gif"));
		flyweights.add(new Sprite("res\\background 320x180.png"));
		flyweights.add(new Sprite("res\\tiro_2.png"));		
		flyweights.add(new Sprite("res\\game_over320.png"));
		/* --------------  Imagens de baixa resolução -------------- */

	}

	public SpriteFlyweight getFlyweight(Sprites componente) {
		switch(resolucao){
			case 1:
				return getFlyweight800x600(componente);
			case 2:
				return getFlyweight320x180(componente);
			default:
				return null;
		}
	}
	
	private SpriteFlyweight getFlyweight800x600 (Sprites componente){
		switch (componente) {
			case NAVE:
				return flyweights.get(0);
			case INIMIGO:
				return flyweights.get(1);
			case CENARIO:
				return flyweights.get(2);
			case TIRO:
				return flyweights.get(3);
			case GAMEOVER:
				return flyweights.get(4);
			default:
				return null;
		}
	}
	
	private SpriteFlyweight getFlyweight320x180 (Sprites componente){
		
		switch (componente) {
		case NAVE:
			return flyweights.get(5);
		case INIMIGO:
			return flyweights.get(6);
		case CENARIO:
			return flyweights.get(7);
		case TIRO:
			return flyweights.get(8);
		case GAMEOVER:
			return flyweights.get(9);
		default:
			return null;
		}
		
	}
}
