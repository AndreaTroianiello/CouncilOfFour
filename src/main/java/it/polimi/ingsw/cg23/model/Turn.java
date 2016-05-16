package it.polimi.ingsw.cg23.model;

import java.util.List;

import it.polimi.ingsw.cg23.model.action.Action;
import it.polimi.ingsw.cg23.model.components.Deck;

public class Turn {
	private final List<Player> players;											// the players of the game.
	private int currentPlayer;												//the current player.
	private int finalPlayer;													//the player who has built all emporiums available first.
	private Action action;
	private final Board board;
	private int mainIndex;
	private boolean mainAction;
	private boolean secondAction;
	
	
	public Turn(List<Player> players,Board board){
		this.players=players;
		this.board=board;
		this.currentPlayer=0;
		this.finalPlayer=-1;
		this.action=null;
		this.mainIndex=1;
		this.mainAction=true;
		this.secondAction=true;
	}

	/**
	 * Returns the current player.
	 * 
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	/**
	 * Sets the player that will play.
	 * 
	 * @return If true the game is finished and the first player of the list has built all emporiums available.
	 */
	public boolean changePlayer() {
		if((currentPlayer+1)%players.size()!=finalPlayer){
			currentPlayer=(currentPlayer+1)%players.size();
			this.mainAction=true;
			this.secondAction=true;
			return false;
		}
		return true;
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
		 return finalPlayer>=0;
	 }
	
	/**
	 * The player draws a politic card. If the deck is empty, the turn changes it.
	 */
	public void draw (){
		Deck deck=board.getDeck();
		if(deck.deckIsEmpty())
			deck.changeDeck();
		players.get(currentPlayer).addPoliticCard(deck.draw());
	}
	
	/**
	 * Sets the action variables at false.
	 */
	public void controlAction(){
		if(action.isMain() && mainAction){
			--mainIndex;
			if(mainIndex==0)
				mainAction=false;
		}
		if(!action.isMain() && secondAction)
			secondAction=false;
	}
	
	/**
	 * Runs the the action and control if the player has.
	 */
	public void runAction(){
		Player player=players.get(currentPlayer);
		if((action.isMain() && mainAction)||(!action.isMain() && secondAction)){
			action.runAction(player, board);
			controlAction();
		}
		if(finalPlayer==-1 && player.isAvailableEmporiumEmpty()){
			finalPlayer=currentPlayer;
			player.setVictoryPoints(player.getVictoryPoints()+3);
		}
	}
}
