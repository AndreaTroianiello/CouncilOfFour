package it.polimi.ingsw.cg23.client;


import java.awt.Color;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
import it.polimi.ingsw.cg23.utility.ColorManager;

public class ClientModel {
	private Board model;
	private Player player;
	private Logger logger;
	
	public ClientModel() {
		this.model=null;
		this.player=null;
		logger= Logger.getLogger(ClientModel.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
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
		List<Player> players=model.getPlayers();
		for(Player player: players)
			if(this.player.getUser().equals(player.getUser()))
				setPlayer(player);
		this.model = model;
	}
	
	public void setPlayer(Player player){
		this.player=player;
	}
	
	public Player getPlayer(){
		return player;
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
	
	public BusinessPermitTile findPlayerTile(String numberTile){
		try{
			int number=Integer.parseInt(numberTile);
			return player.getAvailableBusinessPermits().get(number);
		}catch(NumberFormatException e){
			logger.error(e);
			return null;
		}	
	}
	
	public BusinessPermitTile findRegionTile(String numberTile,Region region){
		try{
			int number=Integer.parseInt(numberTile);
			return region.getDeck().getShowedDeck().get(number);
		}catch(NumberFormatException e){
			logger.error(e);
			return null;
		}	
	}
}
