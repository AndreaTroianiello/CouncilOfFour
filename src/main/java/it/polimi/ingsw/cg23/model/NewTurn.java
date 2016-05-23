package it.polimi.ingsw.cg23.model;

import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.action.Action;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.observer.Observable;

public class NewTurn extends Observable <Change> {
	
	private Player currentPlayer;									//The current player.
	private Action action;											//The action of the turn.
	private final Board board;
	private int mainIndex;											//Main action's counter.
	private boolean mainAction;										//Authorization of the main action.
	private boolean secondAction;									//Authorization of the second action
	
	
	public NewTurn(Board board){
		this.board=board;
		this.currentPlayer=null;
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
		return currentPlayer;
	}
	
	/**
	 * Sets the action to do.
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
		if(currentPlayer.isAdditionalAction()){		//Control if the second action has enabled a new main action.
			mainIndex++;
			mainAction=true;
			currentPlayer.switchAdditionalAction();
		}
			
	}
	
	/**
	 * Runs the the action and control if the player has.
	 */
	public void runAction(){
		if((action.isMain() && mainAction)||(!action.isMain() && secondAction)){		//Control if the action is authorized
			action.runAction(currentPlayer, board);											//Run the action.
			controlAction();															//Control the authorization.
		}
		/*if(finalPlayer==-1 && player.isAvailableEmporiumEmpty()){						//If the current player has been the first to build all emporiums.
			finalPlayer=currentPlayer;
			player.getVictoryTrack().setVictoryPoints(player.getVictoryTrack().getVictoryPoints()+3);
		}*/
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Turn [currentPlayer=" + currentPlayer + ", mainIndex=" + mainIndex
				+ ", mainAction=" + mainAction + ", secondAction=" + secondAction + "]";
	}
	
	
}
