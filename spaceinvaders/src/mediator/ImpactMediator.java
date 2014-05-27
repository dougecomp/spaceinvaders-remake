package mediator;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Alien;
import model.Nave;
import model.Tiro;

public class ImpactMediator implements Mediator{

	private Colleague me;
	
	public ImpactMediator() {
		
	}

	public Colleague getMe() {
		return me;
	}

	public void setMe(Colleague him) {
		this.me = him;
	}

	@Override
	public void verificarChoque(Colleague him) {
		// TODO Auto-generated method stub
		if(me instanceof NaveColleague){ // colide com tiro de nave e com a nave
			verificaChoqueAlien(him);
		} else if(me instanceof TiroColleague){ // colide com alien ou nave
			TiroColleague colTiro = (TiroColleague) me;
			Tiro tiro = colTiro.getTiro();
			Rectangle recTiro = tiro.getBounds();
			if(him instanceof AlienColleague) {
				AlienColleague colAlien = (AlienColleague) him;
				Alien alien = colAlien.getAlien();
				Rectangle recAlien = alien.getBounds();
				if(recTiro.intersects(recAlien)){
					alien.setVisible(false);
					tiro.setVisible(false);
					tiro.disparaMatouAlien();
				}
			}
		}
	}
	
	/**
	 * Verifica Colisão de Alien com Nave e Tiro de Nave
	 * Não é necessário fazer o processo inverso
	 * Este método é executado quando a instancia de 'me' é um NaveColleague
	 * @param him
	 */
	private void verificaChoqueAlien(Colleague him){
		
		NaveColleague colNave = (NaveColleague) me;
		Nave nave = colNave.getNave();
		Rectangle recNave = nave.getBounds();
		if(him instanceof AlienColleague){ // Colisão de alien com nave
			AlienColleague colAlien = (AlienColleague) him;
			Alien alien = colAlien.getAlien();
			Rectangle recAlien = alien.getBounds();				
			if(recNave.intersects(recAlien)){
				int vidas = nave.getVidas();
				nave.setVidas(vidas--);
				nave.setVisible(false);
				alien.setVisible(false);
			}
		} /*else if(him instanceof TiroColleague){ // colisão de alien com tiro (verificar se é da nave)
			TiroColleague colTiro = (TiroColleague) him;
			Tiro tiro = colTiro.getTiro();
			Rectangle recTiro = tiro.getBounds();
			if(recNave.intersects(recTiro)){
				ImageIcon icon = new ImageIcon("res\\Explosao.gif");
				alien.setImagem(icon.getImage());
				tiro.disparaMatouAlien();
			}
		}*/
	}
	
}
