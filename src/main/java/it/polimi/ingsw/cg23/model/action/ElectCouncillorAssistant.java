package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class ElectCouncillorAssistant extends SecondaryAction implements Action {

	private final Councillor councillor;
	private final int region; 											//wich region the player choose 
	private final boolean king;
	
	
	public ElectCouncillorAssistant(Councillor councillor, int region, boolean king) {
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
		}
		else{
			board.getKing().getCouncil().getCouncillors().remove(0);				//remove the first councillor in the chosen council
			board.getKing().getCouncil().getCouncillors().add(this.councillor);		//append the chosen councillor in the same council
		}
		
		int assistants = player.getAssistants();
		assistants = assistants - 1;
		try {
			player.setAssistants(assistants);
		} catch (NegativeNumberException e) {
			System.out.println("The player doesn't have enough assistants");
			e.printStackTrace();
		}
		
	}
}
