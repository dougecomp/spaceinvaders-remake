package controller;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import composite.ImageComposite;
import composite.ScreenComponent;
import composite.StringComposite;
import observer.GameListener;
import observer.PlayerListener;
import model.Alien;
import model.Fase;
import model.Jogo;
import model.JogoMarte;
import model.Nave;
import model.Tiro;
import config.Configuracoes;
import view.Background;
import view.BigSpaceInvaders;

public class GameController implements ActionListener, GameListener {

	private Background view;
	private Fase fase;
	private String nivel;
	private Timer timer;
	private boolean inGame;
	
	public GameController() {
		nivel = "espaco";
		timer = new Timer(15, this);
	}
	
	public void iniciarJogo(){
		int resolucao = Configuracoes.getInstance().getResolucao();
		
		Jogo jogo = null;
		switch(nivel){
			case "espaco":
				jogo = new Jogo();
				break;
			
			case "marte":
				jogo = new JogoMarte();
				break;
				
			default:
				jogo = new Jogo();
				break;
		}		
		
		fase = jogo.montaFase();
		
		Nave nave = fase.getNave();
		for(PlayerListener p: fase.getAliens()){
			nave.addPlayerListener(p);
		}
		fase.setNave(nave);
		
		BigSpaceInvaders bsi = new BigSpaceInvaders();
		
		view = new Background(resolucao);
		/*view.setBackgroundImage(fase.getBackground());*/
		bsi.add(view);
		view.addGameListener(this);
		inGame = true;
		
		timer.start();
	}
	
	/**
	 * GameLoop
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Nave nave = fase.getNave();
		Tiro tiro = nave.getTiro();
		ArrayList<Alien> aliens = fase.getAliens();
		ArrayList<ScreenComponent> componentes = new ArrayList<ScreenComponent>();
		
		if(fase.getNave().getVidas() <= 0){
			gameOver(componentes);
		}
		
		if (aliens.size() == 0) {
			gameOver(componentes);
			//iniciarJogo();
			
		}
		
		if(tiro != null){
			if(tiro.isVisible()){
				tiro.mover();
				componentes.add(new ImageComposite(tiro.getImagem(), tiro.getX(), tiro.getY()));
			} else {
				nave.setTiro(null);
			}
		} else {
			nave.setTiro(null);
		}		

		for (int i = 0; i < aliens.size(); i++) {
			Alien al = aliens.get(i);
			if (al.isVisible()) {
				al.mover();
				componentes.add(new ImageComposite(al.getImagem(), al.getX(), al.getY()));
			} else {
				aliens.remove(i);
			}
		}
		fase.setAliens(aliens);
		
		componentes.add(new ImageComposite(nave.getImagem(), nave.getX(), nave.getY()));
		
		componentes.add(new StringComposite("ALIENS: " + aliens.size(), 5, 15));
		componentes.add(new StringComposite("PONTUAÇÃO: " + nave.getPontuacao(), 5, 30));
		componentes.add(new StringComposite("VIDAS: " + nave.getVidas(), 5, 45));
		
		nave.mover();
		checarColisao();
		view.setImagens(componentes);
		view.repaint();
	}

	@Override
	public void teclaApertada(KeyEvent evento) {
		// TODO Auto-generated method stub
		int codeButton = evento.getKeyCode();
		if(codeButton == KeyEvent.VK_SPACE){
			fase.getNave().atira();
		}
		if(codeButton == KeyEvent.VK_LEFT){
			fase.getNave().setDx(-2);
		}
		if(codeButton == KeyEvent.VK_RIGHT){
			fase.getNave().setDx(2);
		}
	}

	@Override
	public void teclaLiberada(KeyEvent evento) {
		// TODO Auto-generated method stub
		int codeButton = evento.getKeyCode();
		
		if(codeButton == KeyEvent.VK_UP){
			fase.getNave().setDy(0);
		}
		if(codeButton == KeyEvent.VK_DOWN){
			fase.getNave().setDy(0);
		}
		if(codeButton == KeyEvent.VK_LEFT){
			fase.getNave().setDx(0);
		}
		if(codeButton == KeyEvent.VK_RIGHT){
			fase.getNave().setDx(0);
		}
	}

	public void checarColisao() {
		Nave nave = fase.getNave();
		ArrayList<Alien> aliens = fase.getAliens();
		Tiro tiro = nave.getTiro();
		
		Rectangle formaNave = nave.getBounds();
		Rectangle formaAlien;
		Rectangle formaTiro;

		// Checa a colisão dos aliens com a nave.
		for (int i = 0; i < aliens.size(); i++) {
			Alien tempAlien = aliens.get(i);
			formaAlien = tempAlien.getBounds();

			if (formaNave.intersects(formaAlien) && nave.isVisible()) {
				int vidas = nave.getVidas();
				nave.setVidas(vidas--);
				nave.setVisible(false);
				tempAlien.setVisible(false);
				//inGame = false;
			}
		}

		// Checa a colisão do tiro com os aliens.		
		if(tiro != null && tiro.isVisible()){
			formaTiro = tiro.getBounds();
			for (int j = 0; j < aliens.size(); j++) {
				Alien tempAlien = aliens.get(j);
				formaAlien = tempAlien.getBounds();

				if(formaTiro.intersects(formaAlien)) {
					ImageIcon icon = new ImageIcon("res\\Explosao.gif");
					tempAlien.setImagem(icon.getImage());
					tiro.disparaMatouAlien();
					/*for(int k = 0; k < 2000; k++){
						
					}*/
					tempAlien.setVisible(false);
					tiro.setVisible(false);
				}
			}			
		}			
	}
	
	/**
	 * Mostrar tela de fim de jogo, encerrando o gameloop. A tela não é fechada.
	 * @param componentes
	 */
	public void gameOver(ArrayList<ScreenComponent> componentes){
		inGame = false;
		ImageIcon ref = new ImageIcon("res\\game_over800.png");
		Image gameOver = ref.getImage();
		componentes.add(new ImageComposite(gameOver, 0, 0));
		view.repaint();			
		timer.stop();
	}
}
