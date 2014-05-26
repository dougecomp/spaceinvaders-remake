package mediator;

public abstract class Colleague {
	private Mediator mediator;	
	
	public Colleague(Mediator m) {
		mediator = m;
	}

	public void VerificarChoque() {
		mediator.chocar( this );
	}

	public abstract void receberImpacto(Colleague colleague);
	
}
