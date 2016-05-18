package it.polimi.ingsw.cg23.controller;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Turn;
import it.polimi.ingsw.cg23.model.action.*;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Partita {

	public void start(){
		Avvio via=new Avvio();
		CliInterface cl=new CliInterface();

		via.startPartita();	
		List<Player> giocatori=via.getGiocatori();
		Board b=via.getBoard();
		
		Turn t=new Turn(giocatori, b);//creato il turno
		//----------turno----------
		for(int i=0; i<1; i++){
			cl.print("", "-Creo il turno " + i + " :\n");
			cl.print("", t.getCurrentPlayer().toString());
			cl.print("", t.getCurrentPlayer().getHand().toString());
			cl.print("", t.getCurrentPlayer().getEmporiums().toString());
			cl.print("", b.getRegions().get(1).getDeck().getShowedDeck().toString());
			
			
			t.setAction(new ChangeBusinessPermit(1));
			t.runAction();
			t.runAction();
			cl.print("", b.getRegions().get(1).getDeck().getShowedDeck().toString());
			cl.print("", t.getCurrentPlayer().toString());
			System.out.println(t+"\n");
			t.changePlayer();
		}

	}
}
