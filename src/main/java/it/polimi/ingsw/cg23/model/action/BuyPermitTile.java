package it.polimi.ingsw.cg23.model.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.controller.change.BoardChange;
import it.polimi.ingsw.cg23.controller.change.BusinessPermitTileChange;
import it.polimi.ingsw.cg23.controller.change.CouncilChange;
import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.Region;
import it.polimi.ingsw.cg23.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.model.components.Council;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to buy a permit tile from the chosen region if your politic cards match
 * some of the councillors of that region. It contains a boolean that specifies if it is a main action, 
 * which region and which tile the player chooses, a list of the politic cards of the player, and 
 * another list where the used cards go
 *
 * @author Vincenzo
 */
public class BuyPermitTile extends GameAction implements StandardAction{
	
	private static final long serialVersionUID = 2974062894189424346L;
	private final List<PoliticCard> cards;
	private final Region region;
	private final BusinessPermitTile chosenTile;									//wich tile the player chose from the showed ones
	private List<PoliticCard> discardedCards= new ArrayList<>();
	
	
	
	/** 
	 * the constructor set the variable of the class: main i set to true, cards, region, and chosenTile 
	 * are set as the parameter given to the method
	 * 
	 * @param cards
	 * @param region
	 * @param choosenTile
	 */
	public BuyPermitTile(List<PoliticCard> cards, Region region, BusinessPermitTile choosenTile) {
		super(true);
		this.cards = cards;
		this.region = region;
		this.chosenTile = choosenTile;
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
	 * control how many match are there between cards' colors and councillors' colors,
	 * if there is one or more the player take the card he chooses from the region he chooses
	 * and the relative amount of money is taken from him
	 * then it show another PermitTile form the hidden deck
	 * 
	 * @param player
	 * @param board
	 */
	@Override
	public void runAction(Player player, Board board) {
		Council council = this.region.getCouncil();
		int jolly = howManyJolly(board);
		int match = jolly + howManyMatch(board, council);
		int moneyPaid = payCoins(match, player);
		int coins = player.getRichness().getCoins();
		
		if(moneyPaid != -1){
			try {
				coins = coins - jolly;
				player.getRichness().setCoins(coins);
				player.addAvailableBusinessPermit(chosenTile);
				board.getDeck().discardCards(discardedCards);
				this.region.getDeck().changeShowedDeck();
			} catch (NegativeNumberException e) {
				try {
					player.getRichness().setCoins(coins+moneyPaid);
					this.cards.addAll(discardedCards);
				} catch (NegativeNumberException e1) {
					getLogger().error(e1);
				}
				getLogger().error(e);
			}
		}
		
		List<BusinessPermitTile> changedDeck = this.region.getDeck().getShowedDeck();
		this.notifyObserver(new BusinessPermitTileChange(changedDeck.get(changedDeck.size()-1)));
	}
	
	/**
	 * control how many match between are there between 
	 * the councillors and the politic cards
	 * 
	 * @param board
	 * @param council
	 * @return match
	 */
	public int howManyMatch(Board board, Council council){
		int match = 0;
		int councilLenght = council.getCouncillors().size();
		
		for(int i=0; i<councilLenght; i++){									//iterate the council
			for(PoliticCard card: cards){									//iterate the politic cards
				if(card.getColor().toString().equals(council.getCouncillors().get(i).getColor().toString())){
					match = match + 1;										//if there is a match update the counter
					this.discardedCards.add(card);							//add the card to the discarded cards
					this.cards.remove(card);								//remove the card from the politic cards
					break;													//and break the cycle
				}
			}
		}
		cards.removeAll(discardedCards);
		board.getDeck().discardCards(discardedCards);
		
		return match;
	}
	
	/**
	 * control how many jolly are there in the chosen politic cards
	 * 
	 * @param board
	 * @return jolly
	 */
	public int howManyJolly(Board board){
		int jolly = 0;
		
		for(PoliticCard card: cards)					//iterate the politic cards
			if(card.isJolly()){							//if the card is a jolly
				jolly = jolly + 1;						//update the counter
				this.discardedCards.add(card);			//and add the card to the discardedCards
			}
		this.cards.removeAll(discardedCards);			//remove all the jolly from the politic cards
		board.getDeck().discardCards(discardedCards);
		
		return jolly;
	}

	
	/**
	 * take the relative amount of money based on the number of match
	 * 
	 * @param cardNumber
	 * @param player
	 */
	public int payCoins(int match, Player player){
		int coin = player.getRichness().getCoins();
		int payment = (4-match)*3+1;
		if(match == 0){
			getLogger().error("Your cards don't match any councillor");
			return -1;
		}
		if(match == 4){
			return 0;
		}
		else{
			if(tryPayment(player, coin, payment)!=-1)
				return payment;
			return -1;
		}
	}
	
	
	/**
	 * try to make the payment, and catch the exception if the 
	 * player doesn't have enough money
	 * 
	 * @param player
	 * @param money
	 * @param payment
	 */
	public int tryPayment(Player player,int money, int payment){
		try {
			money = money - payment;
			player.getRichness().setCoins(money);
			return 0;
		} catch (NegativeNumberException e) {
			this.cards.addAll(discardedCards);
			getLogger().error("The player doesn't have enough money!", e);
			return -1;
		}
	}


	/**
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "BuyPermitTile [cards=" + cards + ", region=" + region + ", chosenTile=" + chosenTile + "]";
	}
	
	
}
