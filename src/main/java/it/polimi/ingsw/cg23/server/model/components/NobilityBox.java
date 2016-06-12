package it.polimi.ingsw.cg23.server.model.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;

public class NobilityBox implements Serializable{
	
	private static final long serialVersionUID = -2228749391593113128L;
	private final List<Bonus> bonus;
	private List<Player> players;

	public NobilityBox() {
		this.bonus = new ArrayList<>();
		this.players = new ArrayList<>();
	}
	
	/**
	 * @return the list of players in the box
	 */
	public List<Player> getPlayers(){
		return players;
	}
	
	/**
	 * adds a player in the list players
	 * @param player
	 */
	public void addPlayer(Player player){
		this.players.add(player);	
	}
	
	/**
	 * removes a player from the list players
	 * @param player
	 */
	public void removePlayer(Player player){
		this.players.remove(player);	
	}

	/**
	 * @return the bonus
	 */
	public List<Bonus> getBonus() {
		return bonus;
	}
	
	/**
	 * add a bonus in the noibilityBox
	 * 
	 * @param bonus
	 */
	public void addBonus(Bonus bonus){
		this.bonus.add(bonus);	
	}

	/**
	 * It generates a string formed by the most significant statistics of the NobilityBox.
	 * @return string
	 */
	@Override
	public String toString() {
		return "NobilityBox [bonus=" + bonus + "]";
	}

}
