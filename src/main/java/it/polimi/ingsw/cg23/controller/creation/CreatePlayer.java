package it.polimi.ingsw.cg23.controller.creation;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.view.CliInterface;

public class CreatePlayer {
	List <Player> giocatori = new ArrayList<>();//lista giocatori
	CliInterface cl=new CliInterface();
	NobilityTrack nT=new NobilityTrack(cl.getNobilityTrackLenght("NobilityTrack.xml"));//recupero la lunghezza dall'xml
	
	/**
	 * create and add a player to the list
	 * @param n , the name of player, if null the name is ask from the cl
	 */
	public void createPlayer(String n){
		int assistant=playerNumber();//numero di giocatori gia' presenti nella lista
		String name;
		if(n==null)
			name=cl.writeReturnValue("Come ti chiami giocatore?",null).toString();//recupero il nome del giocatore
		else
			name=n;
		Player p=new Player(name, assistant+10, 0, nT);//creo i giocatori
		giocatori.add(p);//aggiunge un giocatore alla lista
	}

	/**
	 * 
	 * @return the player list
	 */
	public List<Player> getGiocatori(){
		return giocatori;
	}

	/**
	 * return the number of player in the list
	 * @return the number of player
	 */
	public int playerNumber(){
		return giocatori.size();	
	}
}
