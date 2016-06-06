package it.polimi.ingsw.cg23.controller.creation;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.view.Print;

public class CreatePlayer {
	private List <Player> giocatori;
	
	private Print p;
	
	
	public CreatePlayer(){
		this.giocatori = new ArrayList<>();//lista giocatori
		this.p= new Print();
	}
	
	/**
	 * create and add a player to the list
	 * @param n , the name of player, if null the name is ask from the cl
	 */
	public void createPlayer(NobilityTrack nT){
		int assistant=playerNumber();//numero di giocatori gia' presenti nella lista
		
		String name="player";
			name=p.writeReturnValue("Come ti chiami giocatore?",null).toString();//recupero il nome del giocatore
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
