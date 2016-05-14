package it.polimi.ingsw.cg23.model.bonus;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;

public class BonusTileBonus implements Bonus {
	
	private final List<BusinessPermitTile> businessPermitTiles;	//the list of PerimtTile the player choose to have the bonuses from
	private final String name="TileBonus";
	private final int number;

	public BonusTileBonus(int number) {
		this.businessPermitTiles = new ArrayList<>();
		this.number=number;
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
		return number+name;
	}

	/**
	 * for each businessPermitTile in the List give 
	 * the player the bonuses in it
	 */
	@Override
	public void giveBonus(Player player) {
		for(BusinessPermitTile bPT: this.businessPermitTiles){		//iterate the PermitsTile the player want to pick the bonuses from
			for(Bonus bonus: bPT.getBonusTile()){					//for each PermitTite iterate the bonuses in it
				bonus.giveBonus(player);							//for each bonus give it to the player
				
			}
		}

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusTileBonus [businessPermitTiles=" + businessPermitTiles + "]";
	}
	
	

}
