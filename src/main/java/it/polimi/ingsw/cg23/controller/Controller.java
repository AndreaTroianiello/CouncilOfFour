package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.observer.*;
import it.polimi.ingsw.cg23.controller.action.Action;
import it.polimi.ingsw.cg23.controller.action.EndTurn;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.action.*;

public class Controller implements Observer<Action>{
	
	private final Board model;
	private final Turn turn;
	
	public Controller(Board model){
		this.model=model;
		this.turn=new Turn(model.getPlayers(),model);
	}
	
	@Override
	public void update(Action action){
		System.out.println("I AM THE CONTROLLER UPDATING THE MODEL");
		Observer.super.update(action);
		if(action instanceof GameAction && "TURN".equals(model.getStatus().getStatus())){
			turn.setAction((GameAction) action);
			turn.runAction();
		}
		/*if(action instanceof MarketAction && model.getStatus().getStatus().contains("MARKET")){
			turn.setAction((GameAction) action);
			turn.runAction();
		}*/
		if(action instanceof EndTurn){
			((EndTurn) action).runAction(turn);
		}
		
			
	}

	@Override
	public void update() {
		
	}
	
	
}