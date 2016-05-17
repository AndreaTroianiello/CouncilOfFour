package it.polimi.ingsw.cg23.model.bonus;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.view.CliInterface;

public class BonusTileBonus implements Bonus, Cloneable {
	
	private final List<BusinessPermitTile> businessPermitTiles;	//the list of PerimtTile the player choose to have the bonuses from
	private final String name="TileBonus";
	
	private final CliInterface cl;

	public BonusTileBonus(int number) {
		this.businessPermitTiles = new ArrayList<>();
		this.cl = new CliInterface();
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
			System.out.println("La stringa immessa è incorretta");
		}
		
		for(BusinessPermitTile bPT: this.businessPermitTiles){		//iterate the PermitsTile the player want to pick the bonuses from
			for(Bonus bonus: bPT.getBonusTile()){					//for each PermitTite iterate the bonuses in it
				bonus.giveBonus(player);							//for each bonus give it to the player
				
			}
		}

	}
	
	public void setNumber(int number){}
	
	public void setParameters(){
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusTileBonus [businessPermitCard=" + businessPermitTiles + "]";
	}
	
	

}
