package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RegionDeck {
	private final int MAX_TITLES_SHOWED=2;
	private final List<BusinessPermitTitle> businessPermitHidden;
	private final List<BusinessPermitTitle> businessPermitShowed;
	
	public RegionDeck(){
		businessPermitHidden=new ArrayList<>();
		businessPermitShowed=new ArrayList<>();
	}

	/**
	 * @return the businessPermitHidden
	 */
	public List<BusinessPermitTitle> getBusinessPermitHidden() {
		return businessPermitHidden;
	}

	/**
	 * @return the businessPermitShowed
	 */
	public List<BusinessPermitTitle> getBusinessPermitShowed() {
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
	 * Puts the titles in the right deck.
	 * 
	 * @param businessPermitTitle
	 */
	public void setBusinessPermit(List<BusinessPermitTitle> businessPermitTitle){
		
		shuffleTitle(businessPermitTitle);										//Shuffles the titles.
		for(int index=0;index<MAX_TITLES_SHOWED;++index)						//Puts MAX_TITLES_SHOWED titles in the showed deck. 
			businessPermitShowed.add(businessPermitTitle.remove(0));
		while(!businessPermitTitle.isEmpty())									//Puts the other titles in the hidden deck.
			businessPermitHidden.add(businessPermitTitle.remove(0));
	}
	/**
	 * Shuffles the list of business permit title.
	 * 
	 * @param businessPermitTitle the title that need to shuffle.
	 */
	public void shuffleTitle(List<BusinessPermitTitle> businessPermitTitle){
		Random random= new Random();											//Create the random generator.
		for(int index=businessPermitTitle.size();index>0;index--){
			int randomIndex=random.nextInt(index);								//Gets a random index.
			BusinessPermitTitle title=businessPermitTitle.remove(randomIndex);	//Removes the title at the random index.
			businessPermitTitle.add(title);
		}
	}
	
	/**
	 * 
	 * 
	 */
	public void changeShowedDeck(){
		while(!businessPermitShowed.isEmpty())									//Puts the showed titles in the hidden deck.
			businessPermitHidden.add(businessPermitShowed.remove(0));
		for(int index=0;index<MAX_TITLES_SHOWED;++index)						//Puts MAX_TITLES_SHOWED titles in the showed deck. 
			businessPermitShowed.add(businessPermitHidden.remove(0));
		
	}

}
