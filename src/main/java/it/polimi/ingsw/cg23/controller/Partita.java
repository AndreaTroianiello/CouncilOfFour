package it.polimi.ingsw.cg23.controller;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.action.*;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.view.Print;

public class Partita {
	Avvio via;
	Print cl;
	
	public Partita(){
		via=new Avvio();
		cl=new Print();
	}
	
	public void start(){
		
		

		
		via.startPartita();	
		List<Player> giocatori=via.getGiocatori();
		List<PoliticCard> cards = new ArrayList<>();
		cards.add(new PoliticCard(Color.BLACK, false));
		cards.add(new PoliticCard(Color.BLUE, false));
		cards.add(new PoliticCard(null, true));
		Board b=via.getBoard();
		
		Turn t=new Turn(giocatori, b);//creato il turno
		//----------turno----------
		for(int i=0; i<1; i++){
			cl.print("", "-Creo il turno " + i + " :\n");
			try {
				t.getCurrentPlayer().getRichness().setCoins(100);
			} catch (NegativeNumberException e) {
			}
			//Emporium e=t.getCurrentPlayer().getAvailableEmporium();
			//e.setCity(b.getRegions().get(1).searchCityById('J'));
			//b.getRegions().get(1).searchCityById('J').getEmporiums().add(e);
			//b.getRegions().get(1).searchCityById('J').getEmporiums().add(e);
			b.getTypes().get(1).runBonusType(t.getCurrentPlayer());
			cl.print("", t.getCurrentPlayer().toString());
			cl.print("", cards.toString());
			cl.print("", t.getCurrentPlayer().getEmporiums().toString());
			//cl.print("", "C R "+b.getRegions().get(0).getCouncil().toString());
			cl.print("", "C K "+b.getKing().getCouncil().toString());
			City destination=b.getRegions().get(1).searchCityById('F');
			cl.print("",destination.toString());
			cl.print("",b.getKing().getCity().toString());
			cl.print("!!",b.getRegions().get(0).getDeck().getShowedDeck().toString());
			cl.print("",""+b.getKing().getCity().minimumDistance(destination, new ArrayList<City>()));
			//t.setAction(new BuyPermitTile(t.getCurrentPlayer().getHand(), 0,0));
			t.setAction(new BuildEmporiumKing(cards,destination));
			t.runAction();
			t.runAction();
			cl.print("",b.getKing().getCity().toString());
			cl.print("",t.getCurrentPlayer().getAvailableBusinessPermits().toString());
			//cl.print("", "C R "+b.getRegions().get(0).getCouncil().toString());
			cl.print("", cards.toString());
			cl.print("", t.getCurrentPlayer().getEmporiums().toString());
			cl.print("", t.getCurrentPlayer().toString());
			cl.print(t,"");
			t.changePlayer();
		}
		
		//print the map
		cl.createMap(b.getRegions(), giocatori, b.getKing());
	}
}
