package it.polimi.ingsw.cg23.model;

import java.util.List;
import it.polimi.ingsw.cg23.model.components.*;

public class Player {
	private final String user;									//name of the player
	private List<Emporium> availableEmporiums;					//the emporiums that are available to use
	private List<Emporium> usedEmporiums;						//the emporiums that are just placed
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
		this.politicsCards= null;
		this.availableEmporiums=null;
		this.usedEmporiums=null;
		this.availableBusinesPermits=null;
		this.usedBusinessPermits=null;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param the available emporiums
	 */
	public void setAvailableEmporiums(List<Emporium> availableEmporiums) {
		this.availableEmporiums=availableEmporiums;
	}
	
	/**
	 * @return an available emporium
	 */
	public Emporium getAvailableEmporium() {
		if(!availableEmporiums.isEmpty())
			return availableEmporiums.remove(0);
		else
			return null;
	}
	
	/**
	 * @return the used emporiums
	 */
	public List<Emporium> getEmporiums() {
		return usedEmporiums;
	}

	
	/**
	 * @param emporiums the used emporium to set
	 */
	public void setEmporium(Emporium emporium) {
		this.usedEmporiums.add(emporium);
	}

	
	/**
	 * @return the additionalAction
	 */
	public boolean isAdditionalAction() {
		return additionalAction;
	}

	/**
	 * @param additionalAction the additionalAction to set
	 */
	public void setAdditionalAction(boolean additionalAction) {
		this.additionalAction = additionalAction;
	}

	/**
	 * @return the assistantsPool
	 */
	public AssistantsPool getAssistantsPool() {
		return assistantsPool;
	}

	/**
	 * @return the richness
	 */
	public Richness getRichness() {
		return richness;
	}

	/**
	 * @return the victoryTrack
	 */
	public VictoryTrack getVictoryTrack() {
		return victoryTrack;
	}
	
	/**
	 * @param politicCard the politic card to add at player's hand
	 */
	public void setHand(PoliticCard politicCard) {
		this.politicsCards.add(politicCard);
	}
	
	/**
	 * @return the player's hand
	 */
	public List<PoliticCard> getHand() {
		return politicsCards;
	}

	
	/**
	 * @return the availableBusinessPermit
	 */
	public List<BusinessPermitTitle> getAvailableBusinessPermit() {
		return availableBusinesPermits;
	}
	

	/**
	 * @param businessPermit the business permit title to add at player's permits
	 */
	public void setAvailableBusinessPermit(BusinessPermitTitle businessPermit) {
		this.availableBusinesPermits.add(businessPermit);
	}

	
	/**
	 * @return the usedBusinessPermits
	 */
	public List<BusinessPermitTitle> getUsedBusinessPermit() {
		return usedBusinessPermits;
	}

	
	/**
	 * @param usedBusinessPermits the used business permit title to add at player's permits
	 */
	public void setUsedBusinessPermit(BusinessPermitTitle businessPermit) {
		this.usedBusinessPermits.add(businessPermit);
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Giocatore: da fare!";
	}
	
}
