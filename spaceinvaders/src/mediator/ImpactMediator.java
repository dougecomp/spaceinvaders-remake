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
		if(me instanceof NaveColleague){ // colide com alien ou nave			
			verificaChoqueNave(him);
			
		} else if(me instanceof TiroColleague){ // colide com tiro de nave e com a nave
			
			TiroColleague colTiro = (TiroColleague) me;
			Tiro tiro = colTiro.getTiro();
			Rectangle recTiro = tiro.getBounds();
			
			if(him instanceof AlienColleague) {
				AlienColleague colAlien = (AlienColleague) him;
				Alien alien = colAlien.getAlien();
				
				ImageIcon icon = new ImageIcon("res//Explosao.gif");
				Rectangle recAlien = alien.getBounds();
				if(recTiro.intersects(recAlien)){
					alien.setImagem(icon.getImage());
					alien.setVisible(false);
					tiro.setVisible(false);
					tiro.disparaMatouAlien();
				}
			}

		if(me instanceof NaveColleague){ // colis�o com alien ou tiro de alien
			verificaChoqueNave(him);
		} else if(me instanceof TiroColleague){ // colis�o com alien ou nave
			verificaChoqueTiro(him);

		}
	}}
	
	/**
	 * Verifica Colis�o de Alien com Nave e Tiro de Nave
	 * N�o � necess�rio fazer o processo inverso
	 * Este m�todo � executado quando a instancia de 'me' � um NaveColleague
	 * @param him
	 */
	private void verificaChoqueNave(Colleague him){
		
		NaveColleague colNave = (NaveColleague) me;
		Nave nave = colNave.getNave();
		Rectangle recNave = nave.getBounds();

		if(him instanceof AlienColleague){ // Colis�o de nave com alien

			AlienColleague colAlien = (AlienColleague) him;
			Alien alien = colAlien.getAlien();
			Rectangle recAlien = alien.getBounds();				
			if(recNave.intersects(recAlien)){

				int vidas = nave.getVidas()- 1;
				nave.setVidas(vidas);

				nave.setVisible(false);
				alien.setVisible(false);
				
			}
		} else if(him instanceof TiroColleague){ // colis�o de alien com tiro (verificar se � da nave)
			TiroColleague colTiro = (TiroColleague) him;
			Tiro tiro = colTiro.getTiro();
			Rectangle recTiro = tiro.getBounds();
			if(recNave.intersects(recTiro)){
				int vidas = nave.getVidas() - 1;
				nave.setVidas(vidas);
				nave.setVisible(false);
				tiro.setVisible(false);
			}
		}
	}
	
	/**
	 * Verifica Colis�o de tiro com alien
	 * N�o � necess�rio fazer o processo inverso
	 * Este m�todo � executado quando a instancia de 'me' � um TiroColleague
	 * @param him
	 */
	private void verificaChoqueTiro(Colleague him){
		TiroColleague colTiro = (TiroColleague) me;
		Tiro tiro = colTiro.getTiro();
		Rectangle recTiro = tiro.getBounds();
		if(him instanceof AlienColleague) { // Verificar se o tiro � de nave
			AlienColleague colAlien = (AlienColleague) him;
			Alien alien = colAlien.getAlien();
			Rectangle recAlien = alien.getBounds();
			if(recTiro.intersects(recAlien)){
				alien.setVisible(false);
				tiro.setVisible(false);
				tiro.disparaMatouAlien();
			}
		} else if(him instanceof NaveColleague){
			NaveColleague colNave = (NaveColleague) him;
			Nave nave = colNave.getNave();
			Rectangle recNave = nave.getBounds();
			if(recTiro.intersects(recNave)){
				int vidas = nave.getVidas();
				nave.setVidas(--vidas);
				nave.setVisible(false);
				tiro.setVisible(false);
				tiro.disparaMatouPlayer();
			}
		}	
}
	}
