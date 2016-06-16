package it.polimi.ingsw.cg23.server.controller.action;

import java.util.Set;

import it.polimi.ingsw.cg23.server.controller.change.ChatChange;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.view.View;

public class SendMessage extends Action {

	private static final long serialVersionUID = 3097121314859196861L;
	private String message;
	private Player player;
	public SendMessage(String message,Player player) throws NullPointerException{
		this.message=message;
		this.player=player;
	}

	/**
	 * Runs the action. If the name is already used doesn't create the player.
	 * @param views All views of the application.
	 */
	public void runAction(Set<View> views){
		ChatChange change=new ChatChange(message, player.getUser());
		for(View view: views)
			if(view!=getPlayer())
				view.update(change);
	}

}