package it.polimi.ingsw.cg23.model;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.model.components.*;
import it.polimi.ingsw.cg23.model.exception.NegativeNumberException;

public class Player {
	private final String user;									//name of the player
	private List<Emporium> availableEmporiums;					//the emporiums that are available to place
	private List<Emporium> usedEmporiums;						//the emporiums that are already placed
	private boolean additionalAction;							// main action given by the star bonus, the default value is false
	private final AssistantsPool assistantsPool;				
	private final Richness richness;
	private final VictoryTrack victoryTrack;
	private List<PoliticCard> politicsCards;					//the player's hand
	private List<BusinessPermitTile> availableBusinesPermits;
	private List<BusinessPermitTile> usedBusinessPermits;
	private final NobilityTrack playerNobilityTrack;			//to make getter and setter
	private NobilityBox nobilityBox; 							//the nobility box contained in the nobility track.
	private int nobilityBoxPosition;							//to make getter and setter
	
	public Player(String user, int assistants, int coins, NobilityTrack nobilityTrack) { 
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
		this.nobilityBoxPosition=0; 
		this.playerNobilityTrack= nobilityTrack;
		//this.nobilityBox=this.playerNobilityTrack.getNobilityBoxes().get(0);
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
	 * @throws NegativeNumberException the number of assistants must be positive.
	 */
	public void setAssistants(int assistants) throws NegativeNumberException {
		if (assistants>=0)
			assistantsPool.setAssistants(assistants);
		else
			throw new NegativeNumberException("The number of assistants can't be negative.");
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
	 * @throws NegativeNumberException the number of coins must be positive.
	 */
	public void setCoins(int coins) throws NegativeNumberException {
		if(coins>=0)
			richness.setCoins(coins);
		else
			throw new NegativeNumberException("The number of coins can't be negative.");
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
	 * Returns all politic cards of the player.
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
	public List<BusinessPermitTile> getAvailableBusinessPermits() {
		return availableBusinesPermits;
	}
	
	/**
	 * Adds a business permit title at the player's available titles.
	 * 
	 * @param businessPermit the business permit title to add at player's permits.
	 */
	public void addAvailableBusinessPermit(BusinessPermitTile businessPermit) {
		this.availableBusinesPermits.add(businessPermit);
	}

	/**
	 * Returns all used titles of the player.
	 * 
	 * @return the usedBusinessPermits.
	 */
	public List<BusinessPermitTile> getUsedBusinessPermit() {
		return usedBusinessPermits;
	}

	
	/**
	 * Adds a business permits title at player's used titles.
	 * 
	 * @param usedBusinessPermits the used business permit title.
	 */
	public void setUsedBusinessPermit(BusinessPermitTile businessPermit) {
		this.usedBusinessPermits.add(businessPermit);
	}
	
	

	/**
	 * @return the nobilityBoxPoistion
	 */
	public int getNobilityBoxPosition() {
		return nobilityBoxPosition;
	}

	/**
	 * @param nobilityBoxPoistion the nobilityBoxPoistion to set
	 */
	public void setNobilityBoxPoistion(int nobilityBoxPoistion) {
		this.nobilityBoxPosition = nobilityBoxPoistion;
	}

	/**
	 * @return the playerNobilityTrack
	 */
	public NobilityTrack getPlayerNobilityTrack() {
		return playerNobilityTrack;
	}

	/**
	 * Returns the nobility box of the player.
	 * 
	 * @return the nobilityBox
	 */
	public NobilityBox getNobilityBox() {
		return nobilityBox;
	}

	/**
	 * Sets the new nobility box of the player.
	 * 
	 * @param nobilityBox the nobilityBox to set
	 */
	public void setNobilityBox(NobilityBox nobilityBox) {
		this.nobilityBox = nobilityBox;
	}
	

	/**
	 * Returns all stats of the player.
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "-User: " + user + "\n-Assistants: " + getAssistants() + "\n-Coins: "+ getCoins() + "\n-Victory points: " + getVictoryPoints() ;
	}
	
}
