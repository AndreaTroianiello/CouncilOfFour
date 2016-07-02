package it.polimi.ingsw.cg23.server.model.marketplace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * The market's class. The market allows a player to sell to other players
 * @author Andrea
 *
 */
public class Market implements Serializable{

	private static final long serialVersionUID = 1070392830721899258L;
	private final List<Item> itemsToSell;


	/**
	 * The constructor of the market. Creates a empty list of items.
	 */
	public Market() {
		this.itemsToSell=new ArrayList<>();	
	}
	
	/**
	 * Returns the list of all items to sell.	
	 * @return
	 */
	public List<Item> getItems(){
		return itemsToSell;
	}
	/**
	 * Returns the list of items to sell.
	 * 
	 * @param newItem  a new item to sell.
	 */
	public void addItemToSell(Item newItem){
		this.itemsToSell.add(newItem);
	}
	
	/**
	 * Adds the business permit tile for sale to the player indicated
	 * @param item The business permit tile for sale.
	 * @param player The player indicated.
	 */
	public void removeBusinessPermitTile(BusinessPermitTile item,Player player){
		player.addAvailableBusinessPermit(item);
	}

	/**
	 * Adds the politic card for sale to the player indicated
	 * @param item The politic card for sale.
	 * @param player The player indicated.
	 */
	public void removePoliticCard(PoliticCard item,Player player){
		player.addPoliticCard(item);
	}
	
	/**
	 * Adds assistants for sale to the player indicated
	 * @param item The assistants pool for sale.
	 * @param player The player indicated.
	 * @throws NegativeNumberException 
	 */
	public void removeAssistants(AssistantsPool item,Player player) throws NegativeNumberException{
		int assistants=item.getAssistants();
		assistants+=player.getAssistantsPool().getAssistants();
		player.getAssistantsPool().setAssistants(assistants);
	}
	
	/**
	 * Removes all items from the list.
	 * @throws NegativeNumberException 
	 */
	public void resetItems() throws NegativeNumberException{
		for(Item item:itemsToSell){
			Player player=item.getPlayer();
			if(item.getItem() instanceof BusinessPermitTile)
				removeBusinessPermitTile((BusinessPermitTile)item.getItem(),player);
			if(item.getItem() instanceof PoliticCard)
				removePoliticCard((PoliticCard)item.getItem(),player);
			if(item.getItem() instanceof AssistantsPool)
				removeAssistants((AssistantsPool)item.getItem(),player);
		}
		this.itemsToSell.clear();
	}

	/**
	 * Returns a shuffle list of players.
	 * @param players The player to shuffle.
	 * @return the list of players.s
	 */
	public List<Player> generatePlayersList(List<Player> players){
		Random random= new Random();											//Create the random generator.
		List<Player> marketList=new ArrayList<>(players);
		//Shuffle the new list.
		for(int index=players.size();index>0;index--){
			int randomIndex=random.nextInt(index);								//Get a random index.
			Player player=marketList.remove(randomIndex);						//Get the player at the random index.
			marketList.add(player);
		}
		return marketList;
	}
}
