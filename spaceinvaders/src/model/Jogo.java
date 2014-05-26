package model;

import java.math.MathContext;

import config.Configuracoes;
import model.Fase;

public class Jogo {

	public Jogo(){
		
	}
	
	public Fase criaFase(){
		return new Fase();
	}
	
	public Nave criaNave(){
		return new Nave();
	}
	
	public Alien criaInimigo(int x, int y){
		return new Alien(x, y);
	}
	
	public Fase montaFase(){
		
		Fase fase = criaFase();
		
		// Criar o jogador
		Nave nave = criaNave();
		fase.setNave(nave);		
		
		// Fazer um loop para criar os inimigos
		int x, y = 0;
		int largura = Configuracoes.getInstance().getLargura();
		int altura = Configuracoes.getInstance().getAltura();
		for (int i = 0; i < 3; i++) {
			y += Math.round(largura*0.1);
			x = (int) Math.round(altura*0.1);
			for (int j = 0; j < 7; j++) {
				x += Math.round(altura*0.1);	
				fase.addInimigo(criaInimigo(x, y));
			}			
		}
		
		return fase;
	}
	
}
