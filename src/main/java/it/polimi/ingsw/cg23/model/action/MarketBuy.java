package it.polimi.ingsw.cg23.model.action;

import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.model.marketplace.CanBeSold;
import it.polimi.ingsw.cg23.model.marketplace.Item;
import it.polimi.ingsw.cg23.model.marketplace.Market;

/**
 * MarketBuy is the action that allows you to buy items from the market.
 * @author Andrea
 *
 */
public class MarketBuy extends GameAction implements MarketAction {
	
	private Item item;
	
	/**
	 * The constructor of MarketBuy action.
	 * @param item The item to buy.
	 */
	public MarketBuy(Item item) {
		super(false);
		this.item=item;
	}
	
	/**
	 * Compares the assistants with the reference assistants. The item for sale must be a assistants pool.
	 * 
	 * @param itemToCompare The assistants pool to compare.
	 * @return If true the business permit tile is the same.
	 */
	public boolean compareAssistants(AssistantsPool itemToCompare){
		int refenceAssistants=((AssistantsPool) item.getItem()).getAssistants();
		int assistantsToCompare= itemToCompare.getAssistants();
		if(refenceAssistants==assistantsToCompare){
		}
		return false;
	}
	
	/**
	 * Compares the politic card with the reference card. The item for sale must be a politic card.
	 * 
	 * @param itemToCompare The politic card to compare.
	 * @return If true the business permit tile is the same.
	 */
	public boolean comparePoliticCard(PoliticCard itemToCompare){
		PoliticCard refenceItem=(PoliticCard) item.getItem();
		if(itemToCompare.getColor()!=null)
			if(itemToCompare.getColor().equals(refenceItem.getColor()))
				return true;
		else
			if(itemToCompare.isJolly()==refenceItem.isJolly())
				return true;
		return false;
	}
	
	/**
	 * Compares the business permit tile with the reference tile. The item for sale must be a business permit tile.
	 * 
	 * @param itemToCompare The business permit tile to compare.
	 * @return If true the business permit tile is the same.
	 */
	public boolean compareTile(BusinessPermitTile itemToCompare){
		BusinessPermitTile refenceItem=(BusinessPermitTile) item.getItem();
		if(itemToCompare.getCitiesId().equals(refenceItem.getCitiesId()))
				return true;
		return false;
	}
	
	/**
	 * Compares the item with the reference item. This method selected according to the type of item for sale.
	 * 
	 * @param itemToCompare The item to compare.
	 * @return If true the item is the same.
	 */
	public boolean compareItems(CanBeSold itemToCompare){
		if(itemToCompare instanceof BusinessPermitTile && item.getItem() instanceof BusinessPermitTile)
			return compareTile((BusinessPermitTile) itemToCompare);
		if(itemToCompare instanceof PoliticCard && item.getItem() instanceof PoliticCard)
			return comparePoliticCard((PoliticCard) itemToCompare);	
		if(itemToCompare instanceof AssistantsPool && item.getItem() instanceof AssistantsPool)
			return true;
		return false;
	}
	
	/**
	 * Searches the item in the list of items for sale.
	 * 
	 * @param board the game's board.
	 * @return the item for sale from the market's list. If the item wasn't found returns null.
	 */
	public Item searchItem(Board board){
		Market market=board.getMarket();
		List<Item> items=market.getItems();
		for(Item i: items){
			if(item.getPlayer().getUser().equals(i.getPlayer().getUser())){
				if(compareItems(item.getItem()))
					return i;	
			}
		}
		return null;
	}
	
	/**
	 * Searches the item in the list of items for sale.
	 * 
	 * @param board the game's board.
	 * @return the item for sale from the market's list. If the item wasn't found returns null.
	 */
	public void addItem(Player player){
	 
	}
	
	/**
	 * It allows to run the action.
	 * @param player The current player of the turn.
	 * @param board The model of the game.
	 */
	@Override
	public void runAction(Player player,Board board){
		Item realItem=searchItem(board);
		if(realItem!=null){
			int coins=player.getRichness().getCoins();
			try {
				player.getRichness().setCoins(coins-realItem.getCoins());
				
			} catch (NegativeNumberException e) {
			}
		}
		
	}

}
