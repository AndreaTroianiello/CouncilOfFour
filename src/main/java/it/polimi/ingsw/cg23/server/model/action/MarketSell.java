package it.polimi.ingsw.cg23.server.model.action;

import java.util.List;

import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.server.model.marketplace.CanBeSold;
import it.polimi.ingsw.cg23.server.model.marketplace.Item;

/**
 * MarketSell is the action that allows you to sell items in the market.
 * 
 * @author Andrea
 *
 */
public class MarketSell extends GameAction implements MarketAction {

	private static final long serialVersionUID = 7634075876339346475L;
	private CanBeSold item;
	private int coins;

	/**
	 * The constructor of MarketSell action.
	 * 
	 * @param item
	 *            The item for sale.
	 * @param coins
	 *            The price of the item.
	 * @throws NullPointerException
	 *             if the parameters are null.
	 */
	public MarketSell(CanBeSold item, int coins) {
		super(false);
		if (item != null)
			this.item = item;
		else
			throw new NullPointerException();
		this.coins = coins;
	}

	/**
	 * Searches the assistants in the player's hand.
	 * 
	 * @param item
	 *            The assistants pool that contains the assistants to search.
	 * @param player
	 *            The owner of the assistants.
	 * @return the item for sale from the player's stats. If the item wasn't
	 *         found returns null.
	 */
	private AssistantsPool searchAssistants(AssistantsPool item, Player player) {
		AssistantsPool playerAssistants = player.getAssistantsPool();
		int assistants = playerAssistants.getAssistants() - item.getAssistants();
		if (assistants >= 0) {
			try {
				playerAssistants.setAssistants(assistants);
				return item;
			} catch (NegativeNumberException e) {
				getLogger().error(e);
				return null;
			}
		}
		return null;
	}

	/**
	 * Searches the politic card in the player's hand.
	 * 
	 * @param item
	 *            The politic card to search.
	 * @param player
	 *            The owner of the politic card.
	 * @return the item for sale from the player's stats. If the item wasn't
	 *         found returns null.
	 */
	private PoliticCard searchPoliticCard(PoliticCard item, Player player) {
		List<PoliticCard> playerHand = player.getHand();
		for (int index = 0; index < playerHand.size(); index++) {
			if (playerHand.get(index).getColor() != null) {
				if (playerHand.get(index).getColor().equals(item.getColor()))
					return playerHand.remove(index);
			} else {
				if (playerHand.get(index).isJolly() == item.isJolly())
					return playerHand.remove(index);
			}
		}
		return null;
	}

	/**
	 * Searches the business permit tile in the player's list of available.
	 * 
	 * @param item
	 *            The business permit tile to search.
	 * @param player
	 *            The owner of the tile.
	 * @return the item for sale from the player's stats. If the item wasn't
	 *         found returns null.
	 */
	private BusinessPermitTile searchTile(BusinessPermitTile item, Player player) {
		List<BusinessPermitTile> playerTiles = player.getAvailableBusinessPermits();
		for (int index = 0; index < playerTiles.size(); index++) {
			if (playerTiles.get(index).getCitiesId().equals(item.getCitiesId()))
				return playerTiles.remove(index);
		}
		return null;
	}

	/**
	 * Searches the item for sale.
	 * 
	 * @param item
	 *            The item to search.
	 * @param player
	 *            The owner of the item.
	 * @return the item for sale from the player's stats. If the item wasn't
	 *         found returns null.
	 */
	private CanBeSold searchItem(CanBeSold item, Player player) {
		CanBeSold newItem = null;
		if (item instanceof BusinessPermitTile) {
			newItem = searchTile((BusinessPermitTile) item, player);
		}

		if (item instanceof PoliticCard) {
			newItem = searchPoliticCard((PoliticCard) item, player);
		}

		if (item instanceof AssistantsPool) {
			newItem = searchAssistants((AssistantsPool) item, player);
		}
		return newItem;
	}

	/**
	 * It allows to run the action.
	 * 
	 * @param player
	 *            The current player of the turn.
	 * @param board
	 *            The model of the game.
	 */
	@Override
	public boolean runAction(Player player, Board board) {
		item = searchItem(item, player);
		if (item != null) {
			board.getMarket().addItemToSell(new Item(item, player, coins));
			board.notifyObserver(new BoardChange(board));
			return true;
		}
		return false;
	}

}
