package it.polimi.ingsw.cg23.server.controller;
/**
 * 
 * main class
 */
public class Main {
	private Main(){//costruttore vuoto
		
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		Avvio game=new Avvio("RegionCity.xml");
		game.startPartita();
	}
}
