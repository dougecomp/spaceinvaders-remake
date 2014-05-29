package model;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ImageIcon;

import config.Configuracoes;
import flyweight.FlyweightFactory;
import flyweight.FlyweightFactory.Sprites;
import observer.TiroEvent;
import observer.TiroListener;


public class Tiro {
	
	private Image imagem;
	private int x, y, altura, largura;
	private boolean isVisible;
	private ArrayList<TiroListener> tiroListeners = new ArrayList<TiroListener>();
	private int velocidade = 3;
	
	public Tiro(int x, int y){
		
		this.x = x;
		this.y = y;
		
		FlyweightFactory ff = new FlyweightFactory();
		imagem = ff.getFlyweight(Sprites.TIRO).desenhaImagem();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		isVisible = true;
		
	}
	
	public void mover(){
		this.y -= velocidade;
		if(this.y <= 0 || this.y > Configuracoes.getInstance().getLargura()){
			setVisible(false);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
	public void addTiroListener(TiroListener t) {
		if(!tiroListeners.contains(t)){
			tiroListeners.add(t);
		}
	}
	
	public void removeTiroListener(TiroListener t){
		tiroListeners.remove(t);
	}
	
	public void disparaMatouPlayer(){
		TiroEvent evento = new TiroEvent(this);
		for (TiroListener t: tiroListeners){
			t.tiroMatouPlayer(evento);
		}
	}

	public void disparaMatouAlien(){
		TiroEvent evento = new TiroEvent(this);
		Collection<TiroListener> listeners = tiroListeners;
		
		while(!listeners.isEmpty()){
			Iterator<TiroListener> iterator = listeners.iterator();
			if(iterator.hasNext()){
				TiroListener list = iterator.next();
				list.tiroMatouAlien(evento);
			}			
		}
	}
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Image getImagem() {
		return imagem;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	
	

}