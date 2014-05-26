package mediator;

public class ImpactMediator implements Mediator{

	private Colleague me;
	
	public ImpactMediator(Colleague me) {
		this.me = me;
	}

	public Colleague getMe() {
		return me;
	}

	public void setMe(Colleague him) {
		this.me = him;
	}

	@Override
	public void chocar(Colleague colleague) {
		// TODO Auto-generated method stub
		
	}
	
	private void definirTratamento(Colleague him){
		
		if(me instanceof AlienCollegue){ // colide com tiro de nave e com a nave
			
		} else if(me instanceof NaveColleague){ // colide com alien e tiro de alien
			
		} else if(me instanceof TiroColleague){ // colide com alien e nave 
			
		}
	}
	
}
