package it.polimi.ingsw.cg23.server.model.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.BusinessPermitTileChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.utilities.Payer;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to buy a permit tile from the chosen
 * region if your politic cards match some of the councillors of that region. It
 * contains a boolean that specifies if it is a main action, which region and
 * which tile the player chooses, a list of the politic cards of the player, and
 * another list where the used cards go
 *
 * @author Vincenzo
 */
public class BuyPermitTile extends GameAction implements StandardAction {

	private static final long serialVersionUID = 2974062894189424346L;
	private final List<PoliticCard> cards;
	private final Region region;
	private final BusinessPermitTile chosenTile; // wich tile the player chose
													// from the showed ones
	private List<PoliticCard> discardedCards = new ArrayList<>();
	private final ControlAction controlAction;
	private List<PoliticCard> realHand;
	private final Payer payer;

	/**
	 * the constructor set the variable of the class: main i set to true, cards,
	 * region, and chosenTile are set as the parameter given to the method
	 * 
	 * @param cards
	 *            the cards that will be matched with the council
	 * @param region
	 *            where to pick the tile
	 * @param choosenTile
	 *            which tile the player chooses
	 * @throws NullPointerException
	 *             if the parameters are null.
	 */
	public BuyPermitTile(List<PoliticCard> cards, Region region, BusinessPermitTile choosenTile) {
		super(true);
		if (cards != null && region != null && choosenTile != null) {
			this.cards = cards;
			this.region = region;
			this.chosenTile = choosenTile;
		} else
			throw new NullPointerException();
		this.controlAction = new ControlAction();
		this.payer = new Payer();
	}

	/**
	 * @return the chosenTile
	 */
	public BusinessPermitTile getChosenTile() {
		return chosenTile;
	}

	/**
	 * @return the card
	 */
	public List<PoliticCard> getCards() {
		return cards;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * control how many match are there between cards' colors and councillors'
	 * colors, if there is one or more the player take the card he chooses from
	 * the region he chooses and the relative amount of money is taken from him
	 * then it show another PermitTile form the hidden deck
	 * 
	 * @param player
	 *            who runs the action
	 * @param board
	 *            the model of the game
	 * 
	 * @return true if the action is successful, false otherwise
	 */
	@Override
	public boolean runAction(Player player, Board board) {
		this.realHand = this.controlAction.controlPoliticCards(this.cards, player);
		Region realRegion = this.controlAction.controlRegion(this.region, board);
		BusinessPermitTile realTile = this.controlAction.controlBusinessPermitRegion(chosenTile, realRegion);
		if (this.realHand != null && realRegion != null && realTile != null) {
			Council council = realRegion.getCouncil();
			int jolly = howManyJolly(board);
			int match = jolly + howManyMatch(board, council);
			player.getHand().removeAll(discardedCards);
			int moneyPaid = this.payer.payCoins(this.cards, this.discardedCards, match, player);
			int coins = player.getRichness().getCoins();

			if (moneyPaid != -1) {
				try {
					coins = coins - jolly;
					player.getRichness().setCoins(coins);
					player.addAvailableBusinessPermit(realTile);
					realRegion.getDeck().getShowedDeck().remove(realTile);
					for (Bonus b : realTile.getBonusTile()) {
						b.giveBonus(player);
					}
					realRegion.getDeck().changeShowedDeck();
				} catch (NegativeNumberException e) {
					try {
						player.getRichness().setCoins(coins + moneyPaid);
						this.realHand.addAll(discardedCards);
					} catch (NegativeNumberException e1) {
						getLogger().error(e1);
					}
					getLogger().error(e);
					return false;
				}
			} else
				return false;

			List<BusinessPermitTile> changedDeck = realRegion.getDeck().getShowedDeck();
			board.notifyObserver(new BusinessPermitTileChange(changedDeck.get(changedDeck.size() - 1)));
			board.notifyObserver(new BoardChange(board));
			return true;
		}
		return false;
	}

	/**
	 * control how many match between are there between the councillors and the
	 * politic cards
	 * 
	 * @param board
	 *            the model of the game
	 * @param council
	 *            the council chosen
	 * @return the number of match between cards and councillors
	 */
	private int howManyMatch(Board board, Council council) {
		int match = 0;
		int councilLenght = council.getCouncillors().size();

		for (int i = 0; i < councilLenght; i++) { // iterate the council
			for (PoliticCard card : this.realHand) { // iterate the politic
														// cards
				if (card.getColor().toString().equals(council.getCouncillors().get(i).getColor().toString())) {
					match = match + 1; // if there is a match update the counter
					this.discardedCards.add(card); // add the card to the
													// discarded cards
					this.realHand.remove(card); // remove the card from the
												// politic cards
					break; // and break the cycle
				}
			}
		}
		this.realHand.removeAll(discardedCards);
		board.getDeck().discardCards(discardedCards);

		return match;
	}

	/**
	 * control how many jolly are there in the chosen politic cards
	 * 
	 * @param board
	 *            the model of the game
	 * @return the number of jollies in cards
	 */
	private int howManyJolly(Board board) {
		int jolly = 0;

		for (PoliticCard card : this.realHand) // iterate the politic cards
			if (card.isJolly()) { // if the card is a jolly
				jolly = jolly + 1; // update the counter
				this.discardedCards.add(card); // and add the card to the
												// discardedCards
			}
		this.realHand.removeAll(discardedCards); // remove all the jolly from
													// the politic cards
		board.getDeck().discardCards(discardedCards);

		return jolly;
	}

	/**
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "BuyPermitTile [cards=" + cards + ", region=" + region + ", chosenTile=" + chosenTile + "]";
	}

}
