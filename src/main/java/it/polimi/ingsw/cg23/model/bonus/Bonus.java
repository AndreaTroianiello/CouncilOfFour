package it.polimi.ingsw.cg23.model.bonus;


import it.polimi.ingsw.cg23.model.Player;

public interface Bonus {
	
	public void giveBonus(Player player);
	public String getName();
	public void setParameters();

}
