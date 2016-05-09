package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class ElectCouncillor extends PrimaryAction implements Action{
	
	private final Councillor councillor;
	private final int region; 											//wich region the player choose 
	
	
	public ElectCouncillor(Councillor councillor, int region) {
		this.councillor = councillor;
		this.region = region;
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

	
	@Override
	public void runAction(Player player, Board board){
		board.getRegions().get(this.region).getCouncil().getCouncillors().remove(0);				//remove the first councillor in the choosen council
		board.getRegions().get(this.region).getCouncil().getCouncillors().add(this.councillor);		//append the choosen councillor in the same council
		int coin = player.getCoins();
		coin = coin +4;
		try{
			player.setCoins(coin);
		} catch(NegativeNumberException e){
			System.out.println("The bonus makes the player have negative coins");
		}
		
	}

}
