package mediator;

import model.Alien;

public class AlienColleague extends Colleague{

	private Alien alien;
	
	public AlienColleague(Mediator m, Alien alien) {
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
}
