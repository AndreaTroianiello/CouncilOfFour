package it.polimi.ingsw.cg23.server.controller;

import it.polimi.ingsw.cg23.server.model.exception.XmlException;

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
	 * @throws XmlException 
	 */
	public static void main(String[] args) throws XmlException {
		Avvio game=new Avvio("map3.xml");
		game.startPartita();
	}
}
