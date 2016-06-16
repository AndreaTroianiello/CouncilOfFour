package it.polimi.ingsw.cg23.server.model.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

public class ControlAction implements Serializable{
	
	private static final long serialVersionUID = 3589241930022739342L;

	/**
	 * controls if the city sent for the bonus exists in the map
	 * @param board
	 * @return
	 */
	public City controlCity(City controlledCity, Board board){
		for(Region region: board.getRegions()){
			for(City city: region.getCities()){
				if(controlledCity.getId() == city.getId()){
					return city;
				}
			}
		}
		return null;
	}
	
	/**
	 * controls if the region sent for the bonus exists in the map
	 * @param board
	 * @return
	 */
	public Region controlRegion(Region controlledRegion, Board board){
		for(Region region: board.getRegions()){
			if(controlledRegion != null && controlledRegion.getName().equals(region.getName())){
				return region;
			}
		}
		return null;
	}
	
	
	/**
	 * control if the player has the chosen cards in his hand
	 * 
	 * @param hand
	 * @param player
	 * @return
	 */
	public List<PoliticCard> controlPoliticCards(List<PoliticCard> hand, Player player){
		List<PoliticCard> playerCards = player.getHand();
		int handSize = hand.size();
		List<PoliticCard> fakeHand = new ArrayList<>();
		fakeHand.addAll(hand);
		List<PoliticCard> realHand = new ArrayList<>();
		for(PoliticCard politicCard: playerCards){
			for(PoliticCard chosenCard: fakeHand){
				if(chosenCard.isJolly() && politicCard.isJolly()){
					realHand.add(politicCard);
					fakeHand.remove(chosenCard);
					break;
				}
				if(politicCard.isJolly() ^ chosenCard.isJolly()){
					break;
				}
				if(politicCard.getColor().equals(chosenCard.getColor())){
					realHand.add(politicCard);
					fakeHand.remove(chosenCard);
					break;
				}
			}
		}
		
		if(realHand.size() == handSize){
			return realHand;
		}
		
		return null;
	}
	
	/**
	 * controls if the player has in his available tiles the chosen tile
	 * @param tile
	 * @param player
	 * @return
	 */
	public BusinessPermitTile controlBusinessPermit(BusinessPermitTile tile, Player player){
		List<BusinessPermitTile> playerTiles = player.getAvailableBusinessPermits();
		for(BusinessPermitTile playerTile: playerTiles){
			if(playerTile.toString().equals(tile.toString())){
				return playerTile;
			}
		}
		return null;
	}
	
	/**
	 * controls if exist the chosen tile in the region showed deck
	 * @param tile
	 * @param region
	 * @return
	 */
	public BusinessPermitTile controlBusinessPermitRegion(BusinessPermitTile  tile, Region region){
		List<BusinessPermitTile> showedTiles = region.getDeck().getShowedDeck();
		for(BusinessPermitTile showedTile: showedTiles){
			if(showedTile.toString().equals(tile.toString())){
				return showedTile;
			}
		}
		return null;
	}
}
