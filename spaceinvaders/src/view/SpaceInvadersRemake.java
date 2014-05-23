package com.spaceinvedersremake.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SpaceInvadersRemake extends JFrame{
	
	int width = 500;
	int height = 400;
	
	public SpaceInvadersRemake(){
		add(new Background());
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new SpaceInvadersRemake();
	}

}
