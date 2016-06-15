package it.polimi.ingsw.cg23.server.controller;

import it.polimi.ingsw.cg23.server.controller.xmlreader.ReadCittaXml;
import it.polimi.ingsw.cg23.server.model.exception.XmlException;
import it.polimi.ingsw.cg23.utility.Print;

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
		Avvio game=new Avvio("RegionCity.xml");
		game.startPartita();
		//new Print().printArray(new ReadCittaXml().readFileXml("map/map3.xml"));
		//new Print().printArray(new ReadCittaXml().readFileXml("RegionCity.xml"));
		}
}
