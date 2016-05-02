package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;

public class BonusAdditionalAction implements Bonus {

	@Override
	public void giveBonus(Player player) {
		if(!player.isAdditionalAction())
			player.switchAdditionalAction();

	}
}
