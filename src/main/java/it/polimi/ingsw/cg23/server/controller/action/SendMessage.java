package it.polimi.ingsw.cg23.server.controller.action;

import java.util.Set;

import it.polimi.ingsw.cg23.server.controller.change.ChatChange;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.view.View;

/**
 * This action sends the message at all users.
 * @author Andrea
 *
 */
public class SendMessage extends Action {

	private static final long serialVersionUID = 3097121314859196861L;
	private String message;
	private Player player;
	
	/**
	 * The constructor of SendMessage.
	 * @param message The message to send.
	 * @param player The owner of the action.
	 * @throws NullPointerException if a parameter is null.
	 */
	public SendMessage(String message,Player player){
		if(message!=null&&player!=null){
			this.message=message;
			this.player=player;
		}else
			throw new NullPointerException();
	}

	/**
	 * Runs the action. If the name is already used doesn't create the player.
	 * @param views All views of the application.
	 */
	public void runAction(Set<View> views){
		ChatChange change=new ChatChange(message, player.getUser());
		for(View view: views)
			view.update(change);
	}

}
