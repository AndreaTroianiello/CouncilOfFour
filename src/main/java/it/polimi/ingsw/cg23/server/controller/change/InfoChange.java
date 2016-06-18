package it.polimi.ingsw.cg23.server.controller.change;


/**
 * The InfoChange is used to notify a information.
 * @author Andrea
 *
 */
public class InfoChange implements Change {
	
	private static final long serialVersionUID = -5693277103823286185L;
	private final String msg; 
	
	/**
	 * The constructor of the InfoChange
	 * @param error The message of info.
	 */
	public InfoChange(String info) {
		this.msg=info;
	}

	/**
	 * It generates a string formed by the info message.
	 * @return string
	 */
	@Override
	public String toString() {
		return "InfoChange [msg=" + msg + "]";
	}
}
