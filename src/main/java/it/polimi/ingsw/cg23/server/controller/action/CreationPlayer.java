package it.polimi.ingsw.cg23.server.controller.action;

import java.util.List;

import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.controller.change.InfoChange;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
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
	 * @throws NullPointerException if the parameters are null.
	 */
	public CreationPlayer(String name) throws NullPointerException {
		if(name!=null)
			this.name=name;
		else 
			throw new NullPointerException();
	}
	
	/**
	 * Runs the action. If the name is already used doesn't create the player.
	 * @param controller The controller of the game.
	 * @param model The model of the game.
	 */
	public void runAction(Controller controller,Board model){
		boolean exist=false;
		List<Player> players=model.getPlayers();
		for(Player player: players)
			if(name.equals(player.getUser()))
				exist=true;
		if(!exist){
			Player player=new Player(name, model.getNobilityTrack());
			controller.putSocketPlayer(super.getPlayer(), player);
			this.notifyObserver(new PlayerChange(player));
		}
		else
			this.notifyObserver(new InfoChange("The player already exists."));
	}

}
