package it.polimi.ingsw.cg23.server.controller;


public class Main {
	private Main(){//costruttore vuoto
		
	}
	public static void main(String[] args) {
		Avvio game=new Avvio("ConfigurazionePartita.xml");
		game.startPartita();
	}
}
