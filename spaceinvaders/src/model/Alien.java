package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import config.Configuracoes;
import observer.PlayerEvent;
import observer.PlayerListener;
import observer.TiroEvent;
import observer.TiroListener;

public class Alien implements TiroListener, PlayerListener{
	
	private Image imagem;
	private int x, y, altura, largura;
	private boolean isVisible;
	private int sentido = 0;	
	private int velocidade = 1;
	private Tiro tiro;
	private ThreadTiro threadTiro;

	public Alien(int x, int y){
		this.x = x;
		this.y = y;
		int resolucao = Configuracoes.getInstance().getResolucao();
		
		
		if(resolucao==1){
			ImageIcon alien = new ImageIcon("res\\3D_UFO_spins.gif");
			imagem = alien.getImage();
		}else{
			ImageIcon alien = new ImageIcon("res\\3D_UFO_spins_2.gif");
			imagem = alien.getImage();
		}
		this.altura = imagem.getHeight(null);
		this.largura = imagem.getWidth(null);
		
		
		isVisible = true;
	}
	
	public void calculaProximoTiro(){
		threadTiro = new ThreadTiro(this);
		Thread t = new Thread(threadTiro);
		t.start();
		
	}

	
	public void mover(){
		int largura = Configuracoes.getInstance().getLargura();
		int altura = Configuracoes.getInstance().getAltura();
		
		if(this.y < 0 || this.y > altura) {
			this.y = 0;		
		} else if( this.x <= 0 ){
			this.y += this.altura*0.5 ;
			this.x = 10 + velocidade;
			this.sentido = 0;
		} else if( this.x > 0 && this.x <= largura*0.9 && sentido == 0 ){ //indo pra direita
			this.x += velocidade;
		} else if( this.x > 0 && this.x <= largura*0.9 && sentido == 1 ){ //indo pra esquerda
			this.x -= velocidade;
		} else if( this.x > largura*0.9){
			this.y += this.altura*0.5;
			this.x = (int) (largura*0.9 - velocidade);
			this.sentido = 1;
		}
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

	@Override
	public void playerAtirou(PlayerEvent evento) {
		// TODO Auto-generated method stub
		Nave nave = (Nave) evento.getSource();
		nave.getTiro().addTiroListener(this);
	}

	@Override
	public void tiroMatouPlayer(TiroEvent e) {
		// TODO Auto-generated method stub
		Tiro t = (Tiro) e.getSource();
		t.removeTiroListener(this);
	}

	@Override
	public void tiroMatouAlien(TiroEvent e) {
		// TODO Auto-generated method stub
		Tiro t = (Tiro) e.getSource();
		t.removeTiroListener(this);
		
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Image getImagem() {
		return imagem;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}
	
	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	
	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public Tiro getTiro() {
		return tiro;
	}

	public void setTiro(Tiro tiro) {
		this.tiro = tiro;
	}

}
