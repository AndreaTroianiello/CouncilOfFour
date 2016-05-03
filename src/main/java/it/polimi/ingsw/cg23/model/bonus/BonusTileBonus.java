package it.polimi.ingsw.cg23.model.bonus;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;

public class BonusTileBonus implements Bonus {
	
	private final List<BusinessPermitTile> businessPermitTiles;	//the list of PerimtTile the player choose to have the bonuses from
	

	public BonusTileBonus(BusinessPermitTile businessPermitTiles) {
		this.businessPermitTiles = new ArrayList<>();
	}


	/**
	 * @return the businessPermitTile
	 */
	public List<BusinessPermitTile> getBusinessPermitTiles() {
		return businessPermitTiles;
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

}
