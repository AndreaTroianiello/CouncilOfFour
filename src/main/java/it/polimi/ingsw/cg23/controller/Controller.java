package it.polimi.ingsw.cg23.controller;

import it.polimi.ingsw.cg23.observer.*;
import it.polimi.ingsw.cg23.view.View;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.cg23.controller.action.Action;
import it.polimi.ingsw.cg23.controller.action.CreationPlayer;
import it.polimi.ingsw.cg23.controller.action.EndTurn;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.action.*;

/**
 * Controller accepts actions for the model and manages the turn.
 * @author Andrea
 *
 */
public class Controller implements Observer<Action>{
	
	private final Board model;
	private Turn turn;
	private final Map<View,Player> interconnections;
	
	/**
	 * The constructor of the Controller. 
	 * Initializes the turn at null and the map of the connections.
	 * @param model The model of the game.
	 */
	public Controller(Board model){
		this.model=model;
		this.turn=null;
		interconnections=new HashMap<>();
	}
	
	/**
	 * Sets the player in the map. Connects the player with the view.
	 * @param view The player's view.
	 * @param player The associated player to view.
	 */
	public void putSocketPlayer(View view,Player player){
		this.interconnections.put(view, player);
		this.model.addPlayer(player);
		
	}
	
	/**
	 * Returns number of the player created.
	 * @return The size of the map.
	 */
	public int getPlayersNumber(){
		return model.getPlayers().size();
	}
	
	/**
	 * Creates the turn and sets the state of the game.
	 */
	public void startGame(){
		this.turn=new Turn(model);
		this.turn.changePlayer();
		this.model.changeStatus();
	}
	
	/**
	 * Controls the action and performs it.
	 */
	@Override
	public synchronized void update(Action action){
		System.out.println("I AM THE CONTROLLER UPDATING THE MODEL");
		
		
		if("INITIALIZATION".equals(model.getStatus().getStatus())){
			if(action instanceof CreationPlayer)
				((CreationPlayer) action).runAction(this, model);	
			return;
		}
		if(interconnections.get(action.getPlayer())==turn.getCurrentPlayer() &&
			action instanceof GameAction){
			performAction((GameAction) action);
			return;
		}
		if(action instanceof EndTurn && interconnections.get(action.getPlayer())==turn.getCurrentPlayer()){
			((EndTurn) action).runAction(turn);
			return;
		}	
	}

	/**
	 * Not implemented.
	 */
	@Override
	public void update() {
		
	}
	
	/**
	 * Performs the game action if the state is right.
	 * @param action The incoming action.
	 */
	public void performAction(GameAction action){
		if(action instanceof StandardAction && "TURN".equals(model.getStatus().getStatus())||
		   action instanceof MarketSell && "MARKET: SELLING".equals(model.getStatus().getStatus())||
		   action instanceof MarketBuy && "MARKET: BUYING".equals(model.getStatus().getStatus())){
			turn.setAction((GameAction) action);
			turn.runAction();}
	}
}