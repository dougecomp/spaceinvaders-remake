package com.spaceinvedersremake.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainPanel extends JFrame{
	
	private int width = 300;
	private int height = 150;

	public static void main(String[] args) {
		new MainPanel();
	}
	
	public MainPanel(){
		add(new BgMainPanel(this));
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
}
