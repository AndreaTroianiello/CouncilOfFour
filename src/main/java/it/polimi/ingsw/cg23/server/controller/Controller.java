package it.polimi.ingsw.cg23.server.controller;

import it.polimi.ingsw.cg23.observer.*;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.CreationPlayer;
import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;
import it.polimi.ingsw.cg23.server.controller.change.StateChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.*;
import it.polimi.ingsw.cg23.server.model.components.Emporium;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.server.view.View;
import it.polimi.ingsw.cg23.utility.CreateMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Controller accepts actions for the model and manages the turn.
 * @author Andrea
 *
 */
public class Controller implements Observer<Action>{
	
	private final Board model;
	private Turn turn;
	private final Map<View,Player> interconnections;
	private static Logger logger;
	
	/**
	 * The constructor of the Controller. 
	 * Initializes the turn at null and the map of the connections.
	 * @param model The model of the game.
	 */
	public Controller(Board model){
		this.model=model;
		this.turn=null;
		interconnections=new HashMap<>();
		logger = Logger.getLogger(Controller.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	/**
	 * Sets the player in the map. Connects the player with the view.
	 * @param view The player's view.
	 * @param player The associated player to view.
	 */
	public void putSocketPlayer(View view,Player player){
		this.interconnections.put(view, player);
		this.model.addPlayer(player);
		//this.chat.addView(view);
		
	}
	
	/**
	 * Resets the map of the connections and  cancels the registration on views.
	 */
	public void resetInterconnections(){
		unregisterViews();
		interconnections.clear();
	}
	
	/**
	 * The controller cancels the registration on views.
	 */
	private void unregisterViews(){
		Set<View> views=interconnections.keySet();
		for(View view: views)
			view.unregisterObserver(this);
	}
	
	/**
     * Returns the view from the map. 
     * @param player the owner of the view.
     * @return the player's view. If the view isn't found returns null.
     */
	public View getView(Player player) {
    	if(interconnections.containsValue(player)){
    		Set<View> views=interconnections.keySet();
    		for(View view: views){
    			if(interconnections.get(view).equals(player))
    				return view;
    		}
    	}
    	return null;
    }
	
	/**
	 * Returns the status of all views of the game.
	 * @return if true all view are suspended.
	 */
	public boolean isAllSuspended(){
		Set<View> views=interconnections.keySet();
		boolean result=true;
		for(View view:views)
			result=result&&view.getSuspended();
		return result;
	}
	
	/**
	 * Returns the turn of the game.
	 * @return the turn.
	 */
	public Turn getTurn(){
		return turn;
	}
	
	/**
	 * Returns number of the player created.
	 * @return The size of the map.
	 */
	public int getPlayersNumber(){
		return model.getPlayers().size();
	}
	
	/**
	 * Creates the turn, sets all players and the game's state.
	 */
	public void startGame(){
		turn=new Turn(model);
		model.getStatus().setCurrentPlayer(this.turn.getCurrentPlayer());
		setPlayersHand();
		setPlayerStats();
		gameTwoPlayers();
		new CreateMap().createMapDraw(model);
		model.changeStatus();
		model.notifyObserver(new StateChange(model.getStatus()));
		model.notifyObserver(new BoardChange(model));
		new Thread(new Timer(getView(turn.getCurrentPlayer()),this)).start();
	}
	
	/**
	 * Sets the initial stats of all players.
	 */
	private void setPlayerStats(){
		List<Player> players=model.getPlayers();
		for(int index=0;index<players.size();index++){
			Player player=players.get(index);
			try {
				player.getRichness().setCoins(10+index);
				player.getAssistantsPool().setAssistants(index+1);
			} catch (NegativeNumberException e) {
				logger.error(e);
			}
		}
	}
	
	/**
	 * Sets the additional emporium if the players are only two.
	 */
	private void gameTwoPlayers(){
		Random rnd=new Random();
		if(model.getPlayers().size()==2){
			Player player=new Player("NaN",model.getNobilityTrack());
			List<Region>reg=model.getRegions();
			for(Region r:reg){
				List<Character> list=r.getDeck().getShowedDeck().get(rnd.nextInt(r.getDeck().getShowedDeck().size())).getCitiesId();
				for(char c:list){
					City city=r.searchCityById(c);
					Emporium e=player.getAvailableEmporium();
					e.setCity(city);
					city.getEmporiums().add(e);
				}
				r.getDeck().changeShowedDeck();
			}
		}
	}
	
	/**
	 * Sets the initial hand of all players.
	 */
	private void setPlayersHand(){
		List<Player> players=model.getPlayers();
		for(Player player:players){
			for(int index=0;index<6;++index)
				player.addPoliticCard(model.getDeck().draw());
		}
	}
	
	/**
	 * Controls the action and performs it.
	 */
	@Override
	public synchronized void update(Action action){
		logger.info("I AM THE CONTROLLER UPDATING THE MODEL");		
		if("INITIALIZATION".equals(model.getStatus().getStatus())){
			if(action instanceof CreationPlayer)
				((CreationPlayer) action).runAction(this, model);	
			return;
		}
		notifyAll();
		if(interconnections.get(action.getPlayer())==turn.getCurrentPlayer() &&
			action instanceof GameAction){
			turn.setAction((GameAction) action);
			turn.runAction();
			return;
		}
		if(action instanceof EndTurn && interconnections.get(action.getPlayer())==turn.getCurrentPlayer()){
			((EndTurn) action).runAction(this);
		}
		else
			action.notifyObserver(new InfoChange("Action refused."));
	}
}