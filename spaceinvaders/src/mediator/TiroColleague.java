package mediator;

import model.Tiro;

public class TiroColleague extends Colleague {

	private Tiro tiro;
	
	public TiroColleague(Mediator m, Tiro tiro) {
		super(m);
		this.tiro = tiro;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void receberImpacto(Colleague colleague) {
		// TODO Auto-generated method stub
		
	}

}
