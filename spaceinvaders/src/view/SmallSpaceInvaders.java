package com.spaceinvedersremake.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SmallSpaceInvaders extends JFrame{
	
	final int width = 320;
	final int height = 180;
	
	public SmallSpaceInvaders(){
		setTitle("Space Invaders 320x180");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}//Fim do construtor SpaceInvadersPequeno.
}//Fim da classe SpaceInvadersPequeno.
