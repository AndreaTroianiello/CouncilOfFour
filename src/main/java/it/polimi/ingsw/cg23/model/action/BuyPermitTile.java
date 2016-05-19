package it.polimi.ingsw.cg23.model.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Council;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BuyPermitTile implements Action {
	
	private final List<PoliticCard> cards;
	private final int region;
	private final int chosenTile;									//wich tile the player chose from the showed ones
	private final boolean main;
	
	public BuyPermitTile(List<PoliticCard> cards, int region, int choosenTile) {
		this.cards = cards;
		this.region = region;
		this.chosenTile = choosenTile;
		this.main = true;
	}

	
	/**
	 * @return the chosenTile
	 */
	public int getChosenTile() {
		return chosenTile;
	}




	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
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
	public int getRegion() {
		return region;
	}

	/**
	 * control how many match are there between cards' colors and councillors' colors,
	 * if there is one or more the player take the card he chooses from the region he chooses
	 * and the relative amount of money is taken from him
	 * then it show another PermitTile form the hidden deck
	 * @param player
	 * @param board
	 */
	@Override
	public void runAction(Player player, Board board) {
		Council council = board.getRegions().get(this.region).getCouncil();
		int match = howManyMatch(council, player, board)[0];
		int jolly = howManyMatch(council, player, board)[1];
		int moneyPaid = payCoins(match, player);
		int coins = player.getRichness().getCoins();
		
		if(moneyPaid != -1){
		try {
			coins = coins - jolly;
			player.getRichness().setCoins(coins);
			player.addAvailableBusinessPermit(board.getRegions().get(this.region).getDeck().getShowedDeck().remove(chosenTile));
			board.getRegions().get(region).getDeck().changeShowedDeck();
		} catch (NegativeNumberException e) {
			try {
				player.getRichness().setCoins(coins+moneyPaid);
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
		List<PoliticCard> discardedCards= new ArrayList<>();
		
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
		
		board.getDeck().discardCars(discardedCards);
		
		result[0] = cardNumber;
		result[1] = jollyNumber;
		
		return result;
	}

	
	/**
	 * take the relative amount of money based on the number of match
	 * @param cardNumber
	 * @param player
	 */
	public int payCoins(int cardNumber, Player player){
		int coins = player.getRichness().getCoins();
		switch(cardNumber){
		case 1: 
			if(tryPayment(player, coins, 10)!= -1)
				return 10;
			return -1;			
		case 2:
			if(tryPayment(player, coins, 7)!=-1)
				return 7;
			return -1;
		case 3:
			if(tryPayment(player, coins, 4)!=-1)
				return 4;
			return -1;
		case 4: 
			return 0;
			
		default: System.out.println("Your cards don't match any councillor");
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
			System.out.println("The player doesn't have enough money!");
			return -1;
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BuyPermitTile [cards=" + cards + ", region=" + region + ", chosenTile=" + chosenTile + "]";
	}
	
	
}
