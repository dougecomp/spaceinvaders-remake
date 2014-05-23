package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BigSpaceInvaders extends JFrame{
	
	final int width = 800;
	final int height = 600;
	
	public BigSpaceInvaders(){
		setTitle("Space Invaders 800x600");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}//fim do construtor SpaceInvadersGrande.
}//Fim da classe SpaceInvadersGrande.
