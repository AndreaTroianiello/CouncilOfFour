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
 * 
 * @author Andrea
 *
 */
public class ClientModel {
	private Board board;
	private Player player;
	private Logger logger;
	private List<PoliticCard> cards;

	/**
	 * The constructor of ClientModel. Initializes the variables at null.
	 */
	public ClientModel() {
		this.board = null;
		this.player = null;
		this.cards = new ArrayList<>();
		logger = Logger.getLogger(ClientModel.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Returns the game's model on the client.
	 * 
	 * @return the game's model.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Sets the game's model on the client.
	 * 
	 * @param board
	 *            the game's model to set.
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Sets the player of the user.
	 * 
	 * @param player
	 *            The user's player.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Returns the player of the user.
	 * 
	 * @return the user's player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns the cards of the user
	 * 
	 * @return the user's cards
	 */
	public List<PoliticCard> getCards() {
		return cards;
	}

	/**
	 * Finds the city on the game's model using the name.
	 * 
	 * @param cityName
	 *            The name of the city to find.
	 * @return the city, if it isn't found returns null.
	 */
	public City findCity(String cityName) {
		for (int i = 0; i < board.getRegions().size(); i++) {
			City c = board.getRegions().get(i).searchCityById(cityName.toUpperCase().charAt(0));
			if (c != null)
				return c;
		}
		return null;
	}

	/**
	 * Finds the region on the game's model using the name.
	 * 
	 * @param regionName
	 *            The name of the region to find.
	 * @return the region, if it isn't found returns null.
	 */
	public Region findRegion(String regionName) {
		for (int i = 0; i < board.getRegions().size(); i++) {
			if (board.getRegions().get(i).getName().equals(regionName))
				return board.getRegions().get(i);
		}
		return null;
	}

	/**
	 * Finds the color.
	 * 
	 * @param colorName
	 *            The name of the color to find.
	 * @return the color, if it isn't found returns null.
	 */
	public Color findColor(String colorName) {
		return new ColorManager().getColor(colorName);
	}

	/**
	 * Finds the tile among the player's available tile.
	 * 
	 * @param numerTile
	 *            The number of the tile.
	 * @return the tile, if ti isn't found returns null.
	 */
	public BusinessPermitTile findPlayerTile(String numberTile) {
		try {
			int number = Integer.parseInt(numberTile);
			return player.getAvailableBusinessPermits().get(number);
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * Finds the tile among the region's showed tiles.
	 * 
	 * @param numerTile
	 *            The number of the tile.
	 * @param region
	 *            the region of the tile.
	 * @return the tile, if it isn't found returns null.
	 */
	public BusinessPermitTile findRegionTile(String numberTile, Region region) {
		try {
			int number = Integer.parseInt(numberTile);
			return region.getDeck().getShowedDeck().get(number);
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * Finds the item among the market's items.
	 * 
	 * @param numberItem
	 *            The number of the item.
	 * @return the item, if it isn't found returns null.
	 */
	public Item findItem(String numberItem) {
		int number;
		try {
			number = Integer.parseInt(numberItem);
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		}
		Market market = board.getMarket();
		if (number >= market.getItems().size())
			return null;
		else
			return market.getItems().get(number);
	}

	/**
	 * Finds the Politic Card among the player's hand.
	 * 
	 * @param numberCard
	 *            The number of the card.
	 * @return the card, if it isn't found returns null.
	 */
	public PoliticCard findPoliticCard(String numberCard) {
		try {
			int number = Integer.parseInt(numberCard);
			return player.getHand().get(number);
		} catch (NumberFormatException e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * Add the cards that the player chooses for an action in a list
	 * 
	 * @param tokenizer
	 *            a string with the numbers of the chosen cards in the player's
	 *            hand (the numbers are the position of the cards in the hand
	 *            from 0 to the size of the hand minus one)
	 */
	public void makeHand(StringTokenizer tokenizer) {
		cards.clear();
		while (tokenizer.hasMoreTokens()) {
			int i = Integer.parseInt(tokenizer.nextToken());
			if (player.getHand().get(i) != null)
				cards.add(player.getHand().get(i));
		}
	}
}
