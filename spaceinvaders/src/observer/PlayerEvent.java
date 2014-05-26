package observer;

import java.util.EventObject;

import model.Nave;

@SuppressWarnings("serial")
public class PlayerEvent extends EventObject{

	public PlayerEvent(Nave source) {
		super(source);
	}
}
