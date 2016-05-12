package it.polimi.ingsw.cg23.model.bonus;
 
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
 
public class BonusGetPermitTile implements Bonus {
	
	private final int region;				//wich region the player want to get the PermitTitle from
	private final int card; 				//wich PermitTitle the player choose from the showed ones 
	private final Board board;
	
	
 
	public BonusGetPermitTile(int region, int card, Board board) {
		this.region = region;
		this.card = card;
		this.board = board;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}



	/**
	 * @return the region
	 */
	public int getRegion() {
		return region;
	}
	

	/**
	 * @return the card
	 */
	public int getCard() {
		return card;
	}

	/**
	 * give to the player the PermitTitle chosen and replace it 
	 * on the field with the one on the top of the region deck
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		BusinessPermitTile bonusPermit=this.board.getRegions().get(region).getDeck().getShowedDeck().get(card);		//give to a variable the chosen card
		player.getAvailableBusinessPermits().add(bonusPermit);   					//add the chosen PermitTitle to the player collection
		this.board.getRegions().get(region).getDeck().changeShowedDeck(); 			//replace the PermitTitle chosen with the one in top of the deck
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusGetPermitTile [deck=" + region + ", card=" + card + "]";
	}
	
	
}