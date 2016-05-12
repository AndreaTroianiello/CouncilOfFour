package it.polimi.ingsw.cg23.model;

import it.polimi.ingsw.cg23.model.action.Action;
import it.polimi.ingsw.cg23.model.components.Deck;

public class Turn {
	private Player currentPlayer;
	private Action action;
	private final Board board;
	
	
	public Turn(Board board){
		this.currentPlayer=null;
		this.action=null;
		this.board=board;
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
	 * 
	 */
	public void setAction(Action action){
		this.action=action;
	}
	
	/**
	 * The player draws a politic card. If the deck is empty, the turn changes it.
	 */
	public void draw (){
		Deck deck=board.getDeck();
		if(deck.deckIsEmpty())
			deck.changeDeck();
		currentPlayer.addPoliticCard(deck.draw());
	}
	
	/**
	 * 
	 */
	public void runAction(){
		action.runAction(currentPlayer, board);
	}
	
	/**
	 * It indicates whether the player has used all available emporiums.
	 * 
	 * return the status of availableEmporiums' list. It is true if this list is empty.
	 */
	 public boolean isFinalTurn(){
		 return currentPlayer.isAvailableEmporiumEmpty();
	 }
}
