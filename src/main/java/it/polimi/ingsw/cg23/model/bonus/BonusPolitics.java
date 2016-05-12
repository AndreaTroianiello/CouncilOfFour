package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;


public class BonusPolitics implements Bonus {
	
	private final int cardNumber;
	private final Board board;
	
	
	public BonusPolitics(int cardNumber, Board board) {
		this.cardNumber = cardNumber;
		this.board = board;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusPolitics [cardNumber=" + cardNumber + "]";
	}

	
}
