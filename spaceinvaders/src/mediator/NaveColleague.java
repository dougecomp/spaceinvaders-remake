package mediator;

import model.Nave;

public class NaveColleague extends Colleague {

	private Nave nave;
	
	public NaveColleague(Mediator m, Nave nave) {
		super(m);
		this.nave = nave;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receberImpacto(Colleague colleague) {
		// TODO Auto-generated method stub
		
	}

}
