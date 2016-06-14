package it.polimi.ingsw.cg23.server.controller;

import java.util.List;

import it.polimi.ingsw.cg23.server.controller.change.ErrorChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.State;
import it.polimi.ingsw.cg23.server.model.action.GameAction;
import it.polimi.ingsw.cg23.server.model.action.MarketAction;
import it.polimi.ingsw.cg23.server.model.action.MarketSell;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * The turn governs the actions of a player set.
 * @author Andrea
 *
 */
public class Turn {
	
	private List<Player> players;									//The players of the game.
	private int currentPlayer;										//The current player.
	private GameAction action;										//The action of the turn.
	private final Board board; 
	private int mainIndex;											//Main action's counter.
	private boolean mainAction;										//Authorization of the main action.
	private boolean secondAction;									//Authorization of the second action
	
	/**
	 * The constructor of Turn.
	 * @param board The game's board.
	 */
	public Turn(Board board){
		this.players=board.getPlayers();
		this.board=board;
		this.currentPlayer=0;
		this.action=null;
		this.mainIndex=1;
		this.mainAction=true;
		this.secondAction=true;
	}

	/**
	 * Returns the current player.
	 * 
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	public boolean isChangeState(){
		return currentPlayer==0 && !"FINAL TURN".equals(board.getStatus().getStatus()) ;
	}
	/**
	 * Sets the player that will play.
	 * 
	 * @return If true the game is finished and the first player of the list has built all emporiums available.
	 * @throws NegativeNumberException 
	 */
	public boolean changePlayer() throws NegativeNumberException {
		State status=board.getStatus();
		if(players.get((currentPlayer+1)%players.size())!=status.getFinalPlayer()){    //Control if the next player wasn't the first to build all emporiums.
			currentPlayer=(currentPlayer+1)%players.size();
	
			if(isChangeState()){
				board.changeStatus();
				List<Player> players=board.getPlayers();
				if("MARKET: BUYING".equals(status.getStatus()))
					setPlayers(board.getMarket().generatePlayersList(players));
				if("TURN".equals(status.getStatus())){
					setPlayers(players);
					board.getMarket().resetItems();
				}
			}
			
			status.setCurrentPlayer(players.get(currentPlayer));			
			if(getCurrentPlayer().isAdditionalAction())
				getCurrentPlayer().switchAdditionalAction();
			if(status.getStatus().contains("TURN")){
				draw();
				this.mainAction=true;
				this.secondAction=true;
				this.mainIndex=1;
			}
			return false;
		}
		return true;
	}
	
	/**
	 * Sets the action to do.
	 */
	public void setAction(GameAction action){
		this.action=action;
	}
	
	/**
	 * Returns the game board.
	 * @return board.
	 */
	public Board getBoard(){
		return board;
	}
	
	/**
	 * Sets the players of the game.
	 * @param players a list of the players.
	 */
	 public void setPlayers(List<Player> players){
		 this.players=players;
	 }
	 
	/**
	 * It indicates whether a player has used all available emporiums.
	 * 
	 * return finalTurn. It is true if a players has completed the emporiums.
	 */
	 public boolean isFinalTurn(){									//Return true if a player has built all emporiums.
		 return board.getStatus().getFinalPlayer()!=null;
	 }
	
	/**
	 * The player draws a politic card.
	 */
	private void draw (){
		Deck deck=board.getDeck();
		PoliticCard card=deck.draw();
		if(card!=null)
			getCurrentPlayer().addPoliticCard(deck.draw());
	}
	
	/**
	 * Sets the action variables at false.
	 */
	private void controlAction(){
		if(action.isMain() && mainAction){							//Control if the action is a main action and it's authorized.
			--mainIndex;											//Decrement the main action's counter.
			if(mainIndex==0)
				mainAction=false;
		}
		if(!action.isMain() && secondAction)						//Control if the action is not a main action and it's authorized.
			secondAction=false;
		if(getCurrentPlayer().isAdditionalAction()){				//Control if the second action has enabled a new main action.
			mainIndex++;
			mainAction=true;
			getCurrentPlayer().switchAdditionalAction();
		}
			
	}
	
	/**
	 * Runs the the normal action.
	 */
	private void runActionNormal(){
		Player player=getCurrentPlayer();
		State status=board.getStatus();
		if((action.isMain() && mainAction)||(!action.isMain() && secondAction)){		//Control if the action is authorized
			action.runAction(player, board);											//Run the action.
			controlAction();															//Control the authorization.
		}
		else
			action.notifyObserver(new ErrorChange("Action refused."));
		if(status.getFinalPlayer()==null && player.isAvailableEmporiumEmpty())					//If the current player has been the first to build all emporiums.
			status.setFinalPlayer(getCurrentPlayer());
	}
	
	/**
	 * Runs the the market's action.
	 */
	private void runActionMarket(){
		Player player=getCurrentPlayer();
		State status=board.getStatus();
		if(action instanceof MarketSell 
			&& "MARKET: SELLING".equals(status.getStatus())){			//Control if the action permits to sell a item.
			action.runAction(player, board);							//Run the action.															//Control the authorization.
		}
		else
			action.notifyObserver(new ErrorChange("Action refused."));
		
	}
	
	/**
	 * Controls if the action is a normal action or a market's action and runs it.
	 */
	public void runAction(){
		if(action instanceof MarketAction)
			runActionMarket();
		else
			runActionNormal();
	}

	/**
	 * It generates a string formed by the most significant statistics of the Turn.
	 * @return string
	 */
	@Override
	public String toString() {
		return "Turn [currentPlayer=" + getCurrentPlayer().getUser() + ", mainIndex=" + mainIndex
				+ ", mainAction=" + mainAction + ", secondAction=" + secondAction + "]";
	}
	
	
}
