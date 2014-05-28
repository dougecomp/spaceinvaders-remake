package flyweight;

import java.util.ArrayList;

public class FlyweightFactory {

	protected ArrayList<SpriteFlyweight> flyweights;

	public enum Sprites {
		NAVE_800X600, INIMIGO_800X600, CENARIO_800X600, TIRO_800X600, GAMEOVER_800X600, NAVE_320X180, INIMIGO_320X180, CENARIO_320X180, TIRO_320X180, GAMEOVER_320X180
	}

	public FlyweightFactory() {
		flyweights = new ArrayList<SpriteFlyweight>();

		flyweights.add(new Sprite("res\\nave1.gif"));
		flyweights.add(new Sprite("res\\3D_UFO_spins.gif"));
		flyweights.add(new Sprite("res\\background 800x600.png"));
		flyweights.add(new Sprite("res\\tiro2.png"));

		flyweights.add(new Sprite("res\\nave_2.gif"));
		flyweights.add(new Sprite("res\\3D_UFO_spins_2.gif"));
		flyweights.add(new Sprite("res\\backgorund 320x180.png"));
		flyweights.add(new Sprite("res\\tiro_2.gif"));
		
		flyweights.add(new Sprite("res\\game_over800.png"));
		flyweights.add(new Sprite("res\\game_over320.png"));

	}

	public SpriteFlyweight getFlyweight(Sprites componente) {
		switch (componente) {
		case NAVE_800X600:
			return flyweights.get(0);
		case INIMIGO_800X600:
			return flyweights.get(1);
		case CENARIO_800X600:
			return flyweights.get(2);
		case TIRO_800X600:
			return flyweights.get(3);
		case NAVE_320X180:
			return flyweights.get(4);
		case INIMIGO_320X180:
			return flyweights.get(5);
		case CENARIO_320X180:
			return flyweights.get(6);
		case TIRO_320X180:
			return flyweights.get(7);
		case GAMEOVER_800X600:
			return flyweights.get(8);
		case GAMEOVER_320X180:
			return flyweights.get(9);
		default:
			return null;

		}
	}
}
