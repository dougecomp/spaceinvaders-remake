package mediator;

import model.Alien;

public class AlienCollegue extends Colleague{

	private Alien alien;
	
	public AlienCollegue(Mediator m, Alien alien) {
		super(m);
		this.alien = alien;
		// TODO Auto-generated constructor stub
	}

	public Alien getAlien() {
		return alien;
	}

	public void setAlien(Alien alien) {
		this.alien = alien;
	}

	@Override
	public void receberImpacto(Colleague colleague) {
		// TODO Auto-generated method stub
		
	}
	
	

}
