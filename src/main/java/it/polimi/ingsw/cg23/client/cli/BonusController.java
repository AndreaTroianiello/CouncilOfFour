package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.server.controller.action.PerformBonus;
import it.polimi.ingsw.cg23.server.model.City;
import it.polimi.ingsw.cg23.server.model.bonus.Bonus;
import it.polimi.ingsw.cg23.server.model.bonus.BonusCityToken;
import it.polimi.ingsw.cg23.server.model.bonus.BonusGetPermitTile;
import it.polimi.ingsw.cg23.server.model.bonus.BonusTileBonus;

public class BonusController {

	private ControllerCLI controller;
	private Logger logger;
	private ClientModel clientModel;

	/**
	 * The constructor of BonusController
	 * @param controller The main controller of the client.
	 */
	public BonusController(ControllerCLI controller) {
		this.controller=controller;
		logger = Logger.getLogger(CommandController.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Notifies the controller with a string.
	 * @param string The string to be communicated
	 * @param bonus The bonus to be communicated
	 * @throws IOException if the connection has problems
	 */
	public void updateCommand(String string, Bonus bonus) throws IOException{
		clientModel=controller.getModel();
		try{
			parseCommand(string,bonus);
			controller.setBonus(null);
		}catch(NoSuchElementException | NullPointerException | NumberFormatException e){
			logger.error("Wrong command.");
		}
	}
	
	/**
	 * Manages the command string.
	 * @param string The string that contains the commands
	 * @throws IOException If the connection has problems
	 */
	private void parseCommand(String string, Bonus bonus) throws IOException{
		if(bonus instanceof BonusCityToken){
			((BonusCityToken)bonus).setCities(searchCities(string,bonus.getNumber()));
		}
		if(bonus instanceof BonusGetPermitTile){
			StringTokenizer tokenizer= new StringTokenizer(string," ");
			((BonusGetPermitTile)bonus).setTile(clientModel.findRegion(tokenizer.nextToken()),
												Integer.parseInt(tokenizer.nextToken()));
		}
		if(bonus instanceof BonusTileBonus){
			StringTokenizer tokenizer= new StringTokenizer(string," ");
			((BonusTileBonus)bonus).setNumberTile(Integer.parseInt(tokenizer.nextToken()));
		}
		controller.updateController(new PerformBonus(clientModel.getPlayer(), bonus));
	}

	/**
	 * Search if each city in the string are in the model, and add in cities the ones that are in it
	 * @param string The names of the cities to be searched
	 * @param parameters The number of cities to be searched
	 * @return the list of the city in the string that are in the model too
	 */
	public List<City> searchCities(String string,int parameters){
		List<City> cities=new ArrayList<>();
		StringTokenizer tokenizer= new StringTokenizer(string," ");
		for(int index=0;index<parameters;++index){
			City city=clientModel.findCity(tokenizer.nextToken());
			if(city!=null)
				cities.add(city);
		}
		return cities;
	}
	
}
