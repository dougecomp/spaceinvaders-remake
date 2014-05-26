package observer;

import java.util.EventListener;

public interface TiroListener extends EventListener{

	void tiroMatouPlayer(TiroEvent e);
	
	void tiroMatouAlien(TiroEvent e);
}
