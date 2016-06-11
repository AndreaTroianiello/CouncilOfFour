package it.polimi.ingsw.cg23.server.model.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Deck contains the all politic cards of the game. If you discard a card, it is put in this object.
 * @author Andrea
 *
 */
public class Deck implements Serializable {

	private static final long serialVersionUID = 1343800378842862258L;
	private final List<PoliticCard> politicCards ;					//Main deck.
	private final List<PoliticCard> discardedCards;

	/**
	 * The constructor of Deck. Initializes the principal list of politic cards and the list of discarded cards
	 * Also shuffles the principal list.
	 * @param politicCards
	 */
	public Deck(List<PoliticCard> politicCards){
		this.politicCards=politicCards;
		this.discardedCards=new ArrayList<>();
		shuffleDeck();
	}
	
	/**
	 * Draws a politic cards from the main deck.
	 * 
	 * @return the first politic card of the main deck (remove it).Returns null if Deck doesn't contain politic cards.
	 */
	public PoliticCard draw(){
		if(deckIsEmpty())
			changeDeck();
		if(discardedCards.isEmpty()&& deckIsEmpty())
			return null;
		return politicCards.remove(0);
	}
	
	/**
	 * Discards a list of cards.
	 * 
	 * @param cards the list of cards to discard.
	 */
	public void discardCards(List<PoliticCard> cards){
		discardedCards.addAll(cards);
	}
	
	/**
	 * Returns the status of the main deck.
	 * 
	 * @return if the main deck is empty returns true.
	 */
	public boolean deckIsEmpty(){
		return politicCards.isEmpty();
	}
	
	/**
	 * If the discarded deck is not empty changes it with the main deck and shuffles. Clears the discarded deck. 
	 */
	private void changeDeck(){
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
	private void shuffleDeck(){
		Random random= new Random();											//Create the random generator.
		for(int index=politicCards.size();index>0;index--){
			int randomIndex=random.nextInt(index);								//Gets a random index.
			PoliticCard cards=politicCards.remove(randomIndex);					//Removes the card at the random index.
			politicCards.add(cards);
		}
	}
	
	/**
	 * It generates a string formed by the most significant statistics of the Deck.
	 * @return string
	 */
	@Override
	public String toString() {
		return "Deck [politicCards=" + politicCards + ", discardedCards=" + discardedCards + "]";
	}
}
