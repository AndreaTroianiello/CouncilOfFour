package it.polimi.ingsw.cg23.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.controller.change.Change;
import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.observer.Observable;

public class Board extends Observable<Change>{
	private Deck deck;
	private List<Region> regions;
	private List<Type> types;
	private NobilityTrack nobilityTrack;
	private King king;
	private final List<Councillor> councillorPool;
	private final List<Player> players;
	
	public Board(Deck deck, List<Region> regions, List<Type> types, NobilityTrack nobilityTrack, King king) {
		this.deck = deck;
		this.regions = regions;
		this.types = types;
		this.nobilityTrack = nobilityTrack;
		this.king = king;
		this.councillorPool=new ArrayList<>();
		this.players=new ArrayList<>();
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
		for(Councillor councillor: councillorPool){
			if(color.equals(councillor.getColor()))
				return councillor;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Board [deck=" + deck + ", regions=" + regions + ", nobilityTrack=" + nobilityTrack + ", king=" + king
				+ "]";
	}
	
	
}
