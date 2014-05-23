package com.spaceinvedersremake.model;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Tiro {
	
	private Image imagem;
	private int x, y, altura, largura;
	private boolean isVisible;
	
	//private static final int LARGURA_TELA = 500;
	//private static final int ALTURA_TELA = 400;
	private static final int VELOCIDADE = 2;
	
	public Tiro(int x, int y){
		
		this.x = x;
		this.y = y;

		ImageIcon imTiro = new ImageIcon("res\\tiro2.png");
		imagem = imTiro.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		isVisible = true;
		
	}
	
	public void mover(){
		this.y -= VELOCIDADE;
		if(this.y < 0){
			setVisible(false);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
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

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

}