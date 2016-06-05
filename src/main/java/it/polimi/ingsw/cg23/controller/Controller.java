package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.observer.*;
import it.polimi.ingsw.cg23.view.View;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.cg23.controller.action.Action;
import it.polimi.ingsw.cg23.controller.action.CreationPlayer;
import it.polimi.ingsw.cg23.controller.action.EndTurn;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.action.*;

public class Controller implements Observer<Action>{
	
	private final Board model;
	private Turn turn;
	private final Map<View,Player> interconnections;
	
	public Controller(Board model){
		this.model=model;
		this.turn=null;
		interconnections=new HashMap<>();
	}
	
	
	@Override
	public void update(Action action){
		System.out.println("I AM THE CONTROLLER UPDATING THE MODEL");
		
		
		if("INITIALIZATION".equals(model.getStatus().getStatus())){
			if(action instanceof CreationPlayer)
				((CreationPlayer) action).runAction(this, model);	
			return;
		}
		if(interconnections.get(action.getPlayer())==turn.getCurrentPlayer() &&
			(action instanceof GameAction && "TURN".equals(model.getStatus().getStatus())||
			 action instanceof MarketSell && "MARKET: SELLING".equals(model.getStatus().getStatus())||
		     action instanceof MarketBuy && "MARKET: BUYING".equals(model.getStatus().getStatus())
		   )){
			turn.setAction((GameAction) action);
			turn.runAction();
			return;
		}
		if(action instanceof EndTurn && interconnections.get(action.getPlayer())==turn.getCurrentPlayer()){
			((EndTurn) action).runAction(turn);
			return;
		}
		
			
	}

	@Override
	public void update() {
		
	}
	
	public void putSocketPlayer(View view,Player player){
		this.interconnections.put(view, player);
		this.model.addPlayer(player);
		
	}
	
	public int getPlayersNumber(){
		return model.getPlayers().size();
	}
	
	public void startGame(){
		this.turn=new Turn(model.getPlayers(),model);
		this.turn.changePlayer();
		this.model.changeStatus();
	}
}