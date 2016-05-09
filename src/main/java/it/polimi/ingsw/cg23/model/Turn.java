package it.polimi.ingsw.cg23.model;

import it.polimi.ingsw.cg23.model.action.Action;
import it.polimi.ingsw.cg23.model.components.Deck;

public class Turn {
	private Player currentPlayer;
	private final Deck deck;
	private Action action;
	
	public Turn(Deck deck){
		this.deck=deck;
		this.currentPlayer=null;
		this.action=null;
	}

	/**
	 * Returns the current player.
	 * 
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets the player that will play.
	 * 
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Returns the deck of politic cards.
	 * 
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * 
	 */
	public void setAction(Action action){
		this.action=action;
	}
	
	/**
	 * The player draws a politic card. If the deck is empty, the turn changes it.
	 */
	public void draw (){
		if(deck.deckIsEmpty())
			deck.changeDeck();
		currentPlayer.addPoliticCard(deck.draw());
	}
	
	/**
	 * 
	 */
	public void runAction(){
		action.runAction();
	}
}
