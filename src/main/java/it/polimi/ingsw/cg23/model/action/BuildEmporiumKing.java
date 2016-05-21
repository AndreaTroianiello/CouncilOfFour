package it.polimi.ingsw.cg23.model.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.City;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.components.Council;
import it.polimi.ingsw.cg23.model.components.PoliticCard;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class BuildEmporiumKing implements Action {
	
	private final boolean main;
	private final List<PoliticCard> cards;
	private List<PoliticCard> discardedCards = new ArrayList<>();
	private final City destination;
	
	

	public BuildEmporiumKing(List<PoliticCard> cards, City destination) {
		this.main = true;
		this.cards = cards;
		this.destination = destination;
	}
	
	@Override
	public boolean isMain() {
		return main;
	}

	@Override
	public void runAction(Player player, Board board) {
		int jolly = howManyJolly(board);															//control how many jolly there are
		int match = jolly + howManyMatch(board, board.getKing().getCouncil());						//control how many match there are
		int payMatch = payCoins(match, player);														//pay the amount of coins relative to the match
		int steps = (int) board.getKing().getCity().minimumDistance(destination, new ArrayList<City>());		//control the distance between the king's city and the destination
		int coin = player.getRichness().getCoins();													//control the richness of the player			
		
		if(payMatch!=-1){			
			try {
				coin = coin - steps*2 - jolly;
				player.getRichness().setCoins(coin);
			} catch (NegativeNumberException e) {
				System.out.println("The player doesn't have enough money!");
				try {
					player.getRichness().setCoins(coin+payMatch);
					this.cards.addAll(discardedCards);
				} catch (NegativeNumberException e1) {
					e1.printStackTrace();
				}
			}
			
			if(player.getAvailableEmporium() != null){
				buildEmporiumK(player, board, steps, jolly, payMatch);
			}
		}
		
		else{															//if the path isn't correct, give back the player the money previously paid
			try {
				player.getRichness().setCoins(player.getRichness().getCoins()+payMatch);
			} catch (NegativeNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		
		return jolly;
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
				if(card.getColor().toString().equals(council.getCouncillors().get(i).toString())){
					match = match + 1;										//if there is a match update the counter
					this.discardedCards.add(card);							//add the card to the discarded cards
					this.cards.remove(card);								//remove the card from the politic cards
					break;													//and break the cycle
				}
			}
		}
		
		return match;
	}



/**
 * take the relative amount of money based on the number of match
 * @param match
 * @param chosenTile
 * @param player
 * @param board
 */
	public int payCoins(int match, Player player){
		int coin = player.getRichness().getCoins();
		switch(match){
		case 1: 
			if(tryPayment(player, coin, 10)!=-1)
				return 10;
			return -1;
		case 2:
			if(tryPayment(player, coin, 7)!=-1)
				return 7;
			return -1;
		case 3:
			if(tryPayment(player, coin, 4)!=-1)
				return 4;
			return -1;
		case 4: 
			return 0;
		
		default: 
			System.out.println("Your cards don't match any councillor");
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
			System.out.println("The player doesn't have enough money");
			return -1;
		}
	}

	/**
	 * build the emporium in the destination
	 * 
	 * @param player
	 * @param board
	 * @param steps
	 * @param payMatch
	 */
	public void buildEmporiumK(Player player, Board board, int steps, int jolly, int payMatch){
		try {
			this.destination.buildEmporium(player.getAvailableEmporium());
			board.getKing().setCity(destination);
			board.getDeck().discardCars(discardedCards);
		} catch (NegativeNumberException e) {
			System.out.println("The player doesn't have available emporiums");
			int currentCoin = player.getRichness().getCoins();
			this.cards.addAll(discardedCards);
			try {
				player.getRichness().setCoins(currentCoin+steps*2+jolly+payMatch);	//if the player doesn't have available emporiums, give back the money previously paid
			} catch (NegativeNumberException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();					
		}
	}
}
