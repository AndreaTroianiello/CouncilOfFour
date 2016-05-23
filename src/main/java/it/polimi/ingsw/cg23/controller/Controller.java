package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.observer.*;

import java.util.List;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Turn;
import it.polimi.ingsw.cg23.model.action.*;

public class Controller implements Observer<Action>{
	
	private final Turn turn;
	private final List<Player> players;
	
	public Controller(Turn turn,List<Player> players){
		this.turn=turn;
		this.players=players;
	}
	
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