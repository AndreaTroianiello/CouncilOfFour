package it.polimi.ingsw.cg23.server.controller.change;

public class ErrorChange implements Change {
	
	private static final long serialVersionUID = -5693277103823286185L;
	private final String msg; 
	
	/**
	 * The constructor of the ErrorChange
	 * @param error The message of error.
	 */
	public ErrorChange(String error) {
		this.msg=error;
	}

	/**
	 * It generates a string formed by the error message.
	 * @return string
	 */
	@Override
	public String toString() {
		return "ErrorChange [msg=" + msg + "]";
	}
}
