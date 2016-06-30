package it.polimi.ingsw.cg23.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.server.model.components.PoliticCard;

public class SelectedElements {
	
	private Region region;
	private City city;
	private BusinessPermitTile tile;
	private Color councillor;
	private final List<PoliticCard> cards;
	
	public SelectedElements() {
		this.region=null;
		this.city=null;
		this.cards=new ArrayList<>();
	}
	
	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}
	
	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	/**
	 * @return the tile
	 */
	public BusinessPermitTile getTile() {
		return tile;
	}
	
	/**
	 * @param tile the tile to set
	 */
	public void setTile(BusinessPermitTile tile) {
		this.tile = tile;
	}
	
	/**
	 * @return the tile
	 */
	public Color getCouncillor() {
		return councillor;
	}
	
	/**
	 * @param tile the tile to set
	 */
	public void setCouncillor(Color councillor) {
		this.councillor = councillor;
	}
	
	/**
	 * @return the cards
	 */
	public List<PoliticCard> getCards() {
		return cards;
	}
	
	public void addCard(PoliticCard card){
		cards.add(card);
	}
	
	public void resetAll(){
		region=null;
		city=null;
		tile=null;
		cards.clear();
	}
}
