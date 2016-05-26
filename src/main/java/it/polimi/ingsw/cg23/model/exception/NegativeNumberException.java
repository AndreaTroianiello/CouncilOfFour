package it.polimi.ingsw.cg23.model.exception;

public class NegativeNumberException extends Exception {
	
	private static final long serialVersionUID = 5816150923943946118L;

	public NegativeNumberException(String message){
		super(message);
	}
}
