package it.polimi.ingsw.cg23.model.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Council;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BuildEmporiumKing extends PrimaryAction implements Action {
	
	private final List<PoliticCard> cards;
	private final City destination;
		
	
	public BuildEmporiumKing(List<PoliticCard> cards, City destination) {
		this.cards = cards;
		this.destination = destination;
	}
	
	
	/**
	 * @return the cards
	 */
	public List<PoliticCard> getCards() {
		return cards;
	}



	/**
	 * @return the city
	 */
	public City getDestination() {
		return destination;
	}


	/**
	 * control how many cards match with the councillors, take the money from the player,
	 * control if the path given is correct and build the emporium
	 */
	@Override
	public void runAction(Player player, Board board) {
		int match = howManyMatch(board.getKing().getCouncil());			//control how many match between cards and councillors there are
		int moneyPaid = payCoins(match, player);						//make the player pay the relative amount of money
		int steps = (int) board.getKing().getCity().minimumDistance(destination, new ArrayList<City>());					//set steps as the length of the list, and cast it to int
		int coin = player.getCoins();									//set coin as the current money of the player
		try {
			coin = coin - steps*2;									
			player.setCoins(coin);										//take from the player two coin for each steps the king moved
		} catch (NegativeNumberException e) {
			System.out.println("The player doesn't have enough money");
			try {
				player.setCoins(coin+moneyPaid);						//if the player doesn't have enough money for the steps' payment, give him back the money previously paid 
			} catch (NegativeNumberException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		if(player.getAvailableEmporium() != null){					//if the player has available emporiums, build one in the city
			try {
				this.destination.buildEmporium(player.getAvailableEmporium());
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have available emporiums");
				int currentCoin = player.getCoins();
				try {
					player.setCoins(currentCoin+steps*2+moneyPaid);	//if the player doesn't have available emporiums, give back the money previously paid
				} catch (NegativeNumberException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();					
			}
			
		}
		
		else{															//if the path isn't correct, give back the player the money previously paid
			try {
				player.setCoins(player.getCoins()+moneyPaid);
			} catch (NegativeNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	

	
	/**
	 * take the relative amount of money based on the number of match
	 * @param cardNumber
	 * @param chosenTile
	 * @param player
	 * @param board
	 */
	public int payCoins(int cardNumber, Player player){
		int coin = player.getCoins();
		switch(cardNumber){
		case 1: 
			try {
				coin = coin -10;
				player.setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money");
				e.printStackTrace();
			}
			return 10;
		case 2:
			try {
				coin = coin - 7;
				player.setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money");
				e.printStackTrace();
			}
			return 7;
		case 3:
			try {
				coin = coin - 4;
				player.setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money");
				e.printStackTrace();
			}
			return 4;
		case 4: 
			return 0;
			
		default: System.out.println("Your cards don't match any councillor");
			return 0;
			}
		 
		}
	
	/**
	 * the method confront the color of the cards with the color of the councillors in the council
	 * and return how many match are there 
	 * @param council
	 * @return cardNumber
	 */
	public int howManyMatch(Council council){
		
		int councilLenght = council.getCouncillors().size();		//assign to councilLenght the value of the size of the king's council
		int cardNumber = 0;											//the number of cards that the color match with the councillor's colors
		boolean match = false;										//control if there is a match
		
		for(int i=0; i<councilLenght; i++){							//iterate the council
			for(PoliticCard card : this.cards){						//iterate the cards
				if(card.getColor().toString().equals(council.getCouncillors().get(i).getColor().toString())){		
					match = true;									//if there is a match set the boolean true 
					this.cards.remove(card);						//and remove the card from the list
				}   
				if(match){											//if the match is true 
					cardNumber = cardNumber + 1;					//update the counter
					break;											//and break the second for cycle
				}
			}
		}
		
		return cardNumber;
	}


	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BuildEmporiumKing [cards=" + cards + ", destination=" + destination + "]";
	}

	
}
