package it.polimi.ingsw.cg23.client;


import java.awt.Color;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.utility.ColorManager;

public class ClientModel {
	private Board model;
	public ClientModel() {
		this.model=null;
	}
	/**
	 * @return the model
	 */
	public Board getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(Board model) {
		this.model = model;
	}

	public City findCity(String cityName){
		for(int i=0; i<model.getRegions().size(); i++){
			City c=model.getRegions().get(i).searchCityById(cityName.toUpperCase().charAt(0));
			if(c!=null)
				return c;
		}
		return null;
	}

	public Region findRegion(String regionName){
		for(int i=0; i<model.getRegions().size(); i++){
			if(model.getRegions().get(i).getName().equals(regionName))
				return model.getRegions().get(i);
		}
		return null;
	}

	public Color findColor(String colorName){
		return new ColorManager().getColor(colorName);
	}
}
