package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.PoliticCard;

public class BonusPolitics implements Bonus {
	
	private final PoliticCard card;

	public BonusPolitics(PoliticCard card) {
		this.card = card;
	}

	/**
	 * @return the card
	 */
	public PoliticCard getCard() {
		return card;
	}


	//add a politic card to the player's hand
	@Override
	public void giveBonus(Player player) {
		
		player.addPoliticCard(card);

	}

}
