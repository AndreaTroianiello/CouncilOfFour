package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.observer.*;

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
	private final Turn turn;
	private final Map<SocketAddress,Player> sockets;
	
	public Controller(Board model){
		this.model=model;
		this.turn=new Turn(model.getPlayers(),model);
		this.turn.changePlayer();
		sockets=new HashMap<>();
	}
	
	
	@Override
	public void update(Action action){
		System.out.println("I AM THE CONTROLLER UPDATING THE MODEL");
		
		/*if(action instanceof MarketAction && model.getStatus().getStatus().contains("MARKET")){
			turn.setAction((GameAction) action);
			turn.runAction();
		}*/
		if(action instanceof CreationPlayer){
			((CreationPlayer) action).runAction(this, model);
		}
		if(sockets.get(action.getPlayer())==turn.getCurrentPlayer() &&
			(action instanceof GameAction && "TURN".equals(model.getStatus().getStatus())||
			 action instanceof MarketSell && "MARKET: SELLING".equals(model.getStatus().getStatus())||
		     action instanceof MarketBuy && "MARKET: BUYING".equals(model.getStatus().getStatus())
		   )){
			turn.setAction((GameAction) action);
			turn.runAction();
		}
		if(action instanceof EndTurn && sockets.get(action.getPlayer())==turn.getCurrentPlayer()){
			((EndTurn) action).runAction(turn);
		}
		
			
	}

	@Override
	public void update() {
		
	}
	
	public void putSocketPlayer(SocketAddress socketAddress,Player player){
		this.sockets.put(socketAddress, player);
		this.model.addPlayer(player);
		
	}
}