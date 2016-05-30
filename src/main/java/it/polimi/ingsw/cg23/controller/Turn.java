package it.polimi.ingsw.cg23.controller;

import java.util.List;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;
import it.polimi.ingsw.cg23.model.State;
import it.polimi.ingsw.cg23.model.action.GameAction;
import it.polimi.ingsw.cg23.model.action.MarketAction;
import it.polimi.ingsw.cg23.model.action.MarketSell;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.PoliticCard;

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
	 * @param players The list of game's players.
	 * @param board The game's board.
	 */
	public Turn(List<Player> players,Board board){
		this.players=players;
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

	/**
	 * Sets the player that will play.
	 * 
	 * @return If true the game is finished and the first player of the list has built all emporiums available.
	 */
	public boolean changePlayer() {
		State status=board.getStatus();
		if(players.get((currentPlayer+1)%players.size())!=status.getFinalPlayer()){     //Control if the next player wasn't the first to build all emporiums.
			currentPlayer=(currentPlayer+1)%players.size();
			this.mainAction=true;
			this.secondAction=true;
			this.mainIndex=1;
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
	public void draw (){
		Deck deck=board.getDeck();
		PoliticCard card=deck.draw();
		if(card!=null)
			getCurrentPlayer().addPoliticCard(deck.draw());
	}
	
	/**
	 * Sets the action variables at false.
	 */
	public void controlAction(){
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
	public void runActionNormal(){
		Player player=getCurrentPlayer();
		State status=board.getStatus();
		if((action.isMain() && mainAction)||(!action.isMain() && secondAction)){		//Control if the action is authorized
			action.runAction(player, board);											//Run the action.
			controlAction();															//Control the authorization.
		}
		
		if(status.getFinalPlayer()==null && player.isAvailableEmporiumEmpty()){						//If the current player has been the first to build all emporiums.
			status.setFinalPlayer(getCurrentPlayer());
			player.getVictoryTrack().setVictoryPoints(player.getVictoryTrack().getVictoryPoints()+3);
		}
	}
	
	/**
	 * Runs the the market's action.
	 */
	public void runActionMarket(){
		Player player=getCurrentPlayer();
		State status=board.getStatus();
		if(action instanceof MarketSell 
			&& "MARKET: SELLING".equals(status.getStatus())){			//Control if the action permits to sell a item.
			action.runAction(player, board);							//Run the action.															//Control the authorization.
		}
		
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Turn [currentPlayer=" + getCurrentPlayer().getUser() + ", mainIndex=" + mainIndex
				+ ", mainAction=" + mainAction + ", secondAction=" + secondAction + "]";
	}
	
	
}
