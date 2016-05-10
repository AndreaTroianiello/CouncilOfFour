package it.polimi.ingsw.cg23.model.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Council;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BuyPermitTile extends PrimaryAction implements Action {
	
	private final List<PoliticCard> cards;
	private final int region;
	private final int chosenTile;									//wich tile the player chose from the showed ones
	
	
	public BuyPermitTile(List<PoliticCard> cards, int region, int choosenTile) {
		this.cards = new ArrayList<>();
		this.region = region;
		this.chosenTile = choosenTile;
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
	 * @param player
	 * @param board
	 */
	@Override
	public void runAction(Player player, Board board) {
		Council council = board.getRegions().get(this.region).getCouncil();
		int cardNumber = howManyMatch(council, this.cards);
		pickBusinessPermit(cardNumber, this.chosenTile, player, board);
		
	}
	
	/**
	 * the method confront the color of the cards with the color of the councillors in the council
	 * and return how many match are there 
	 * @param council
	 * @param cards
	 * @return cardNumber
	 */
	public int howManyMatch(Council council, List<PoliticCard> cards){
		
		int councilLenght = council.getCouncillors().size();		//assign to councilLenght the value of the size of the choosen region's council
		int cardNumber = 0;											//the number of cards that the color match with the councillor's colors
		boolean match = false;										//control if there is a match
		
		for(int i=0; i<councilLenght; i++){							//iterate the council
			for(PoliticCard card : this.cards){						//iterate the cards
				if(card.getColor().toString().equals(council.getCouncillors().get(i))){		
					match = true;									//if there is a match set the boolean true 
					this.cards.remove(card);						//and remove the card from the list
				}   
				if(match == true){									//if the match is true 
					cardNumber = cardNumber + 1;					//update the counter
					break;											//and break the second for cycle
				}
			}
		}
		
		return cardNumber;
	}

	
	/**
	 * pick the chosen permit tile from the chosen region and take 
	 * the relative amount of money based on the number of match
	 * @param cardNumber
	 * @param chosenTile
	 * @param player
	 * @param board
	 */
	public void pickBusinessPermit(int cardNumber, int chosenTile, Player player, Board board){
		int coin = player.getCoins();
		switch(cardNumber){
		case 1: player.addAvailableBusinessPermit(board.getRegions().get(this.region).getDeck().getShowedDeck().get(chosenTile));
			try {
				coin = coin -10;
				player.setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money");
				e.printStackTrace();
			}
			break;
		case 2: player.addAvailableBusinessPermit(board.getRegions().get(this.region).getDeck().getShowedDeck().get(chosenTile));
			try {
				coin = coin - 7;
				player.setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money");
				e.printStackTrace();
			}
			break;
		case 3: player.addAvailableBusinessPermit(board.getRegions().get(this.region).getDeck().getShowedDeck().get(chosenTile));
			try {
				coin = coin - 4;
				player.setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money");
				e.printStackTrace();
			}
			break;
		case 4: player.addAvailableBusinessPermit(board.getRegions().get(this.region).getDeck().getShowedDeck().get(chosenTile));
			break;
			
		default: System.out.println("Your cards don't match any councillor");
			break;
			}
		 
		}
}
