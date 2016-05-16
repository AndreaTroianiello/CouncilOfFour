package it.polimi.ingsw.cg23.controller;

import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Turn;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Partita {

	public void start(){
		Avvio via=new Avvio();
		CliInterface cl=new CliInterface();

		via.startPartita();	
		List<Player> giocatori=via.getGiocatori();
		Board b=via.getBoard();

		//----------turno----------
		for(int i=0; i<giocatori.size(); i++){
			new Turn(giocatori, b);//creato il turno
			cl.print("", "-Creo il turno "+i);
		}

	}
}
