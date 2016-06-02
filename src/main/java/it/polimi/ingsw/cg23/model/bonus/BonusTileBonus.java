package it.polimi.ingsw.cg23.model.bonus;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.view.Print;

/**
 * the class of the bonus that allows to run the bonus from a tile the player is in possession. It contains 
 * the tile the player wants to run the bonus from, the name and the cliinterface
 *  
 * @author Vincenzo
 *
 */
public class BonusTileBonus extends Observable<Change> implements Bonus {
	
	private static final long serialVersionUID = -5750535311281465339L;
	private final List<BusinessPermitTile> businessPermitTiles;	//the list of PerimtTile the player choose to have the bonuses from
	private final String name;
	
	private final Print cl;
	
	private static Logger logger;

	/**
	 * the constructor set the tile as a new arraylist, the cli as a new cliinterface and the name as 
	 * the name of the bonus
	 * @param number
	 */
	public BonusTileBonus(int number) {
		BonusTileBonus.logger = Logger.getLogger(BonusTileBonus.class);
		this.businessPermitTiles = new ArrayList<>();
		this.cl = new Print();
		this.name="TileBonus";
	}


	/**
	 * @return the businessPermitTile
	 */
	public List<BusinessPermitTile> getBusinessPermitTiles() {
		return businessPermitTiles;
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
		String c = (String)cl.writeReturnValue("Da che mazzo vuoi prendere il permesso?", null);
		int card=(int)cl.writeReturnValue("Inserisci valore", null);
		
		if(c=="used"){
			this.businessPermitTiles.add(player.getUsedBusinessPermit().get(card));
		}
		if(c=="available"){
			this.businessPermitTiles.add(player.getAvailableBusinessPermits().get(card));
		}
		else{
			logger.error("La stringa immessa è incorretta");
		}
		
		for(BusinessPermitTile bPT: this.businessPermitTiles){		//iterate the PermitsTile the player want to pick the bonuses from
			for(Bonus bonus: bPT.getBonusTile()){					//for each PermitTite iterate the bonuses in it
				bonus.giveBonus(player);							//for each bonus give it to the player
				
			}
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
		return "BonusTileBonus [businessPermitCard=" + businessPermitTiles + "]";
	}
	
	/**
	 * @return a new BonusTileBonus
	 */
	@Override
	public Bonus clone() {
		return new BonusTileBonus(0); 
	}	
	

}
