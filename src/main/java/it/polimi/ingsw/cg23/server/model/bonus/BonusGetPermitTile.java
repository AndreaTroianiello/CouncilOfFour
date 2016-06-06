package it.polimi.ingsw.cg23.server.model.bonus;
 
import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.view.Print;
 
/**
 * the class of the bonus that allows to get a permit tile from a region. It contains the board, the name and the CliInterface
 * 
 * @author Vincenzo
 *
 */
public class BonusGetPermitTile extends Observable<Change> implements Bonus {
	
	private static final long serialVersionUID = 5218205311068329970L;
	private final Board board;
	private final Region region;
	private final String name;
	private final BusinessPermitTile businessPermit;

	
 
	/**
	 * the constructor set the board as the parameter given to the method, cl as a new cliinterface 
	 * and the name as the name of the bonus
	 * @param i
	 * @param a
	 * @param board
	 */
	public BonusGetPermitTile(int a, Board board, BusinessPermitTile businessPermit, Region region) {
		this.businessPermit = businessPermit;
		this.region = region;
		this.board = board;
		this.name="GetPermitTile";
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


	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @return the businessPermit
	 */
	public BusinessPermitTile getBusinessPermit() {
		return businessPermit;
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
		player.getAvailableBusinessPermits().add(businessPermit);   					//add the chosen PermitTitle to the player collection
		this.region.getDeck().changeShowedDeck(); 										//replace the PermitTitle chosen with the one in top of the deck
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
		return new BonusGetPermitTile(0, board, businessPermit, region); 
	}	
	
}