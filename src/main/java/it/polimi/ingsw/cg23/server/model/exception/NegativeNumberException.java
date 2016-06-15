package it.polimi.ingsw.cg23.server.model.exception;

/**
 * Exception used to indicate that a number can not be negative.
 * @author Andrea
 *
 */
public class NegativeNumberException extends Exception {
	
	private static final long serialVersionUID = 5816150923943946118L;

	/**
	 * The constructor of the exception.
	 * @param message The error message.
	 */
	public NegativeNumberException(String message){
		super(message);
	}
}
