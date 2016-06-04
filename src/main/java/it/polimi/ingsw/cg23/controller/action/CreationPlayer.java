package it.polimi.ingsw.cg23.controller.action;

import it.polimi.ingsw.cg23.controller.Controller;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;

public class CreationPlayer extends Action {

	private static final long serialVersionUID = 3683134725976321975L;
	private final String name;
	
	public CreationPlayer(String name) {
		this.name=name;
	}
	
	public void runAction(Controller controller,Board model){
		Player player=new Player(name, 0, 0, model.getNobilityTrack());
		controller.putSocketPlayer(super.getPlayer(), player);
	}

}
