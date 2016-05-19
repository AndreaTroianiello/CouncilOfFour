package it.polimi.ingsw.cg23.controller;


import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Turn;
import it.polimi.ingsw.cg23.model.action.*;
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
			try {
				t.getCurrentPlayer().getRichness().setCoins(100);
			} catch (NegativeNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cl.print("", t.getCurrentPlayer().toString());
			cl.print("", t.getCurrentPlayer().getHand().toString());
			cl.print("", t.getCurrentPlayer().getEmporiums().toString());
			cl.print("", "C K "+b.getKing().getCouncil().toString());
			City destination=b.getRegions().get(0).searchCityById('A');
			cl.print("",b.getKing().getCity().toString());
			cl.print("",""+b.getKing().getCity().minimumDistance(destination, new ArrayList<City>()));
			t.setAction(new BuildEmporiumKing(t.getCurrentPlayer().getHand(), destination));
			t.runAction();
			t.runAction();
			cl.print("",b.getKing().getCity().toString());
			cl.print("", t.getCurrentPlayer().getHand().toString());
			cl.print("", t.getCurrentPlayer().getEmporiums().toString());
			cl.print("", t.getCurrentPlayer().toString());
			cl.print(t,"");
			t.changePlayer();
		}

	}
}
