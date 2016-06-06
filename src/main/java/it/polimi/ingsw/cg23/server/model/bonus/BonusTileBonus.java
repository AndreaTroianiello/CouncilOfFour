package it.polimi.ingsw.cg23.server.model.bonus;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.view.Print;

/**
 * the class of the bonus that allows to run the bonus from a tile the player is in possession. It contains 
 * the tile the player wants to run the bonus from, the name and the cliinterface
 *  
 * @author Vincenzo
 *
 */
public class BonusTileBonus extends Observable<Change> implements Bonus {
	
	private static final long serialVersionUID = -5750535311281465339L;
	private final BusinessPermitTile businessPermitTile;	//the PerimtTile the player choose to have the bonuses from
	private final String name;
	
	
	private static Logger logger;

	/**
	 * the constructor set the tile as a new arraylist, the cli as a new cliinterface and the name as 
	 * the name of the bonus
	 * @param number
	 */
	public BonusTileBonus(int number, BusinessPermitTile businessPermitTile) {
		BonusTileBonus.logger = Logger.getLogger(BonusTileBonus.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.businessPermitTile = businessPermitTile;
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
		
		for(Bonus bonus: this.businessPermitTile.getBonusTile()){					//for each PermitTite iterate the bonuses in it
			bonus.giveBonus(player);							//for each bonus give it to the player
		}
	}
	
	@Override
	public void setNumber(int number){}
	
	@Override
	public void setParameters(){
		
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
	public Bonus clone() {
		return new BonusTileBonus(0, this.businessPermitTile); 
	}	
	

}
