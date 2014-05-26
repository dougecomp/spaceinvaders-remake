package model;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import config.Configuracoes;
import observer.PlayerEvent;
import observer.PlayerListener;
import observer.TiroEvent;
import observer.TiroListener;


public class Nave implements TiroListener {
	
	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private Image imagem;
	private Tiro tiro;
	private boolean isVisible;
	private int vidas = 3;
	private int pontuacao = 0;
	private ArrayList<PlayerListener> playerListeners = new ArrayList<PlayerListener>();
	
	public Nave(){
		ImageIcon referencia = new ImageIcon("res\\nave1.gif");
		imagem = referencia.getImage();		
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		
		this.x = (int) (Configuracoes.getInstance().getLargura()*0.5);
		this.y = (int) (Configuracoes.getInstance().getAltura() - 1.5*altura);
		
		tiro = null;
	}
	
	public void mover() {
		System.out.println(x + ", " + y);
		x += dx;
		
		if(this.x <= 0)
			this.x = 1;
		if(this.x > Configuracoes.getInstance().getLargura()*0.9)
			this.x = (int) (Configuracoes.getInstance().getLargura()*0.9);

	}
	
	public void atira(){
		if(tiro == null ){
			tiro = new Tiro(x+largura/2, y);
			tiro.addTiroListener(this);
			disparaPlayerAtirou();
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
	public void disparaPlayerAtirou() {
		PlayerEvent evento = new PlayerEvent(this);
		for(PlayerListener p: playerListeners) {
			p.playerAtirou(evento);
		}
	}
	
	public void addPlayerListener(PlayerListener p){
		if(!playerListeners.contains(p)){
			playerListeners.add(p);
		}
	}

	public void removePlayerListener(PlayerListener p){
		playerListeners.remove(p);
	}
	
	@Override
	public void tiroMatouPlayer(TiroEvent e) {
		// TODO Auto-generated method stub
		Tiro t = (Tiro) e.getSource();
		t.removeTiroListener(this);
		vidas--;
	}

	@Override
	public void tiroMatouAlien(TiroEvent e) {
		// TODO Auto-generated method stub
		Tiro t = (Tiro) e.getSource();
		pontuacao++;
		t.removeTiroListener(this);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDx() {
		return dx;
	}
	
	public void setDx(int dx){
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}
	
	public void setDy(int dy){
		this.dy = dy;
	}

	public Image getImagem() {
		return imagem;
	}
	
	public Tiro getTiro() {
		return tiro;
	}
	
	public void setTiro(Tiro tiro){
		this.tiro = tiro;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

}
