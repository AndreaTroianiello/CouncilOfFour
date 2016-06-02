package it.polimi.ingsw.cg23.model.action;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/** 
 * the class of the action that allows to build an emporium by using a permitTile. I contains the BusinessPermitTile
 * that the player chooses, the city-id chosen in the tile and a boolean that show if it is a main action or not.
 *
 * @author Vincenzo
 */
public class BuildEmporiumTile extends GameAction {
	
	private static final long serialVersionUID = -5184613644917685573L;
	private final BusinessPermitTile card;
	private final int cityID; 						//wich city the player choose from the ones on the card

	private static Logger logger;
	
	/**
	 * the constructor set the variable of the class: the boolean main is set to true, the city and the cityId
	 * are set to the value of the paramater given
	 * 
	 * @param card
	 * @param cityID
	 */
	public BuildEmporiumTile(BusinessPermitTile card, int cityID) {
		super(true);
		logger = Logger.getLogger(BuildEmporiumTile.class);
		this.card = card;
		this.cityID = cityID;
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

	/**
	 * runAction controls if where is the chosen city and if the player has available emporiums, and if it has
	 * it builds the emporium in the chosen city
	 * 
	 * @param player
	 * @param board
	 */
	@Override
	public void runAction(Player player, Board board) {
		for(Region region : board.getRegions()){
			City city = region.searchCityById(this.card.getCitiesId().get(this.cityID));
			if(player.getAvailableEmporium() != null && city != null){
				try {
					city.buildEmporium(player.getAvailableEmporium());
					player.getAvailableBusinessPermits().remove(card);
					player.setUsedBusinessPermit(card);
				} catch (NegativeNumberException e) {
					logger.error("The player doesn't have enough assistants", e);
										
				}
				
			}
			
		}
		
	}


	/**
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "BuildEmporiumTile [card=" + card + ", cityID=" + cityID + "]";
	}
	
	
	

}
