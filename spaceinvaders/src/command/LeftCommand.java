package command;

import model.Nave;

public class LeftCommand implements Command{
	
	Nave nave;
	
	public LeftCommand(Nave nave){
		this.nave = nave;
	}

	@Override
	public void execute() {
		nave.setDx(-2);
	}

}
