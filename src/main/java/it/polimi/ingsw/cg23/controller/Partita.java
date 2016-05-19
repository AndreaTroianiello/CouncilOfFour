package it.polimi.ingsw.cg23.controller;


import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Turn;
import it.polimi.ingsw.cg23.model.action.*;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;
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
			cl.print("", "PR A "+b.getRegions().get(1).getDeck().getShowedDeck().toString());
			cl.print("", "C R "+b.getRegions().get(1).getCouncil().toString());
			t.getCurrentPlayer().addAvailableBusinessPermit(b.getRegions().get(1).getDeck().getShowedDeck().get(0));
			b.getRegions().get(1).getDeck().changeShowedDeck();
			t.setAction(new BuildEmporiumTile(t.getCurrentPlayer().getAvailableBusinessPermits().get(0), 0));
			t.runAction();
			t.runAction();
			cl.print("", "C R "+b.getRegions().get(1).getCouncil().toString());
			cl.print("", "AP "+t.getCurrentPlayer().getAvailableBusinessPermits().toString());
			cl.print("", "UP "+t.getCurrentPlayer().getUsedBusinessPermit().toString());
			cl.print("", "PR A "+b.getRegions().get(1).getDeck().getShowedDeck().toString());
			cl.print("", t.getCurrentPlayer().getHand().toString());
			cl.print("", t.getCurrentPlayer().getEmporiums().toString());
			cl.print("", t.getCurrentPlayer().toString());
			cl.print(t,"");
			t.changePlayer();
		}

	}
}
