package command;

import java.awt.event.KeyEvent;

public class InputHandler {

	private Command rightButton = new NullCommand();

	private Command leftButton = new NullCommand();

	private Command shotButton = new NullCommand();
	
	private Command reinicioButton = new NullCommand();
	
	public void handleInput(int codeEvent) {

		if (codeEvent == KeyEvent.VK_SPACE) {
			shotButton.execute();
		}else if(codeEvent == KeyEvent.VK_ENTER){
			reinicioButton.execute();
		} else if (codeEvent == KeyEvent.VK_RIGHT) {
			rightButton.execute();
		} else if (codeEvent == KeyEvent.VK_LEFT) {
			leftButton.execute();
		}
	}

	public Command getRightButton() {
		return rightButton;
	}

	public void setRightButton(Command rightButton) {
		this.rightButton = rightButton;
	}

	public Command getLeftButton() {
		return leftButton;
	}

	public void setLeftButton(Command leftButton) {
		this.leftButton = leftButton;
	}

	public Command getShotButton() {
		return shotButton;
	}

	public void setShotButton(Command shotButton) {
		this.shotButton = shotButton;
	}
	public Command getReinicioButton() {
		return reinicioButton;
	}

	public void setReinicioCommand(Command reinicioButton) {
		this.reinicioButton = reinicioButton;
	}
}
