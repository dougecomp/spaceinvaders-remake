package view;

import javax.swing.JFrame;

import config.Configuracoes;

@SuppressWarnings("serial")
public class BigSpaceInvaders extends JFrame{
	
	final int width = Configuracoes.getInstance().getLargura();
	final int height = Configuracoes.getInstance().getAltura();
	
	public BigSpaceInvaders(){
		setTitle("Space Invaders");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}//fim do construtor SpaceInvadersGrande.
}//Fim da classe SpaceInvadersGrande.
