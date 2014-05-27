package command;

import model.Nave;

public class RightCommand implements Command{
	
	Nave nave;
	
	public RightCommand(Nave nave){
		this.nave = nave;
	}

	@Override
	public void execute() {
		nave.setDx(2);
	}

}
