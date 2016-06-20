package it.polimi.ingsw.cg23.client;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;
import it.polimi.ingsw.cg23.server.model.marketplace.Market;
import it.polimi.ingsw.cg23.utility.ColorManager;

/**
 * The model of the client.
 * @author Andrea
 *
 */
public class ClientModel {
	private Board model;
	private Player player;
	private Logger logger;
	private List<PoliticCard> cards;
	
	/**
	 * The constructor of ClientModel. Initializes the variables at null.
	 */
	public ClientModel() {
		this.model=null;
		this.player=null;
		this.cards=new ArrayList<>();
		logger= Logger.getLogger(ClientModel.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	/**
	 * Returns the game's model on the client.
	 * @return the game's model.
	 */
	public Board getModel() {
		return model;
	}
	
	/**
	 * Sets the game's model on the client.
	 * @param model the game's model to set.
	 */
	public void setModel(Board model) {
		this.model = model;
	}
	
	/**
	 * Sets the player of the user.
	 * @param player The user's player.
	 */
	public void setPlayer(Player player){
		this.player=player;
	}
	
	/**
	 * Returns the player of the user.
	 * @return the user's player.
	 */
	public Player getPlayer(){
		return player;
	}

	public List<PoliticCard> getCards(){
		return cards;
	}
	/**
	 * Finds the city on the game's model using the name.
	 * @param cityName The name of the city to find.
	 * @return if the city isn't find returns null.
	 */
	public City findCity(String cityName){
		for(int i=0; i<model.getRegions().size(); i++){
			City c=model.getRegions().get(i).searchCityById(cityName.toUpperCase().charAt(0));
			if(c!=null)
				return c;
		}
		return null;
	}

	/**
	 * Finds the region on the game's model using the name.
	 * @param regionName The name of the region to find.
	 * @return if the region isn't find returns null.
	 */
	public Region findRegion(String regionName){
		for(int i=0; i<model.getRegions().size(); i++){
			if(model.getRegions().get(i).getName().equals(regionName))
				return model.getRegions().get(i);
		}
		return null;
	}

	/**
	 * Finds the color.
	 * @param colorName The name of the color to find.
	 * @return if the color isn't find returns null.
	 */
	public Color findColor(String colorName){
		return new ColorManager().getColor(colorName);
	}
	
	/**
	 * Finds the tile among the player's available tile.
	 * @param numerTile The number of the tile.
	 * @return if the tile isn't find returns null.
	 */
	public BusinessPermitTile findPlayerTile(String numberTile){
		try{
			int number=Integer.parseInt(numberTile);
			return player.getAvailableBusinessPermits().get(number);
		}catch(NumberFormatException e){
			logger.error(e);
			return null;
		}	
	}
	
	/**
	 * Finds the tile among the region's showed tiles.
	 * @param numerTile The number of the tile.
	 * @param region the region of the tile.
	 * @return if the city isn't find returns null.
	 */
	public BusinessPermitTile findRegionTile(String numberTile,Region region){
		try{
			int number=Integer.parseInt(numberTile);
			return region.getDeck().getShowedDeck().get(number);
		}catch(NumberFormatException e){
			logger.error(e);
			return null;
		}	
	}
	
	/**
	 * Finds the item among the market's items.
	 * @param numerItem The number of the item.
	 * @return if the item isn't find returns null.
	 */
	public Item findItem(String numberItem){
		int number;
		try{
			number=Integer.parseInt(numberItem);
		}catch(NumberFormatException e){
			logger.error(e);
			return null;
		}
		Market market=model.getMarket();
		if(number>=market.getItems().size())
			return null;
		else
			return market.getItems().get(number);
	}
	
	/**
	 * Finds the Politic Card among the player's hand.
	 * @param numberCard The number of the card.
	 * @return if the card isn't find returns null.
	 */
	public PoliticCard findPoliticCard(String numberCard){
		try{
			int number=Integer.parseInt(numberCard);
			return player.getHand().get(number);
		}catch(NumberFormatException e){
			logger.error(e);
			return null;
		}	
	}
	
	public void makeHand(StringTokenizer tokenizer){
		cards.clear();
		while(tokenizer.hasMoreTokens()){
			int i = Integer.parseInt(tokenizer.nextToken());
			if(player.getHand().get(i)!=null)
				cards.add(player.getHand().get(i));
		}
	}
}
