package it.polimi.ingsw.cg23.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.view.CliInterface;

public class Controller {
	List <Player> giocatori = new ArrayList<>();//lista giocatori
	NobilityTrack nT=new NobilityTrack(20);//20 e' la lunghezza del percorso nobilta'
	CliInterface cl=new CliInterface();

	public void addPlayer(){
		int assistant=giocatori.size();//numero di giocatori gia' presenti nella lista
		String name=cl.writeReturnValue("come ti chiami?",null).toString();//recupero il nome del giocatore
		Player P=new Player(name, assistant+10, 0, nT);
		giocatori.add(P);//aggiunge un giocatore alla lista
	}
	
}
