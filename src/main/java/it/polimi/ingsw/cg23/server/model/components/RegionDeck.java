package it.polimi.ingsw.cg23.server.model.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The RegionDeck is specific to a Region contains its business permit tiles.
 * @author Andrea
 *
 */
public class RegionDeck implements Serializable {
	
	private static final long serialVersionUID = -6229199735767763627L;
	private final int maxTilesShowed;			
	private final transient List<BusinessPermitTile> hiddenDeck;
	private final List<BusinessPermitTile> showedDeck;
	
	/**
	 * The constructor of Region Deck.
	 * @param maxTilesShowed The number of tiles to show.
	 */
	public RegionDeck(int maxTilesShowed){
		this.maxTilesShowed=maxTilesShowed;
		hiddenDeck=new ArrayList<>();
		showedDeck=new ArrayList<>();
	}

	/**
	 * Returns the number of hidden tiles.
	 * @return the size of hidden deck.
	 */
	public int getHiddenDeckSize() {
		return hiddenDeck.size();
	}

	/**
	 * Returns the list of showed tiles.
	 * @return the showed deck.
	 */
	public List<BusinessPermitTile> getShowedDeck() {
		return showedDeck;
	}
	
	/**
	 * Returns the status of hidden and showed deck. If is true, all decks are empty.
	 * 
	 * @return the status of the all deck.
	 */
	public boolean isEmpty(){
		return hiddenDeck.isEmpty() && showedDeck.isEmpty();
	}
	
	/**
	 * Puts the tiles in the right deck.
	 * 
	 * @param businessPermitTiles
	 */
	public void setBusinessPermitTiles(List<BusinessPermitTile> businessPermitTiles){
		shuffleTitle(businessPermitTiles);	//Shuffles the titles.
		for(int index=0;index<maxTilesShowed;++index)							//Puts maxTilesShowed tiles in the showed deck. 
			showedDeck.add(businessPermitTiles.remove(0));
		while(!businessPermitTiles.isEmpty())									//Puts the other tiles in the hidden deck.
			hiddenDeck.add(businessPermitTiles.remove(0));
	}
	
	/**
	 * Shuffles the list of business permit tile.
	 * 
	 * @param businessPermitTiles the tiles that need to shuffle.
	 */
	private void shuffleTitle(List<BusinessPermitTile> businessPermitTiles){
		Random random= new Random();											//Create the random generator.
		for(int index=businessPermitTiles.size();index>0;index--){
			int randomIndex=random.nextInt(index);								//Gets a random index.
			BusinessPermitTile title=businessPermitTiles.remove(randomIndex);	//Removes the tile at the random index.
			businessPermitTiles.add(title);
		}
	}
	
	/**
	 * Changes the showed tiles with other tiles from the hidden deck.
	 * If you draw a showed card this method don't changes all showed cards,but shows a new card.
	 */
	public void changeShowedDeck(){
		if(showedDeck.size()==maxTilesShowed)											//If the showed deck is full, empties it.
			while(!showedDeck.isEmpty())												//Puts the showed tiles in the hidden deck.
				hiddenDeck.add(showedDeck.remove(0));
		while(showedDeck.size()<maxTilesShowed && !hiddenDeck.isEmpty())				//Puts tiles in the showed deck. 
			showedDeck.add(hiddenDeck.remove(0));
	}

}
