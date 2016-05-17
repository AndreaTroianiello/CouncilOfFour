package it.polimi.ingsw.cg23.model.bonus;
 
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.view.CliInterface;
 
public class BonusGetPermitTile implements Bonus {
	
	private final Board board;
	private final String name="GetPermitTile";
	
	private final CliInterface cl;
	
 
	public BonusGetPermitTile(int i, int a, Board board) {
		
		this.board = board;
		
		this.cl = new CliInterface();
	}
	
	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return name;
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}


	
	public void setNumber(int number){}

	/**
	 * give to the player the PermitTitle chosen and replace it 
	 * on the field with the one on the top of the region deck
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		int region =(int)cl.writeReturnValue("Inserisci valore della regione", null);
		int card = (int)cl.writeReturnValue("Inserisci valore della carta scelta", null);
		
		BusinessPermitTile bonusPermit=this.board.getRegions().get(region).getDeck().getShowedDeck().get(card);		//give to a variable the chosen card
		player.getAvailableBusinessPermits().add(bonusPermit);   					//add the chosen PermitTitle to the player collection
		this.board.getRegions().get(region).getDeck().changeShowedDeck(); 			//replace the PermitTitle chosen with the one in top of the deck
	}


	public void setParameters(){
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusGetPermitTile [board=" + board + ", name=" + name + "]";
	}

	public Bonus clone() {
		return new BonusGetPermitTile(0, 0, null); 
	}	
	
}