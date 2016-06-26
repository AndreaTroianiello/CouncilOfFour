package it.polimi.ingsw.cg23.server.model.exception;

/**
 * xml exception class
 */
public class XmlException extends Exception{
	
	private static final long serialVersionUID = -8639907738286897399L;

	/**
	 * constructor
	 * @param e the exception
	 */

	public XmlException(Exception e){
		super(e);
	}
}
