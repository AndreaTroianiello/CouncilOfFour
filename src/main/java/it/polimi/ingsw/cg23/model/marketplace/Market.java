package it.polimi.ingsw.cg23.model.marketplace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polimi.ingsw.cg23.model.Player;

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
	 * Removes all items from the list.
	 */
	public void resetItems(){
		this.itemsToSell.clear();
	}

	/**
	 * Returns a shuffle list of players.
	 * @param players The player to shuffle.
	 * @return the list of players.s
	 */
	public List<Player> generatePlayersList(List<Player> players){
		Random random= new Random();											//Create the random generator.
		//Clone the list of player.
		List<Player> marketList=new ArrayList<>();								//Create a new list of players.
		for(Player player:players){									
			marketList.add(player);
		}
		//Shuffle the new list.
		for(int index=players.size();index>0;index--){
			int randomIndex=random.nextInt(index);								//Get a random index.
			Player player=marketList.remove(randomIndex);						//Get the player at the random index.
			marketList.add(player);
		}
		return marketList;
	}
}
