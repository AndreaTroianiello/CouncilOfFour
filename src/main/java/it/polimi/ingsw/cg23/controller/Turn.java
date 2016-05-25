package it.polimi.ingsw.cg23.controller;

import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.action.GameAction;
import it.polimi.ingsw.cg23.model.components.Deck;

/**
 * The turn governs the actions of a player set.
 * @author Andrea
 *
 */
public class Turn {
	
	private final List<Player> players;								//The players of the game.
	private int currentPlayer;										//The current player.
	private int finalPlayer;										//The player who has built all emporiums available first.
	private GameAction action;											//The action of the turn.
	private final Board board; 
	private int mainIndex;											//Main action's counter.
	private boolean mainAction;										//Authorization of the main action.
	private boolean secondAction;									//Authorization of the second action
	
	/**
	 * The constructor of Turn.
	 * @param players The list of game's players.
	 * @param board The game's board.
	 */
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
		if((currentPlayer+1)%players.size()!=finalPlayer){     //Control if the next player wasn't the first to build all emporiums.
			currentPlayer=(currentPlayer+1)%players.size();
			this.mainAction=true;
			this.secondAction=true;
			this.mainIndex=1;
			return false;
		}
		return true;
	}
	
	/**
	 * Sets the action to do.
	 */
	public void setAction(GameAction action){
		this.action=action;
	}
	
	/**
	 * It indicates whether a player has used all available emporiums.
	 * 
	 * return finalTurn. It is true if a players has completed the emporiums.
	 */
	 public boolean isFinalTurn(){									//Return true if a player has built all emporiums.
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
		if(action.isMain() && mainAction){							//Control if the action is a main action and it's authorized.
			--mainIndex;											//Decrement the main action's counter.
			if(mainIndex==0)
				mainAction=false;
		}
		if(!action.isMain() && secondAction)						//Control if the action is not a main action and it's authorized.
			secondAction=false;
		if(players.get(currentPlayer).isAdditionalAction()){		//Control if the second action has enabled a new main action.
			mainIndex++;
			mainAction=true;
			players.get(currentPlayer).switchAdditionalAction();
		}
			
	}
	
	/**
	 * Runs the the action and control if the player has.
	 */
	public void runAction(){
		Player player=players.get(currentPlayer);
		if((action.isMain() && mainAction)||(!action.isMain() && secondAction)){		//Control if the action is authorized
			action.runAction(player, board);											//Run the action.
			controlAction();															//Control the authorization.
		}
		if(finalPlayer==-1 && player.isAvailableEmporiumEmpty()){						//If the current player has been the first to build all emporiums.
			finalPlayer=currentPlayer;
			player.getVictoryTrack().setVictoryPoints(player.getVictoryTrack().getVictoryPoints()+3);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Turn [currentPlayer=" + currentPlayer + ", finalPlayer=" + finalPlayer + ", mainIndex=" + mainIndex
				+ ", mainAction=" + mainAction + ", secondAction=" + secondAction + "]";
	}
	
	
}
