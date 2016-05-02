package it.polimi.ingsw.cg23.model.exception;

public class NegativeNumberException extends Exception {
	
	private static final long serialVersionUID =-5605493852164516896L;
	
	public NegativeNumberException(String message){
		super(message);
	}
}
