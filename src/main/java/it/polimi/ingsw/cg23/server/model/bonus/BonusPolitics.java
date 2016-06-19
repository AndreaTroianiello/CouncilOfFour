package it.polimi.ingsw.cg23.server.model.bonus;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

/**
 * the class of the bonus that allows to draw a politic card from the deck. It contains the number of card to 
 * be drawn, the board and the name.
 * 
 * @author Vincenzo
 *
 */
public class BonusPolitics implements Bonus {
	
	private static final long serialVersionUID = -7063039174134594293L;
	private int cardNumber;
	private final Board board;
	private final String name;
	
	/**
	 * the constructor set the name as the name of the bonus, and the other variables as the parameters
	 * given to the method
	 * 
	 * @param cardNumber
	 * @param board
	 */
	public BonusPolitics(int cardNumber, Board board) {
		this.cardNumber = cardNumber;
		this.board = board;
		this.name="Politics";
	}

	/**
	 * @return the bonus name and the number(if exist)
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
	 * set the numebr of the card to be drawn
	 * 
	 * @param cardNumber the cardNumber to set
	 */
	@Override
	public void setNumber(int number) {
		this.cardNumber = number;
	}

	/**
	 * add cardNumber politic cards to the player's hand
	 * 
	 *  @param player
	 *  @param board
	 */
	@Override
	public void giveBonus(Player player) {
		for(int i=0; i<cardNumber; i++){
			PoliticCard card=this.board.getDeck().draw();
			if(card!=null)
				player.addPoliticCard(card);			//draw a card from the politic's deck
		}
	}
	
	@Override
	public int getNumber(){
		return cardNumber;
	}

	@Override
	public void setBoard(Board board) {
		//Not implemented.
	}
	/**
	 * @return the name of the class as string
	 */
	@Override
	public String toString() {
		return "BonusPolitics [cardNumber=" + cardNumber + "]";
	}

	/**
	 * @return a new BonusPolitics
	 */
	@Override
	public Bonus copy() {
		return new BonusPolitics(0, board);
	}	
	
}
