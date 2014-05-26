package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Alien {
	
	private Image imagem;
	private int x, y, altura, largura;
	private boolean isVisible;
	private final int ALTURA_TELA = 600;
	private int sentido;
	
	private int velocidade = 0;
	
	public Alien(int x, int y){
		this.x = x;
		this.y = y;
		sentido = 0;
		
		ImageIcon alien = new ImageIcon("res\\3D_UFO_spins.gif");
		imagem = alien.getImage();
		this.altura = imagem.getHeight(null);
		this.largura = imagem.getWidth(null);
		isVisible = true;
	}
	
	public void mover(){
		if(this.y > ALTURA_TELA){
			this.y = 0;
		}else if(this.x <= 0 ){
			this.y += 5*velocidade;
			this.x += velocidade;
			this.sentido = 0;
		} else if(this.x > 0 && this.x < 743 && sentido == 0){ //indo pra direita
			this.x += velocidade;
		} else if(this.x > 0 && this.x < 743 && sentido == 1){ //indo pra esquerda
			this.x -= velocidade;
		} else if(this.x >= 743){
			this.y += 5*velocidade;
			this.x -= velocidade;
			this.sentido = 1;
		}
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
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

}
