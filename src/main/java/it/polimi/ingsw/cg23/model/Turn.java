package it.polimi.ingsw.cg23.model;

import java.util.List;

import it.polimi.ingsw.cg23.model.action.Action;
import it.polimi.ingsw.cg23.model.components.Deck;

public class Turn {
	private final List<Player> players;											// the players of the game.
	private Player currentPlayer;												//the current player.
	private Player finalPlayer;													//the player who has built all emporiums available first. 
	private Action action;
	private final Board board;
	private boolean finalTurn;
	private boolean mainAction;
	private boolean secondAction;
	
	
	public Turn(List<Player> players,Board board){
		this.players=players;
		this.board=board;
		this.currentPlayer=null;
		this.action=null;
		this.finalTurn=false;
		this.mainAction=true;
		this.secondAction=true;
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
	 * @return If true the game is finished and the first player of the list has built all emporiums available.
	 */
	public boolean changePlayer() {
		if(currentPlayer!=null)
			players.add(currentPlayer);											//Add the current player at the list.
		if(finalTurn && finalPlayer.equals(players.get(0))){					//Control if the first player has finished the game.
			currentPlayer=null;
			return true;
		}
		this.currentPlayer = players.remove(0);									//If false set the player as the current player.
		return false;
	}
	
	/**
	 * Sets the action to do.
	 */
	public void setAction(Action action){
		this.action=action;
	}
	
	/**
	 * It indicates whether a player has used all available emporiums.
	 * 
	 * return finalTurn. It is true if a players has completed the emporiums.
	 */
	 public boolean isFinalTurn(){
		 return finalTurn;
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
	 * Runs the the action and control if the player has.
	 */
	public void runAction(){
		action.runAction(currentPlayer, board);
		if(!finalTurn && currentPlayer.isAvailableEmporiumEmpty()){
			finalTurn=true;
			currentPlayer.setVictoryPoints(currentPlayer.getVictoryPoints()+3);
		}
	}
}
