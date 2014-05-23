package com.spaceinvedersremake.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Classe que cria o painel do JFrame MainPanel.
 * @author JoãoGabrielQA
 *
 */
@SuppressWarnings("serial")
public class BgMainPanel extends JPanel {
	
	final int bWidth = 60;
	final int bHeight = 40;
	
	private JButton bOk, bCancel;
	private ButtonGroup group;
	private JRadioButton bigResolution, smallResolution;
	private BigSpaceInvaders bsi;
	
	/**
	 * Construtor da classe BgMainPanel.
	 * Responsavel por inicializar os RadioButtons e Buttons da canvas e escutar seus eventos.
	 * Recebe como parâmetro MainPanel.
	 * @param canvas
	 */
	public BgMainPanel(final MainPanel canvas){
		bOk = new JButton("Ok");
		bCancel = new JButton("Cancelar");
		bOk.setSize(bWidth, bHeight);
		bCancel.setSize(bWidth, bHeight);
		bigResolution = new JRadioButton();
		bigResolution.setText("800x600");
		smallResolution = new JRadioButton();
		smallResolution.setText("320x180");
		group = new ButtonGroup();
		group.add(bigResolution);
		group.add(smallResolution);
		setLayout(new GridLayout(2, 1));
		add(bigResolution);
		add(smallResolution);
		add(bOk);
		add(bCancel);
		
		bOk.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ok Clicado!");
				
				if(bigResolution.isSelected()){
					System.out.println("800x600");
					bsi = new BigSpaceInvaders();
					bsi.add(new Background(1));
					canvas.dispose();
				}
				if(smallResolution.isSelected()){
					System.out.println("320x180");
					new SmallSpaceInvaders().add(new Background(2));
					canvas.dispose();
				}
			}
		});
		
		bCancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.dispose();
			}
		});
	}//Fim do construtor BgMainPanel
}//Fim da classe BgMainPanel
