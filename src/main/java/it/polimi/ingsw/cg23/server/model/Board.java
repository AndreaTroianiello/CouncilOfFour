package it.polimi.ingsw.cg23.server.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.observer.Observable;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.StateChange;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.components.Deck;
import it.polimi.ingsw.cg23.server.model.components.King;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.model.marketplace.Market;

/**
 * The board contains all objects of the game, this is the application's model.
 * @author Andrea
 *
 */
public class Board extends Observable<Change> implements Serializable{

	private static final long serialVersionUID = 4584086280841338196L;
	private Deck deck;
	private List<Region> regions;
	private List<Type> types;
	private NobilityTrack nobilityTrack;
	private King king;
	private final List<Councillor> councillorPool;
	private final List<Player> players;
	private State status;
	private final Market market;
	
	/**
	 * The constructor of the board.
	 * @param deck The deck of politic cards.
	 * @param regions The list of regions.
	 * @param types The list of type.
	 * @param nobilityTrack  The nobility track.
	 * @param king The king pawn.
	 */
	public Board(Deck deck, List<Region> regions, List<Type> types, NobilityTrack nobilityTrack, King king) {
		this.deck = deck;
		this.regions = regions;
		this.types = types;
		this.nobilityTrack = nobilityTrack;
		this.king = king;
		this.councillorPool=new ArrayList<>();
		this.players=new ArrayList<>();
		this.status=new State();
		this.market=new Market();
	}

	/**
	 * Returns the deck of politic cards.
	 * 
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * Sets a new deck in the board.
	 * 
	 * @param deck the deck to set
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	/**
	 * Returns the types' list.
	 * 
	 * @return the types
	 */
	public List<Type> getTypes() {
		return types;
	}
	
	/**
	 * Sets type of the map.
	 * @param types the types to set
	 */
	public void setTypes(List<Type> types) {
		this.types = types;
	}

	/**
	 * Returns the list of the regions.
	 * 
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}
	
	/**
	 * Sets the regions of the map.
	 * @param regions the regions to set
	 */
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	
	/**
	 * Returns the nobility track.
	 * 
	 * @return the nobilityTrack
	 */
	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}
	
	/**
	 * Sets a new nobility track in the board.
	 * 
	 * @param nobilityTrack the nobilityTrack to set
	 */
	public void setNobilityTrack(NobilityTrack nobilityTrack) {
		this.nobilityTrack = nobilityTrack;
	}
	
	/**
	 * Returns the king.
	 * 
	 * @return the king
	 */
	public King getKing() {
		return king;
	}
	
	/**
	 * Sets the king in the board.
	 * 
	 * @param king the king to set
	 */
	public void setKing(King king) {
		this.king = king;
	}


	/**
	 * Returns the list of councillor.
	 * 
	 * @return the councillorPool
	 */
	public List<Councillor> getCouncillorPool() {
		return councillorPool;
	}
	
	
	/**
	 * Adds the councillor at the pool.
	 * 
	 * @param councillor
	 */
	public void setCouncillor(Councillor councillor){
		councillorPool.add(councillor);
	}
	
	/**
	 * Gets the list of the players.
	 * @return players 
	 */
	public List<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * Adds the player at the list.
	 * @param player The player to add.
	 */
	public void addPlayer(Player player){
		this.players.add(player);
	}
	
	/**
	 * Returns the councillor of wished color.
	 * 
	 * @param color the color of the wished councillor.
	 * @return the councillor.
	 */
	public Councillor getCouncillor(Color color){
		for(int index=0;index<councillorPool.size();index++){
			if(color.equals(councillorPool.get(index).getColor()))
				 return councillorPool.remove(index);
		}
		return null;
	}

	/**
	 * Returns the status of the model.
	 * 
	 * @return the status
	 */
	public State getStatus() {
		return status;
	}
	
	/**
	 * Changes the status of the model.
	 */
	public void changeStatus() {
		String statusString=status.getStatus();
		
		if("TURN".equals(statusString))
			status.setStatus("MARKET: SELLING");
		
		if("MARKET: SELLING".equals(statusString))
			status.setStatus("MARKET: BUYING");
		
		if("MARKET: BUYING".equals(statusString) || "INITIALIZATION".equals(statusString))
			status.setStatus("TURN");

		if(status.getFinalPlayer()!=null && !"FINAL TURN".equals(statusString))
			status.setStatus("FINAL TURN");
		
		this.notifyObserver(new StateChange(status));			//When the state is changed notify the observer.
	}
	
	/**
	 * Returns the market of the game.
	 * @return market
	 */
	public Market getMarket(){
		return market;
	}

	/**
	 * It generates a string formed by the most significant statistics of the Board.
	 * @return string
	 */
	@Override
	public String toString() {
		return "Board [deck=" + deck.deckIsEmpty() + ", regions=" + regions.size()
				+", types="+types.size()+", nobilityTrack=" + nobilityTrack.getNobilityBoxes().size()+ ", king=" + king + "]"
			;
	}
	
	
	
}
