package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import mediator.AlienColleague;
import mediator.ImpactMediator;
import mediator.NaveColleague;
import mediator.TiroColleague;
import model.Alien;
import model.Fase;
import model.Jogo;
import model.JogoMarte;
import model.Nave;
import model.Tiro;
import observer.GameListener;
import observer.PlayerListener;
import view.Background;
import view.BigSpaceInvaders;

import command.InputHandler;
import command.LeftCommand;
import command.ReinicioCommand;
import command.RightCommand;
import command.ShotCommand;
import composite.ImageComposite;
import composite.ScreenComponent;
import composite.StringComposite;
import config.Configuracoes;

public class GameController implements ActionListener, GameListener {

	public BigSpaceInvaders bsi;
	private Background view;
	private Fase fase;
	private String nivel;
	private Timer timer;
	@SuppressWarnings("unused")
	private boolean inGame;
	public BigSpaceInvaders getBsi() {
		return bsi;
	}

	public void setBsi(BigSpaceInvaders bsi) {
		this.bsi = bsi;
	}

	public Background getView() {
		return view;
	}

	public void setView(Background view) {
		this.view = view;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	private InputHandler inputHandler;
	
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
		
		for(Alien a: fase.getAliens()){
			a.calculaProximoTiro();
		}
		bsi = new BigSpaceInvaders();
		
		view = new Background(resolucao);
		bsi.getComponents();
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
			Tiro tiroAlien = al.getTiro();
			if(tiroAlien != null && tiroAlien.isVisible()){				
				tiroAlien.mover();
				componentes.add(new ImageComposite(tiroAlien.getImagem(), tiroAlien.getX(), tiroAlien.getY()));
			} else {
				al.setTiro(null);
			}
			
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
			
		inputHandler = new InputHandler();
		inputHandler.setReinicioCommand(new ReinicioCommand(this));
		inputHandler.setShotButton(new ShotCommand(fase.getNave()));
		inputHandler.setRightButton(new RightCommand(fase.getNave()));
		inputHandler.setLeftButton(new LeftCommand(fase.getNave()));
		inputHandler.handleInput(codeButton);
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
		ImpactMediator im = new ImpactMediator();
		
		im.setMe(new NaveColleague(im, nave));		
		// Checa a colisão dos aliens com a nave.
		for (int i = 0; i < aliens.size(); i++) {			
			Alien tempAlien = aliens.get(i);	
 			im.verificarChoque(new AlienColleague(im, tempAlien));
		}		
		
		im.setMe(new TiroColleague(im, tiro));
		// Checa a colisão do tiro com os aliens.		
		if(tiro != null && tiro.isVisible()){			
			for (int j = 0; j < aliens.size(); j++) {				
				Alien tempAlien = aliens.get(j);				
				im.verificarChoque(new AlienColleague(im, tempAlien));
			}			
		}
		
		for (int j = 0; j < aliens.size(); j++) {
			Alien tempAlien = aliens.get(j);
			Tiro tiroAlien = tempAlien.getTiro();
			im.setMe(new TiroColleague(im, tiroAlien));
			if(tiroAlien != null && tiroAlien.isVisible()){
				im.verificarChoque(new NaveColleague(im, nave));
			}
		}
		
	}
	
	/**
	 * Mostrar tela de fim de jogo, encerrando o gameloop. A tela não é fechada.
	 * @param componentes
	 */
	public void gameOver(ArrayList<ScreenComponent> componentes){
		int resolucao = Configuracoes.getInstance().getResolucao();
		ImageIcon ref =null;
		inGame = false;
		if(resolucao == 1){
		 ref = new ImageIcon("res\\game_over800.png");
		}else{
		 ref = new ImageIcon("res\\game_over320.png");
		}
		Image gameOver = ref.getImage();
		componentes.add(new ImageComposite(gameOver, 0, 0));
		
		view.repaint();			
		timer.stop();
	}
}
