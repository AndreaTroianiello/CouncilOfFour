package it.polimi.ingsw.cg23.model.action;

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
		int cardNumber = howManyMatch(council);
		player.addAvailableBusinessPermit(board.getRegions().get(this.region).getDeck().getShowedDeck().get(chosenTile));
		board.getRegions().get(region).getDeck().changeShowedDeck();
		payCoins(cardNumber, player);
		
	}
	
	/**
	 * the method confront the color of the cards with the color of the councillors in the council
	 * and return how many match are there 
	 * @param council
	 * @return cardNumber
	 */
	public int howManyMatch(Council council){
		
		int councilLenght = council.getCouncillors().size();		//assign to councilLenght the value of the size of the choosen region's council
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

	
	/**
	 * take the relative amount of money based on the number of match
	 * @param cardNumber
	 * @param player
	 */
	public void payCoins(int cardNumber, Player player){
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
			break;
		case 2:
			try {
				coin = coin - 7;
				player.setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money");
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				coin = coin - 4;
				player.setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money");
				e.printStackTrace();
			}
			break;
		case 4: 
			break;
			
		default: System.out.println("Your cards don't match any councillor");
			break;
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
