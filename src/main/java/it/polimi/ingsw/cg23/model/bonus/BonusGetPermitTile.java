package it.polimi.ingsw.cg23.model.bonus;
 
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.RegionDeck;
 
public class BonusGetPermitTile implements Bonus {
	
	private final RegionDeck deck;			//wich region the player want to get the PermitTitle from
	private final int card; 				//wich PermitTitle the player choose from the showed ones 
	
	
 
	public BonusGetPermitTile(RegionDeck deck, int card) {
		this.deck = deck;
		this.card = card;
	}

	

	/**
	 * @return the deck
	 */
	public RegionDeck getDeck() {
		return deck;
	}
	

	/**
	 * @return the card
	 */
	public int getCard() {
		return card;
	}

	/**
	 * give to the player the PermitTitle chosen and replace it 
	 * on the field with the one on the top of the region deck
	 */
	@Override
	public void giveBonus(Player player) {
		BusinessPermitTile bonusPermit=this.deck.getShowedDeck().get(card);
		player.getAvailableBusinessPermits().add(bonusPermit);   //add the choosen PermitTitle to the player collection
		this.deck.changeShowedDeck();   						 //replace the PermitTitle chosen with the one in top of the deck
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusGetPermitTile [deck=" + deck + ", card=" + card + "]";
	}
	
	
}