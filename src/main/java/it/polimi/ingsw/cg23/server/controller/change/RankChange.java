package it.polimi.ingsw.cg23.server.controller.change;

import java.util.List;

import it.polimi.ingsw.cg23.server.model.Player;

public class RankChange implements Change {

	private static final long serialVersionUID = -6189643065363319364L;
	private List<Player> newRank;

	/**
	 * The constructor of the StateChange
	 * @param newState The new state.
	 */
	public RankChange(List<Player> newRank) {
		this.newRank=newRank;
	}
	
	/**
	 * Returns the rank of the game.
	 * @return newRank.
	 */
	public List<Player> getRank(){
		return newRank;
	}
	
	/**
	 * It generates a string formed by the most significant statistics of the RankChange.
	 * @return string
	 */
	@Override
	public String toString() {
		return "RankChange [newRank=" + newRank + "]";
	}
}
