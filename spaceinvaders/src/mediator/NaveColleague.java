package mediator;

import model.Nave;

public class NaveColleague extends Colleague {

	private Nave nave;
	
	public NaveColleague(Mediator m, Nave nave) {
		super(m);
		this.nave = nave;
		// TODO Auto-generated constructor stub
	}

	public Nave getNave() {
		return nave;
	}

	public void setNave(Nave nave) {
		this.nave = nave;
	}

}
