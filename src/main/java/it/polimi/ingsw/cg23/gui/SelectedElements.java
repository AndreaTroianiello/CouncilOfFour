package it.polimi.ingsw.cg23.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

/**
 * SelectedElements contains the chosen objects by the user. 
 * @author Andrea
 *
 */
public class SelectedElements {
	
	private Region region;
	private City city;
	private BusinessPermitTile tile;
	private Color councillor;
	private final List<PoliticCard> cards;
	
	/**
	 * The constructor of SelectedElements. Initializes the parameters at the default values.
	 */
	public SelectedElements() {
		this.region=null;
		this.city=null;
		this.cards=new ArrayList<>();
	}
	
	/**
	 * Returns the selected region.
	 * @return the region.
	 */
	public Region getRegion() {
		return region;
	}
	
	/**
	 * Sets a new selected region.
	 * @param region the new selected region.
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	
	/**
	 * Returns the selected city.
	 * @return the city.
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * Sets a new selected city.
	 * @param city the new selected city.
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	/**
	 * Returns the selected tile.
	 * @return the tile.
	 */
	public BusinessPermitTile getTile() {
		return tile;
	}
	
	/**
	 * Sets a new selected tile.
	 * @param tile the new selected tile.
	 */
	public void setTile(BusinessPermitTile tile) {
		this.tile = tile;
	}
	
	/**
	 * Returns the color of the selected councillor.
	 * @return the color of the selected councillor.
	 */
	public Color getCouncillor() {
		return councillor;
	}
	
	/**
	 * Sets the color of the new selected councillor.
	 * @param councillor the color of the new selected councillor.
	 */
	public void setCouncillor(Color councillor) {
		this.councillor = councillor;
	}
	
	/**
	 * Returns all politics cards selected.
	 * @return the cards
	 */
	public List<PoliticCard> getCards() {
		return cards;
	}
	
	/**
	 * Adds a new selected card at the local hand.
	 * @param card
	 */
	public void addCard(PoliticCard card){
		cards.add(card);
	}
	
	/**
	 * Reinitializes all parameters at the default value.
	 */
	public void resetAll(){
		region=null;
		city=null;
		tile=null;
		cards.clear();
	}
}
