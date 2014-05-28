package command;

import controller.GameController;
import model.Jogo;
import model.Nave;

public class ReinicioCommand implements Command {
	GameController gameController;
	
	public ReinicioCommand(GameController gameController){
		this.gameController = gameController;
	}

	@Override
	public void execute() {
		if(!gameController.isInGame()){
			gameController.bsi.dispose();
			gameController.iniciarJogo();	
		
		}
		
		
	}
}
