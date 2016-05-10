package it.polimi.ingsw.cg23.model.bonus;

import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Deck;


public class BonusPolitics implements Bonus {
	
	private final Deck deck;

	public BonusPolitics(Deck deck) {
		this.deck = deck;
	}

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}


	//add a politic card to the player's hand
	@Override
	public void giveBonus(Player player) {
		player.addPoliticCard(deck.draw());

	}

}
