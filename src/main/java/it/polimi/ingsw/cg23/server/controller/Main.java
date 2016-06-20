package it.polimi.ingsw.cg23.server.controller;

import java.util.ArrayList;

import it.polimi.ingsw.cg23.server.model.Board;
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
		Avvio game=new Avvio("map8.xml", new Board(null, new ArrayList<>(), new ArrayList<>(), null, null, null));
		game.startPartita();
	}
}
