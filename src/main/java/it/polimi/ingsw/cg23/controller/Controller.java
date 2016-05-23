package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.observer.*;

import java.util.List;

import it.polimi.ingsw.cg23.model.NewTurn;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Turn;
import it.polimi.ingsw.cg23.model.action.*;

public class Controller implements Observer<Action>{
	
	private final NewTurn turn;
	private final List<Player> players;
	
	public Controller(NewTurn turn,List<Player> players){
		this.turn=turn;
		this.players=players;
	}
	
	/**
	 * Sets the player that will play.
	 * 
	 * @return If true the game is finished and the first player of the list has built all emporiums available.
	 */
	/*public boolean changePlayer() {
		if()
		if((currentPlayer+1)%players.size()!=finalPlayer){     //Control if the next player wasn't the first to build all emporiums.
			currentPlayer=(currentPlayer+1)%players.size();
			this.mainAction=true;
			this.secondAction=true;
			this.mainIndex=1;
			return false;
		}
		return true;
	}*/
	
	@Override
	public void update(Action action){
		System.out.println("I AM THE CONTROLLER UPDATING THE MODEL");
		Observer.super.update(action);
		if(action instanceof Action){
			turn.setAction(action);
			turn.runAction();
		}
		
			
	}

	@Override
	public void update() {
		
	}
	
	
}