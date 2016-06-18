package it.polimi.ingsw.cg23.server.model.bonus;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;

/**
 * the class of the bonus that allows to run the bonus from a tile the player is in possession. It contains 
 * the tile the player wants to run the bonus from, the name and the cliinterface
 *  
 * @author Vincenzo
 *
 */
public class BonusTileBonus extends Observable<Change> implements Bonus {
	
	private static final long serialVersionUID = -5750535311281465339L;
	private BusinessPermitTile businessPermitTile;	//the PerimtTile the player choose to have the bonuses from
	private final String name;
	private int parameters;
	
	
	private static Logger logger;

	/**
	 * the constructor set the tile as a new arraylist, the cli as a new cliinterface and the name as 
	 * the name of the bonus
	 * @param number
	 */
	public BonusTileBonus() {
		BonusTileBonus.logger = Logger.getLogger(BonusTileBonus.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.parameters=1;
		this.name="TileBonus";
	}


	/**
	 * @return the businessPermitTile
	 */
	public BusinessPermitTile getBusinessPermitTiles() {
		return businessPermitTile;
	}
	
	/**
	 * return the bonus name and the number(if exist)
	 */
	@Override
	public String getName(){
		return name;
	}

	/**
	 * for each businessPermitTile in the List give 
	 * the player the bonuses in it
	 */
	@Override
	public void giveBonus(Player player) {
		for(Bonus bonus: this.businessPermitTile.getBonusTile()){					//iterate the bonus in the tile and
			bonus.giveBonus(player);												//for each bonus give it to the player
		}
	}
	
	@Override
	public void setNumber(int number){
		//this is a method of the Bonus interfaced not used in this class
	}
	
	@Override
	public int getParameters(){
		return parameters;
	}


	/**
	 * @return the name of the class as string
	 */
	@Override
	public String toString() {
		return "BonusTileBonus [businessPermitCard=" + businessPermitTile + "]";
	}
	
	/**
	 * @return a new BonusTileBonus
	 */
	@Override
	public Bonus copy() {
		return new BonusTileBonus(); 
	}	
	

}
