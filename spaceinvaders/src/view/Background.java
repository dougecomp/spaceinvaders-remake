package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import composite.ScreenComponent;
import flyweight.FlyweightFactory;
import flyweight.FlyweightFactory.Sprites;
import observer.GameListener;

@SuppressWarnings("serial")
public class Background extends JPanel {

	private Image background;
	
	private ArrayList<GameListener> gameListeners = new ArrayList<GameListener>();
	private ArrayList<ScreenComponent> imagens = new ArrayList<ScreenComponent>();

	public Background(int res) {
		
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());
		
		FlyweightFactory ff = new FlyweightFactory();
		background = ff.getFlyweight(Sprites.CENARIO).desenhaImagem();

	}

	public void paint(Graphics g) {
		Graphics2D grafico = (Graphics2D) g;
		grafico.drawImage(background, 0, 0, null);

		for(ScreenComponent img: imagens){
			img.draw(g, this);
		}
		
		g.dispose();
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {			
			disparaTeclaApertada(e);			
		}

		@Override
		public void keyReleased(KeyEvent e) {			
			disparaTeclaLiberada(e);
		}

	}
	
	public void disparaTeclaApertada(KeyEvent evento){
		for(GameListener g: gameListeners){
			g.teclaApertada(evento);
		}
	}
	
	public void disparaTeclaLiberada(KeyEvent evento){
		for(GameListener g: gameListeners){
			g.teclaLiberada(evento);
		}
	}
	
	public void addGameListener(GameListener g){
		if(!gameListeners.contains(g)){
			gameListeners.add(g);
		}
	}
	
	public void removeGameListener(GameListener g){
		gameListeners.remove(g);
	}
	
	public ArrayList<ScreenComponent> getImagens() {
		return imagens;
	}

	public void setImagens(ArrayList<ScreenComponent> imagens) {
		this.imagens = imagens;
	}

	public Image getBackgroundImage() {
		return background;
	}

	public void setBackgroundImage(Image background) {
		this.background = background;
	}
	
	

}
