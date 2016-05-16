package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BuildEmporiumTile implements Action {
	
	private final BusinessPermitTile card;
	private final int cityID; 						//wich city the player choose from the ones on the card
	private final boolean main;
	
	public BuildEmporiumTile(BusinessPermitTile card, int cityID) {
		this.card = card;
		this.cityID = cityID;
		this.main = true;
	}
	


	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
	}


	/**
	 * @return the city
	 */
	public int getCityID() {
		return cityID;
	}


	/**
	 * @return the cards
	 */
	public BusinessPermitTile getCard() {
		return card;
	}

	@Override
	public void runAction(Player player, Board board) {
		for(Region region : board.getRegions()){
			City city = region.searchCityById(this.card.getCitiesId().get(this.cityID));
			if(player.getAvailableEmporium() != null){
				try {
					city.buildEmporium(player.getAvailableEmporium());
				} catch (NegativeNumberException e) {
					System.out.println("The player doesn't have available emporiums");
					e.printStackTrace();					
				}
				
			}
			
		}
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BuildEmporiumTile [card=" + card + ", cityID=" + cityID + "]";
	}
	
	
	

}
