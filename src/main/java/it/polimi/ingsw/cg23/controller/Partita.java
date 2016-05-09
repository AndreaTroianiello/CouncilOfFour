package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.view.CliInterface;
/**
 * class that start the game
 *
 */
public class Partita {
		CliInterface cl=new CliInterface();
		Controller c=new Controller();
		/**
		 * to start the game
		 */
		public void startPartita(){//metodo per avviare la partita
			cl.print(null, "Benvenuti a cof!");
			//numero di giocatori della partita (richiesto per ora da cl)
			int playerNumber=Integer.parseInt(cl.writeReturnValue("Quanti giocatori siete?", null).toString());
			for(int i=0; i<playerNumber; i++){//ciclo per creare i giocatori
				c.createPlayer();
			}
			//c.p
			c.createRegions();//crea le regioni e le citta'
			c.printList();
			//creazione elementi di gioco
			//turno
		}
}
