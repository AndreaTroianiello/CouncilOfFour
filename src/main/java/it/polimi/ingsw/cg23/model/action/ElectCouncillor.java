package it.polimi.ingsw.cg23.model.action;

import java.awt.Color;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class ElectCouncillor implements Action{
	
	private final Color councillor;
	private final int region; 											//wich region the player choose 
	private final boolean king;
	private final boolean main;
	
	
	public ElectCouncillor(Color councillor, int region, boolean king) {
		this.councillor = councillor;
		this.region = region;
		this.king = king;
		this.main = true;
	}
	
	

	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
	}



	/**
	 * @return the king
	 */
	public boolean isKing() {
		return king;
	}



	/**
	 * @return the region
	 */
	public int getRegion() {
		return region;
	}


	/**
	 * @return the councillor
	 */
	public Color getCouncillor() {
		return councillor;
	}

	/**
	 * remove the first councillor from the chosen council and append
	 * the new one
	 * @param player
	 * @param board
	 */
	@Override
	public void runAction(Player player, Board board){
		Councillor newCouncillor=board.getCouncillor(councillor);
		if(!this.king){
			Councillor oldCouncillor=board.getRegions().get(this.region).getCouncil().getCouncillors().remove(0);				//remove the first councillor in the chosen council
			board.setCouncillor(oldCouncillor);
			board.getRegions().get(this.region).getCouncil().getCouncillors().add(newCouncillor);								//append the chosen councillor in the same council
		}
		else{
			Councillor oldCouncillor=board.getKing().getCouncil().getCouncillors().remove(0);				//remove the first councillor in the chosen council
			board.setCouncillor(oldCouncillor);
			board.getKing().getCouncil().getCouncillors().add(newCouncillor);								//append the chosen councillor in the same council
		}
		int coins = player.getRichness().getCoins();
		coins = coins +4;
		try{
			player.getRichness().setCoins(coins);
		} catch(NegativeNumberException e){
			System.out.println("The bonus makes the player have negative coins");
		}		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ElectCouncillor [councillor=" + councillor + ", region=" + region + ", king=" + king + "]";
	}
	
	

}
