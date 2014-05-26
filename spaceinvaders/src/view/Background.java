package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Alien;
import model.Nave;
import model.Tiro;

@SuppressWarnings("serial")
public class Background extends JPanel implements ActionListener {

	private Image background;
	private int velocidade = 1;
	private int pontuacao = 0;
	private int vidas = 5;
	private Nave nave;
	private Timer timer;
	private boolean inGame;
	private List<Alien> aliens;

	// private final int QUANTIDADE_TIROS = 5;

	public Background() {

		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());
		ImageIcon ref = new ImageIcon("res\\03.jpg");
		background = ref.getImage();
		inGame = true;
		nave = new Nave();
		timer = new Timer(5, this);
		timer.start();

	}

	public Background(int res) {

		if (res == 1) {
			setFocusable(true);
			setDoubleBuffered(true);
			addKeyListener(new TecladoAdapter());
			ImageIcon imgBkgrd = new ImageIcon("res\\background 800x600.png");
			inGame = true;
			background = imgBkgrd.getImage();
			inicializaAliens();

			nave = new Nave();
			timer = new Timer(15, this);
			timer.start();
		}

		if (res == 2) {
			setFocusable(true);
			setDoubleBuffered(true);
			addKeyListener(new TecladoAdapter());
			ImageIcon imgBkgrd = new ImageIcon("res\\background 320x180.png");
			background = imgBkgrd.getImage();

			// nave = new Nave();
			// timer = new Timer(5, this);
			// timer.start();
		}

	}

	public void inicializaAliens() {
		aliens = new ArrayList<Alien>();
		Alien al;
		int x, y = 0;
		for (int i = 0; i < 4; i++) {
			y += 60;
			x = 80;
			for (int j = 0; j < 8; j++) {
				x += 70;
				al = new Alien(x, y);
				al.setVelocidade(velocidade);
				aliens.add(al);
			}			
		}
	}

	public void checarColisao() {
		Rectangle formaNave = nave.getBounds();
		Rectangle formaAlien;
		Rectangle formaTiro;

		// Checa a colisão dos aliens com a nave.
		for (int i = 0; i < aliens.size(); i++) {
			Alien tempAlien = aliens.get(i);
			formaAlien = tempAlien.getBounds();

			if (formaNave.intersects(formaAlien) && nave.isVisible()) {
				vidas--;
				nave.setVisible(false);
				tempAlien.setVisible(false);
				//inGame = false;
			}
		}

		// Checa a colisão dos tiros com os aliens.
		Tiro tiro = nave.getTiro();
		if(tiro != null && tiro.isVisible()){
			formaTiro = tiro.getBounds();
			for (int j = 0; j < aliens.size(); j++) {
				Alien tempAlien = aliens.get(j);
				formaAlien = tempAlien.getBounds();

				if (formaTiro.intersects(formaAlien)) {
					ImageIcon icon = new ImageIcon("res\\Explosao.gif");
					tempAlien.setImagem(icon.getImage());
					pontuacao++;					
					
					/*for(int k = 0; k < 2000; k++){
						
					}*/ 
					tempAlien.setVisible(false);
					tiro.setVisible(false);
				}
			}			
		}
			
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(vidas <= 0){
			inGame = false;
			repaint();
			timer.stop();
		}

		if (aliens.size() == 0) {
			velocidade++;
			inicializaAliens();
		}
		Tiro tiro = nave.getTiro();
		if(tiro != null){
			if (tiro.isVisible()) {
				tiro.mover();
			} 
		} else {
			tiro = null;
		}
		

		for (int i = 0; i < aliens.size(); i++) {
			Alien al = aliens.get(i);
			if (al.isVisible()) {
				al.mover();
			} else {
				aliens.remove(i);
			}
		}
		nave.mover();
		checarColisao();
		repaint();
	}

	public void paint(Graphics g) {
		Graphics2D grafico = (Graphics2D) g;
		grafico.drawImage(background, 0, 0, null);
		if (inGame) {

			grafico.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
			
			Tiro tiro = nave.getTiro();				
			if(tiro != null && tiro.isVisible())
				grafico.drawImage(tiro.getImagem(), tiro.getX(), tiro.getY(), this);		

			for (int i = 0; i < aliens.size(); i++) {
				Alien al = aliens.get(i);
				grafico.drawImage(al.getImagem(), al.getX(), al.getY(), this);
			}
			
			grafico.setColor(Color.GREEN);
			grafico.drawString("ALIENS: " + aliens.size(), 5, 15);
			grafico.drawString("PONTUAÇÃO: " +pontuacao, 5, 30);
			grafico.drawString("VIDAS: " +vidas, 5, 45);
		}else{
			ImageIcon ref = new ImageIcon("res\\game_over800.png");
			Image gameOver = ref.getImage();
			grafico.drawImage(gameOver, 0, 0, null);
		}
		g.dispose();
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

}
