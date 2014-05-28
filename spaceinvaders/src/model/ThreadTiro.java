package model;

import java.util.Random;

/**
 * Classe para fazer com que o alien atire de tempos em tempos com um intervalo aleatório
 * entre um tiro e outro.
 *
 */
public class ThreadTiro implements Runnable{

	private Alien alien;
	
	public ThreadTiro(Alien alien){
		this.alien = alien;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.alien != null){
			Random rand = new Random();
			int time = rand.nextInt(15);
			try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tiro t = alien.getTiro();
			if(t == null){
				t = new Tiro( (alien.getX() + (alien.getLargura()/2) ), (alien.getY() + alien.getAltura()) );
				t.setVelocidade(-3);
				alien.setTiro(t);
			}
		}
	}

}
