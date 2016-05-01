package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.cg23.model.components.*;

public class Player {
	private final String user;									//name of the player
	private List<Emporium> availableEmporiums;					//the emporiums that are available to place
	private List<Emporium> usedEmporiums;						//the emporiums that are already placed
	private boolean additionalAction;							// main action given by the star bonus, the default value is false
	private final AssistantsPool assistantsPool;				
	private final Richness richness;
	private final VictoryTrack victoryTrack;
	private List<PoliticCard> politicsCards;					//the player's hand
	private List<BusinessPermitTitle> availableBusinesPermits;
	private List<BusinessPermitTitle> usedBusinessPermits;
	
	public Player(String user, int assistants,int coins) {
		this.user = user;
		this.additionalAction = false;
		this.assistantsPool = new AssistantsPool(assistants);
		this.richness = new Richness(coins);
		this.victoryTrack = new VictoryTrack();
		this.politicsCards= new ArrayList<>();
		this.availableEmporiums=new ArrayList<>();
		this.usedEmporiums=new ArrayList<>();
		this.availableBusinesPermits=new ArrayList<>();
		this.usedBusinessPermits=new ArrayList<>();
	}

	/**
	 * Returns the name of the player. It's only one in the game.
	 * 
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Adds an available emporium at the player's list.
	 * 
	 * @param availableEmporium 
	 */
	public void setAvailableEmporium(Emporium availableEmporium) {
		this.availableEmporiums.add(availableEmporium);
	}
	
	/**
	 * Returns an available emporium of the player.
	 * 
	 * @return an available emporium or null (if the list in is empty).
	 */
	public Emporium getAvailableEmporium() {
		if(!availableEmporiums.isEmpty())
			return availableEmporiums.remove(0);
		else
			return null;
	}
	
	/**
	 * Returns the list of all emporiums builded by the player.
	 * 
	 * @return the emporiums used during the game.
	 */
	public List<Emporium> getEmporiums() {
		return usedEmporiums;
	}

	/**
	 * Adds the emporium at the list of emporiums used
	 * 
	 * @param emporium an emporium builded.
	 */
	public void setEmporium(Emporium emporium) {
		this.usedEmporiums.add(emporium);
	}

	/**
	 * Returns the status of the addictional action. If it's true, the player can perform another principal action.
	 * 
	 * @return the additionalAction status.
	 */
	public boolean isAdditionalAction() {
		return additionalAction;
	}

	/**
	 * Negates the additionalAction status.
	 */
	public void switchAdditionalAction() {
		this.additionalAction = !this.additionalAction;
	}

	/**
	 * Returns the assistants pool of the player.
	 * 
	 * @return the assistants of the player
	 */
	public int getAssistants() {
		return assistantsPool.getAssistants();
	}

	/**
	 * Modify the player's assistants.
	 * 
	 * @param assistants the assistants to set.
	 */
	public void setAssistants(int assistants) {
		assistantsPool.setAssistants(assistants);
	}
	
	/**
	 * Returns the richness of the player.
	 * 
	 * @return the coins of the player
	 */
	public int getCoins() {
		return richness.getCoins();
	}

	/**
	 * Modify the player's richness.
	 * 
	 * @param coins the coins to set.
	 */
	public void setCoins(int coins) {
		richness.setCoins(coins);
	}
	
	/**
	 * Returns the vitcory points of the player.
	 * 
	 * @return the victory points of the player.
	 */
	public int getVictoryPoints() {
		return victoryTrack.getVictoryPoints();
	}
	
	/**
	 * Modify the player's victory points.
	 * 
	 * @param points the victory points to set.
	 */
	public void setVictoryPoints(int points) {
		victoryTrack.setVictoryPoints(points);
	}
	
	/**
	 * Adds a politic card at player's hand.
	 * 
	 * @param politicCard a politic card.
	 */
	public void addPoliticCard(PoliticCard politicCard) {
		politicsCards.add(politicCard);
	}
	
	/**
	 * Returns all politc cards of the player.
	 * 
	 * @return the player's hand.
	 */
	public List<PoliticCard> getHand() {
		return politicsCards;
	}

	/**
	 * Returns all available titles of the player.
	 * 
	 * @return the availableBusinessPermits.
	 */
	public List<BusinessPermitTitle> getAvailableBusinessPermits() {
		return availableBusinesPermits;
	}
	
	/**
	 * Adds a business permit title at the player's available titles.
	 * 
	 * @param businessPermit the business permit title to add at player's permits.
	 */
	public void addAvailableBusinessPermit(BusinessPermitTitle businessPermit) {
		this.availableBusinesPermits.add(businessPermit);
	}

	/**
	 * Returns all used titles of the player.
	 * 
	 * @return the usedBusinessPermits.
	 */
	public List<BusinessPermitTitle> getUsedBusinessPermit() {
		return usedBusinessPermits;
	}

	
	/**
	 * Adds a business permits title at player's used titles.
	 * 
	 * @param usedBusinessPermits the used business permit title.
	 */
	public void setUsedBusinessPermit(BusinessPermitTitle businessPermit) {
		this.usedBusinessPermits.add(businessPermit);
	}

	/**
	 * Returns all stats of the player.
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Giocatore: da fare!";
	}
	
}
