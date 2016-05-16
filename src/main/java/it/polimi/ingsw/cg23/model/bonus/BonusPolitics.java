package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;


public class BonusPolitics implements Bonus {
	
	private int cardNumber;
	private final Board board;
	private final String name="Politics";
	
	
	public BonusPolitics(int cardNumber, Board board) {
		this.cardNumber = cardNumber;
		this.board = board;
	}

	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return cardNumber+name;
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}


	/**
	 * @return the cardNumber
	 */
	public int getCardNumber() {
		return cardNumber;
	}
	

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setNumber(int number) {
		this.cardNumber = number;
	}

	/**
	 * add cardNumber politic's cards to the player's hand
	 * 
	 *  @param player
	 *  @param board
	 */
	@Override
	public void giveBonus(Player player) {
		for(int i=0; i<cardNumber; i++)
			player.addPoliticCard(this.board.getDeck().draw());			//draw a card from the politic's deck

	}
	
	
	public void setParameters(){
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusPolitics [cardNumber=" + cardNumber + "]";
	}

	
}
