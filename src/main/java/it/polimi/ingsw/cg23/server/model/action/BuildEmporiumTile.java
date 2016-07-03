package it.polimi.ingsw.cg23.server.model.action;


import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.EmporiumsChange;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/** 
 * the class of the action that allows to build an emporium by using a permitTile. I contains the BusinessPermitTile
 * that the player chooses, the city-id chosen in the tile and a boolean that show if it is a main action or not.
 *
 * @author Vincenzo
 */
public class BuildEmporiumTile extends GameAction implements StandardAction{
	
	private static final long serialVersionUID = -5184613644917685573L;
	private final BusinessPermitTile card;
	private final City city; 						//which city the player choose from the ones on the card
	private final ControlAction controlAction;
	
	/**
	 * the constructor set the variable of the class: the boolean main is set to true, the city and the cityId
	 * are set to the value of the parameter given
	 * 
	 * @param card the tile used
	 * @param city where to build the emporium
	 * @throws NullPointerException if the parameters are null.
	 */ 
	public BuildEmporiumTile(BusinessPermitTile card, City city) throws NullPointerException {
		super(true);
		if(card!=null&&city!=null){
			this.card = card;
			this.city = city;
		}else
			throw new NullPointerException();
		controlAction = new ControlAction();
	}
	
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
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
	 * @param player who runs the action
	 * @param board the model of the game
	 * 
	 * @return true if the action is successful, false otherwise
	 */
	@Override
	public boolean runAction(Player player, Board board) {
		City realCity = controlAction.controlCity(this.city, board);
		BusinessPermitTile realTile = controlAction.controlBusinessPermit(card, player);
		if(player.getAvailableEmporium() != null && realCity != null && realTile != null){
			if(realTile.getCitiesId().contains(realCity.getId())){
				try {
					realCity.buildEmporium(player.getAvailableEmporium());
					player.getAvailableBusinessPermits().remove(realTile);
					player.setUsedBusinessPermit(realTile);
					board.notifyObserver(new EmporiumsChange(realCity.getEmporiums()));
					board.notifyObserver(new BoardChange(board));
					return true;
				} catch (NegativeNumberException e) {
					getLogger().error("The player doesn't have enough assistants", e);	
					this.notifyObserver(new InfoChange("The player doesn't have enough assistants"));
					return false;
				}	
			}
			else{
				this.notifyObserver(new InfoChange("The city selected is not in the chosen tile"));
			}
		}
		return false;
	}


	/**
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "BuildEmporiumTile [card=" + card + ", city=" + city + "]";
	}
	
	
	

}
