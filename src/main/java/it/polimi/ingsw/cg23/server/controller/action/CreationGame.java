package it.polimi.ingsw.cg23.server.controller.action;

import java.util.List;

import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.exception.XmlException;

/**
 * The action CreationPlayer is used to create new players.
 * @author Andrea
 *
 */
public class CreationGame extends Action {

	private static final long serialVersionUID = 3683134725976321975L;
	private final String name;
	private String map;
	
	/**
	 * The constructor of CreationPlayer.
	 * @param name The name of the player.
	 * @throws NullPointerException if the parameters are null.
	 */
	public CreationGame(String name,String map) {
		if(name!=null && map!=null){
			this.name=name;
			this.map=map;
		}else 
			throw new NullPointerException();
	}
	
	/**
	 * Runs the action. If the name is already used doesn't create the player.
	 * @param controller The controller of the game.
	 * @param model The model of the game.
	 */
	public void runAction(Controller controller,Board model){
		createMap(controller, model);
		if(model.getNobilityTrack()!=null)
			createPlayer(controller, model);
	}
	
	/**
	 * It loads map if no one else did. 
	 * @param controller The controller of the game.
	 * @param model The model of the game.
	 */
	private void createMap(Controller controller,Board model){
		if(model.getDeck()==null){
			try {
				new Avvio(map+".xml",model).startPartita();
				this.notifyObserver(new InfoChange("Your map has been chosen."));
			} catch (XmlException e) {
				getLogger().error(e);
				model.resetBoard();
				this.notifyObserver(new InfoChange("Your map wasn't found."));
			}
		}
	}
	
	/**
	 * It creates the player. 
	 * @param controller The controller of the game.
	 * @param model The model of the game.
	 */
	private void createPlayer(Controller controller,Board model){
		boolean exist=false;
		List<Player> players=model.getPlayers();
		for(Player player: players)
			if(name.equals(player.getUser()))
				exist=true;
		if(!exist){
			Player player=new Player(name, model.getNobilityTrack());
			controller.putSocketPlayer(super.getPlayer(), player);
			this.notifyObserver(new PlayerChange(player));
			this.notifyObserver(new InfoChange("Player has been created."));
		}
		else
			this.notifyObserver(new InfoChange("The player already exists."));
	}

}
