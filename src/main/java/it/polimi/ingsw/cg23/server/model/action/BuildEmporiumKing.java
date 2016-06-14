package it.polimi.ingsw.cg23.server.model.action;

import java.util.ArrayList;
import java.util.List;


import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.EmporiumsChange;
import it.polimi.ingsw.cg23.server.controller.change.ErrorChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.Council;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to build an emporium by the help of the king. It contains a boolean 
 * that show if it is a main action, a list of politic cards, a list of discarded cards where are added the 
 * used politic cards, and the final destination of the king
 * 
 * @author Vincenzo
 */
public class BuildEmporiumKing extends GameAction implements StandardAction{

	private static final long serialVersionUID = -4377073782074773902L;
	private final List<PoliticCard> cards;
	private List<PoliticCard> discardedCards = new ArrayList<>();
	private final City destination;
	private final ControlAction controlAction;
	

	/**
	 * the constructor set the variable of the class: main is set to true, cards and destination are
	 * set as the parameter given to the method
	 * 
	 * @param cards
	 * @param destination
	 */
	public BuildEmporiumKing(List<PoliticCard> cards, City destination) {
		super(true);
		this.cards = cards;
		this.destination = destination;
		controlAction = new ControlAction();
	}


	/**
	 * runAction control how many match there are between cards and councillors, how many jolly there are
	 * in the politic cards, and it take the corresponding amount of money from the player. Then it move 
	 * the king in the final destination and take two coins for each step from the player.
	 * 
	 * @param player 
	 * @param board
	 */
	@Override
	public boolean runAction(Player player, Board board) {
		City realDestination = this.controlAction.controlCity(this.destination, board);
		List<PoliticCard> realHand = this.controlAction.controlPoliticCards(cards, player);
		if(realDestination != null && realHand != null){
			int jolly = howManyJolly(board);															//control how many jolly there are
			int match = jolly + howManyMatch(board, board.getKing().getCouncil());						//control how many match there are
			player.getHand().removeAll(discardedCards);
			int payMatch = payCoins(match, player);														//pay the amount of coins relative to the match
			int steps = (int) board.getKing().getCity().minimumDistance(realDestination, new ArrayList<City>());		//control the distance between the king's city and the destination
			int coin = player.getRichness().getCoins();													//control the richness of the player			
		
			if(payMatch!=-1){			
				try {
					coin = coin - steps*2 - jolly;
					player.getRichness().setCoins(coin);
				} catch (NegativeNumberException e) {
					this.notifyObserver(new ErrorChange(e.getMessage()));
					getLogger().error("The player doesn't have enough money!", e);
					try {
						player.getRichness().setCoins(coin+payMatch);
						this.cards.addAll(discardedCards);
						return false;
					} catch (NegativeNumberException e1) {
						this.notifyObserver(new ErrorChange(e.getMessage()));
						getLogger().error(e1);
					}
				}
			
				if(player.getAvailableEmporium() != null){
					try {
						buildEmporiumK(player, board, steps, jolly, payMatch);
					} catch (NegativeNumberException e) {
						this.notifyObserver(new ErrorChange(e.getMessage()));
						getLogger().error(e);
						return false;
					}
				}
			
				else{															//if the path isn't correct, give back the player the money previously paid
					try {
						player.getRichness().setCoins(player.getRichness().getCoins()+payMatch);
					} catch (NegativeNumberException e) {
						this.notifyObserver(new ErrorChange(e.getMessage()));
						getLogger().error(e);
					}
				}
				
			}
			else
				return false;
			board.notifyObserver(new BoardChange(board));
			board.notifyObserver(new EmporiumsChange(board.getKing().getCity().getEmporiums()));
			return true;
		}
		return false;
	}

	
	/**
	 * control how many jolly are there in the chosen politic cards
	 * 
	 * @param board
	 * @return jolly
	 */
	private int howManyJolly(Board board){
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
	 * control how many match between are there between 
	 * the councillors and the politic cards
	 * 
	 * @param board
	 * @param council
	 * @return match
	 */
	private int howManyMatch(Board board, Council council){
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
 * take the relative amount of money based on the number of match
 * @param match
 * @param chosenTile
 * @param player
 * @param board
 */
	private int payCoins(int match, Player player){
		int coin = player.getRichness().getCoins();
		int payment = (4-match)*3+1;
		if(match == 0){
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
	private int tryPayment(Player player,int money, int payment){
		try {
			money = money - payment;
			player.getRichness().setCoins(money);
			return 0;
		} catch (NegativeNumberException e) {
			this.cards.addAll(discardedCards);
			getLogger().error("The player doesn't have enough money", e);
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
	 * @throws NegativeNumberException 
	 */
	private void buildEmporiumK(Player player, Board board, int steps, int jolly, int payMatch) throws NegativeNumberException{
		try {
			this.destination.buildEmporium(player.getAvailableEmporium());
			board.getKing().setCity(destination);
			board.getDeck().discardCards(discardedCards);
		} catch (NegativeNumberException e) {
			getLogger().error("The player doesn't have enough assistants", e);
			int currentCoin = player.getRichness().getCoins();
			this.cards.addAll(discardedCards);
			player.getRichness().setCoins(currentCoin+steps*2+jolly+payMatch);	//if the player doesn't have available emporiums, give back the money previously paid
		}
	}
}
