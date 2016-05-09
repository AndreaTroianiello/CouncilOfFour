package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RegionDeck {
	private final int maxTilesShowed;			
	private final List<BusinessPermitTile> businessPermitHidden;
	private final List<BusinessPermitTile> businessPermitShowed;
	
	public RegionDeck(int maxTilesShowed){
		this.maxTilesShowed=maxTilesShowed;
		businessPermitHidden=new ArrayList<>();
		businessPermitShowed=new ArrayList<>();
	}

	/**
	 * @return the size of hidden deck.
	 */
	public int getBusinessPermitHiddenSize() {
		return businessPermitHidden.size();
	}

	/**
	 * @return the businessPermitShowed
	 */
	public List<BusinessPermitTile> getBusinessPermitShowed() {
		return businessPermitShowed;
	}
	
	/**
	 * Returns the status of hidden and showed deck. If is true, all decks are empty.
	 * 
	 * @return the status of the all deck.
	 */
	public boolean isEmpty(){
		return businessPermitHidden.isEmpty() && businessPermitShowed.isEmpty();
	}
	
	/**
	 * Puts the tiles in the right deck.
	 * 
	 * @param businessPermitTile
	 */
	public void setBusinessPermit(List<BusinessPermitTile> businessPermitTile){
		
		shuffleTitle(businessPermitTile);										//Shuffles the titles.
		for(int index=0;index<maxTilesShowed;++index)							//Puts maxTilesShowed tiles in the showed deck. 
			businessPermitShowed.add(businessPermitTile.remove(0));
		while(!businessPermitTile.isEmpty())									//Puts the other tiles in the hidden deck.
			businessPermitHidden.add(businessPermitTile.remove(0));
	}
	
	/**
	 * Shuffles the list of business permit tile.
	 * 
	 * @param businessPermitTiles the titles that need to shuffle.
	 */
	public void shuffleTitle(List<BusinessPermitTile> businessPermitTiles){
		Random random= new Random();											//Create the random generator.
		for(int index=businessPermitTiles.size();index>0;index--){
			int randomIndex=random.nextInt(index);								//Gets a random index.
			BusinessPermitTile title=businessPermitTiles.remove(randomIndex);	//Removes the tile at the random index.
			businessPermitTiles.add(title);
		}
	}
	
	/**
	 * Changes the showed tiles with other tiles from the hidden deck.
	 */
	public void changeShowedDeck(){
		if(businessPermitShowed.size()==maxTilesShowed)											//If the showed deck is full, empties it.
			while(!businessPermitShowed.isEmpty())												//Puts the showed tiles in the hidden deck.
				businessPermitHidden.add(businessPermitShowed.remove(0));
		while(businessPermitShowed.size()<maxTilesShowed && !businessPermitHidden.isEmpty())	//Puts tiles in the showed deck. 
			businessPermitShowed.add(businessPermitHidden.remove(0));
	}

}
