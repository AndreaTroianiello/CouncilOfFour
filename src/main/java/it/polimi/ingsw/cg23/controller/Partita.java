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
		int playerNumber=numeroGiocatori();
		for(int i=0; i<playerNumber; i++){//ciclo per creare i giocatori
			c.createPlayer();
		}
		c.createRegions();//crea le regioni e le citta'
		cl.createMap(cl.leggiXml("ConfigurazionePartita.xml"), playerNumber);
		
		//TODO creazione elementi di gioco
		//TODO turno
	}
	
	/**
	 * 
	 * @return the number of players
	 */
	public int numeroGiocatori(){
		int playerNumber=0;
		while(playerNumber==0){//si continua a ciclare finche' non e' stato inserito un numero valido
			try{
			playerNumber=Integer.parseInt(cl.writeReturnValue("Quanti giocatori siete?", null).toString());
			}catch(NumberFormatException e){
				cl.print(null, "devi inserire un numero");
				playerNumber=0;
			}
		}
		return playerNumber;
	}
}
