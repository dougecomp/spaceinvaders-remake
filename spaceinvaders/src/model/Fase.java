package model;

import java.util.ArrayList;

public class Fase {

	private Nave nave;
	private ArrayList<Alien> inimigos = new ArrayList<Alien>();
	
	public Fase() {
		
	}
	
	public void addInimigo(Alien entidade){
		inimigos.add(entidade);
	}
	
	public ArrayList<Alien> getAliens(){
		return inimigos;
	}
	
	public void setAliens(ArrayList<Alien> aliens){
		this.inimigos = aliens;
	}
	
	public Nave getNave() {
		return nave;
	}
	
	public void setNave(Nave nave){
		this.nave = nave;
	}
}
