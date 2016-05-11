package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.components.Councillor;
import it.polimi.ingsw.cg23.model.components.Deck;
import it.polimi.ingsw.cg23.model.components.King;
import it.polimi.ingsw.cg23.model.components.NobilityTrack;

public class Board {
	private final Deck deck;
	private final List<Region> regions;
	private final NobilityTrack nobilityTrack;
	private final King king;
	private final List<Councillor> councillorPool;
	
	public Board(Deck deck, List<Region> regions, NobilityTrack nobilityTrack, King king) {
		this.deck = deck;
		this.regions = regions;
		this.nobilityTrack = nobilityTrack;
		this.king = king;
		this.councillorPool=new ArrayList<>();
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
	 * Returns the list of the regions.
	 * 
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
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
	 * Returns the king.
	 * 
	 * @return the king
	 */
	public King getKing() {
		return king;
	}

	/**
	 * @return the councillorPool
	 */
	public List<Councillor> getCouncillorPool() {
		return councillorPool;
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
