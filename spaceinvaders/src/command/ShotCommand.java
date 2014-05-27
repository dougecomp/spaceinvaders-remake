package command;

import model.Nave;

public class ShotCommand implements Command{
	
	Nave nave;
	
	public ShotCommand(Nave nave){
		this.nave = nave;
	}

	@Override
	public void execute() {
		nave.atira();
	}

}
