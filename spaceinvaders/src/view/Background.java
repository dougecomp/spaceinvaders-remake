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

import model.Aliens;
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
	private int[][] coordenadas = { { 29, 2380 }, { 59, 2600 }, { 89, 1380 },
			{ 109, 780 }, { 139, 1789 }, { 239, 880 }, { 259, 954 },
			{ 50, 1456 }, { 150, 790 }, { 209, 1980 }, { 45, 560 }, { 70, 2100 },
			{ 159, 930 }, { 80, 590 }, { 60, 3213 }, { 59, 940 }, { 30, 990 },
			{ 200, 920 }, { 259, 900 }, { 50, 6604 }, { 90, 5401 }, { 220, 810 },
			{ 20, 860 }, { 180, 740 }, { 128, 820 }, { 170, 2126 }, { 30, 700 },
			{ 300, 920 }, { 328, 856 }, { 320, 4672 }, { 450, 760 }, { 470, 790 }, { 730, 1980 }, { 650, 5604 }, { 670, 4821 },
			{ 456, 1456 }, { 732, 590 }, { 678, 530 }, { 520, 940 }, { 573, 990 },
			{ 540, 920 }, { 722, 900 }, { 654, 660 }, { 612, 1345 }, { 419, 810 },
			{ 600, 2765 }, { 750, 740 }, { 642, 820 }, { 449, 1987 }, { 667, 700 },
			{ 500, 920 }, { 725, 856 }, { 618, 2800 } };
	private List<Aliens> aliens;

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
			timer = new Timer(5, this);
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
		aliens = new ArrayList<Aliens>();
		Aliens al;
		for (int i = 0; i < coordenadas.length; i++) {
			al = new Aliens(coordenadas[i][0], coordenadas[i][1]);
			al.setVelocidade(velocidade);
			aliens.add(al);
		}
	}

	public void checarColisao() {
		Rectangle formaNave = nave.getBounds();
		Rectangle formaAlien;
		Rectangle formaTiro;

		// Checa a colisão dos aliens com a nave.
		for (int i = 0; i < aliens.size(); i++) {
			Aliens tempAlien = aliens.get(i);
			formaAlien = tempAlien.getBounds();

			if (formaNave.intersects(formaAlien)) {
				vidas--;
				nave.setVisible(false);
				tempAlien.setVisible(false);
				//inGame = false;
			}
		}

		// Checa a colisão dos tiros com os aliens.
		List<Tiro> tiros = nave.getTiros();

		for (int i = 0; i < tiros.size(); i++) {
			Tiro tempTiro = tiros.get(i);
			formaTiro = tempTiro.getBounds();

			for (int j = 0; j < aliens.size(); j++) {
				Aliens tempAlien = aliens.get(j);
				formaAlien = tempAlien.getBounds();

				if (formaTiro.intersects(formaAlien)) {
					tempAlien.setImagem(new ImageIcon("res\\Explosao.gif").getImage());
					pontuacao++;
					for(int k = 0; k < 2000; k++){
						
					}
					tempAlien.setVisible(false);
					tempTiro.setVisible(false);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(vidas == 0){
			inGame = false;
		}

		if (aliens.size() == 0) {
			velocidade++;
			inicializaAliens();
		}
		List<Tiro> tiros = nave.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro t = tiros.get(i);
			if (t.isVisible()) {
				t.mover();
			} else {
				tiros.remove(i);
			}
		}

		for (int i = 0; i < aliens.size(); i++) {
			Aliens al = aliens.get(i);
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
			List<Tiro> tiros = nave.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro t = tiros.get(i);
				grafico.drawImage(t.getImagem(), t.getX(), t.getY(), this);
			}

			for (int i = 0; i < aliens.size(); i++) {
				Aliens al = aliens.get(i);
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
