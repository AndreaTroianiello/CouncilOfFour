package it.polimi.ingsw.cg23.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.components.*;
/**
 * The class of the player. The player is characterized by a username and contains a list of his emporiums (used and available), a richness,
 * a victory track, a list of politic cards and a nobility position.
 * 
 * @author Andrea
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 3461638919381415919L;
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
	private final NobilityTrack nobilityTrack;					//the nobility track
	private int nobilityBoxPosition;							//the position of the player
	
	/**
	 * The class' constructor. Initializes the player with default stats.
	 * @param user The username of the player.
	 * @param assistants The initial number of player's assistants.
	 * @param coins The initial number of player's coins.
	 * @param nobilityTrack The nobility track of the game.
	 */
	public Player(String user, NobilityTrack nobilityTrack) { 
		this.user = user;
		this.additionalAction = false;
		this.assistantsPool = new AssistantsPool();
		this.richness = new Richness();
		this.victoryTrack = new VictoryTrack();
		this.politicsCards= new ArrayList<>();
		this.availableEmporiums=new ArrayList<>();
		this.usedEmporiums=new ArrayList<>();
		this.availableBusinesPermits=new ArrayList<>();
		this.usedBusinessPermits=new ArrayList<>();
		this.nobilityBoxPosition=0; 
		this.nobilityTrack= nobilityTrack;
		this.nobilityTrack.getNobilityBoxes().get(0).addPlayer(this);
		initializeAvailableEmporium();
	}

	/**
	 * Returns the name of the player. It's only one in the game.
	 * 
	 * @return the user
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * Adds the availables emporiums at the player's list.
	 * 
	 * @param availableEmporium 
	 */
	private void initializeAvailableEmporium() {
		for(int index=0;index<10;++index)
			this.availableEmporiums.add(new Emporium(this));
	}
	
	/**
	 * Returns the status of the emporiums available
	 * 
	 * @return the status of availableEmporiums' list. It is true if this list is empty.
	 */
	public boolean isAvailableEmporiumEmpty(){
		return availableEmporiums.isEmpty();
	}
	
	/**
	 * Returns an available emporium of the player.
	 * 
	 * @return an available emporium or null (if the list in is empty).
	 */
	public Emporium getAvailableEmporium() {
		if(isAvailableEmporiumEmpty())
			return null;
		return availableEmporiums.remove(0);
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
	public AssistantsPool getAssistantsPool() {
		return assistantsPool;
	}

	
	/**
	 * Returns the richness of the player.
	 * 
	 * @return the coins of the player
	 */
	public Richness getRichness() {
		return richness;
	}
	
	/**
	 * Returns the victory track of the player.
	 * 
	 * @return the victory track of the player.
	 */
	public VictoryTrack getVictoryTrack() {
		return this.victoryTrack;
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
	 * Returns the position of the player in the nobility track.
	 * 
	 * @return the nobilityBoxPoistion
	 */
	public int getNobilityBoxPosition() {
		return nobilityBoxPosition;
	}

	/**
	 * Sets the position of the player on the nobility track.
	 * 
	 * @param nobilityBoxPoistion the nobilityBoxPoistion to set.
	 */
	public void setNobilityBoxPoistion(int nobilityBoxPoistion) {
		this.nobilityBoxPosition = nobilityBoxPoistion;
	}

	/**
	 * Returns the nobility track of the player.
	 * 
	 * @return the playerNobilityTrack
	 */
	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}

	/**
	 * It generates a string formed by the most significant statistics of the Player.
	 * @return string
	 */
	@Override
	public String toString() {
		return "Player [user=" + user + ", additionalAction=" + additionalAction + ", assistantsPool=" + assistantsPool.getAssistants()
				+ ", richness=" + richness.getCoins() + ", victoryTrack=" + victoryTrack.getVictoryPoints() + ", nobilityBoxPosition="
				+ nobilityBoxPosition + "]";
	}
	
}
