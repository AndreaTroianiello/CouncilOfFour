package it.polimi.ingsw.cg23.model.action;

import java.util.ArrayList;
import java.util.List;

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
public class BuyPermitTile extends GameAction {
	
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
		int match = howManyMatch(council, player, board)[0];
		int jolly = howManyMatch(council, player, board)[1];
		int moneyPaid = payCoins(match, player);
		int coins = player.getRichness().getCoins();
		
		if(moneyPaid != -1){
		try {
			coins = coins - jolly;
			player.getRichness().setCoins(coins);
			player.addAvailableBusinessPermit(chosenTile);
			board.getDeck().discardCars(discardedCards);
			this.region.getDeck().changeShowedDeck();
		} catch (NegativeNumberException e) {
			try {
				player.getRichness().setCoins(coins+moneyPaid);
				this.cards.addAll(discardedCards);
			} catch (NegativeNumberException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		}
		
		
		
		
	}
	
	/**
	 * the method confront the color of the cards with the color of the councillors in the council
	 * and return how many match are there 
	 * 
	 * @param council
	 * @return cardNumber
	 * @throws NegativeNumberException 
	 */
	public int[] howManyMatch(Council council, Player player, Board board){
		
		int councilLenght = council.getCouncillors().size();		//assign to councilLenght the value of the size of the chosen region's council
		int jollyNumber = 0;										//the number of the jolly in the list
		int cardNumber = 0;											//the number of cards that the color match with the councillor's colors
		boolean match = false;										//control if there is a match
		int[] result;												//an array with the card and the jolly number
		result = new int[2];
		
		
		for(PoliticCard card: this.cards){							//iterate the card
			if(card.isJolly()){										//and control if there are jolly
				cardNumber = cardNumber + 1;						//update the card counter
				jollyNumber = jollyNumber + 1;						//update the jolly counter
				discardedCards.add(card);
			}
			
		}
		cards.removeAll(discardedCards);
		for(int i=0; i<councilLenght; i++){							//iterate the council
			for(PoliticCard card : this.cards){						//iterate the cards
				if(card.getColor().toString().equals(council.getCouncillors().get(i).getColor().toString())){		
					match = true;									//if there is a match set the boolean true 
					this.cards.remove(card);						//and remove the card from the list
					discardedCards.add(card);
				}   
				if(match){											//if the match is true 
					cardNumber = cardNumber + 1;					//update the counter
					match = false;
					break;											//and break the second for cycle
				}
			}
		}
		cards.removeAll(discardedCards);
		board.getDeck().discardCars(discardedCards);
		
		result[0] = cardNumber;
		result[1] = jollyNumber;
		
		return result;
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
			System.out.println("Your cards don't match any councillor");
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
	 * @param coin
	 * @param payment
	 */
	public int tryPayment(Player player,int coin, int payment){
		try {
			coin = coin - payment;
			player.getRichness().setCoins(coin);
			return 0;
		} catch (NegativeNumberException e) {
			this.cards.addAll(discardedCards);
			System.out.println("The player doesn't have enough money!");
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
