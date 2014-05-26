package observer;

import java.util.EventObject;

import model.Tiro;

@SuppressWarnings("serial")
public class TiroEvent extends EventObject{

	public TiroEvent(Tiro source){
		super(source);		
		
	}
}
