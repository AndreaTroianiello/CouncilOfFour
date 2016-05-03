package it.polimi.ingsw.cg23.model.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Deck {

	private final List<PoliticCard> politicCards ;								//Main deck.
	private final List<PoliticCard> discardedCards;

	public Deck(List<PoliticCard> politicCards){
		this.politicCards=politicCards;
		this.discardedCards=new ArrayList<>();
	}
	
	/**
	 * Draws a politic cards from the main deck.
	 * 
	 * @return the first politic card of the main deck (remove it).
	 */
	public PoliticCard draw(){
		return politicCards.remove(0);
	}
	
	/**
	 * Discards a list of cards.
	 * 
	 * @param cards the list of cards to discard.
	 */
	public void discardCars(List<PoliticCard> cards){
		discardedCards.addAll(cards);
	}
	
	/**
	 * Returns the status of the main deck.
	 * 
	 * @return if the main deck is empty returns true.
	 */
	public boolean DeckIsEmpty(){
		return politicCards.isEmpty();
	}
	
	/**
	 * If the discarded deck is not empty changes it with the main deck and shuffles. Clears the discarded deck. 
	 */
	public void changeDeck(){
		if(!discardedCards.isEmpty()){											//If the discarded deck isn't empty  do it
			politicCards.addAll(discardedCards);
			discardedCards.clear();
			shuffleDeck();
		}
	}
	
	/**
	 * Shuffles the deck.
	 * 
	 * @param politicCards the cards that need to shuffle.
	 */
	public void shuffleDeck(){
		Random random= new Random();											//Create the random generator.
		for(int index=politicCards.size();index>0;index--){
			int randomIndex=random.nextInt(index);								//Gets a random index.
			PoliticCard cards=politicCards.remove(randomIndex);					//Removes the card at the random index.
			politicCards.add(cards);
		}
	}
}
