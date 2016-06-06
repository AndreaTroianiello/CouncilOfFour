package it.polimi.ingsw.cg23.server.controller.action;

import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;

/**
 * The action CreationPlayer is used to create new players.
 * @author Andrea
 *
 */
public class CreationPlayer extends Action {

	private static final long serialVersionUID = 3683134725976321975L;
	private final String name;
	
	/**
	 * The constructor of CreationPlayer.
	 * @param name The name of the player.
	 */
	public CreationPlayer(String name) {
		this.name=name;
	}
	
	/**
	 * Runs the action.
	 * @param controller The controller of the game.
	 * @param model The model of the game.
	 */
	public void runAction(Controller controller,Board model){
		Player player=new Player(name, 0, 0, model.getNobilityTrack());
		controller.putSocketPlayer(super.getPlayer(), player);
	}

}
