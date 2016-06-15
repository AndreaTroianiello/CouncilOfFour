package it.polimi.ingsw.cg23.server.controller.change;


/**
 * The ChatChange serves to notify a new message.
 * @author Andrea
 *
 */
public class ChatChange implements Change {

	private static final long serialVersionUID = 7618046359903026433L;
	private final String message;
	private final String player;
	
	/**
	 * The constructor of the ChatChange.
	 * @param message the new message.
	 * @param player the message's owner.
	 */
	public ChatChange(String message,String player){
		this.message=message;
		this.player=player;
	}

	/**
	 * It generates a string formed by the username and the message.
	 * @return string
	 */
	@Override
	public String toString() {
		return player+" : "+message;
	}

}



