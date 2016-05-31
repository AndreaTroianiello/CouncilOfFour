package it.polimi.ingsw.cg23.model.bonus;
 
import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.view.CliInterface;
 
/**
 * the class of the bonus that allows to get a permit tile from a region. It contains the board, the name and the CliInterface
 * 
 * @author Vincenzo
 *
 */
public class BonusGetPermitTile extends Observable<Change> implements Bonus {
	
	private static final long serialVersionUID = 5218205311068329970L;
	private final Board board;
	private final String name;
	private final CliInterface cl;
	
 
	/**
	 * the constructor set the board as the parameter given to the method, cl as a new cliinterface 
	 * and the name as the name of the bonus
	 * @param i
	 * @param a
	 * @param board
	 */
	public BonusGetPermitTile(int a, Board board) {
		
		this.board = board;
		this.name="GetPermitTile";
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


	@Override
	public void setNumber(int number){}

	/**
	 * give to the player the PermitTitle chosen and replace it 
	 * on the field with the one on the top of the region deck
	 * 
	 * @param player
	 */
	@Override
	public void giveBonus(Player player) {
		int region =Integer.parseInt(cl.writeReturnValue("Inserisci valore della regione", null).toString());
		int card = Integer.parseInt(cl.writeReturnValue("Inserisci valore della carta scelta", null).toString());
		
		BusinessPermitTile bonusPermit=this.board.getRegions().get(region).getDeck().getShowedDeck().get(card);		//give to a variable the chosen card
		player.getAvailableBusinessPermits().add(bonusPermit);   					//add the chosen PermitTitle to the player collection
		this.board.getRegions().get(region).getDeck().changeShowedDeck(); 			//replace the PermitTitle chosen with the one in top of the deck
	}

	@Override
	public void setParameters(){
		
	}

	/**
	 * @return the name of the class as string 
	 */
	@Override
	public String toString() {
		return "BonusGetPermitTile";
	}

	/**
	 * @return a new BonusGetPermitTile
	 */
	@Override
	public Bonus clone() {
		return new BonusGetPermitTile(0, board); 
	}	
	
}