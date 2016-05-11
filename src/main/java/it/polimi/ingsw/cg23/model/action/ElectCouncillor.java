package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class ElectCouncillor extends PrimaryAction implements Action{
	
	private final Councillor councillor;
	private final int region; 											//wich region the player choose 
	private final boolean king;
	
	
	public ElectCouncillor(Councillor councillor, int region, boolean king) {
		this.councillor = councillor;
		this.region = region;
		this.king = king;
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
	public Councillor getCouncillor() {
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
		if(!this.king){
			board.getRegions().get(this.region).getCouncil().getCouncillors().remove(0);				//remove the first councillor in the chosen council
			board.getRegions().get(this.region).getCouncil().getCouncillors().add(this.councillor);		//append the chosen councillor in the same council
			int coin = player.getCoins();
			coin = coin +4;
			try{
				player.setCoins(coin);
			} catch(NegativeNumberException e){
				System.out.println("The bonus makes the player have negative coins");
			}
		}
		else{
			board.getKing().getCouncil().getCouncillors().remove(0);				//remove the first councillor in the chosen council
			board.getKing().getCouncil().getCouncillors().add(this.councillor);		//append the chosen councillor in the same council
			int coin = player.getCoins();
			coin = coin +4;
			try{
				player.setCoins(coin);
			} catch(NegativeNumberException e){
				System.out.println("The bonus makes the player have negative coins");
			}
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
