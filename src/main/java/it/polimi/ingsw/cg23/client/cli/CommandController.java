package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.CreationGame;
import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.controller.action.SendMessage;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumKing;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumTile;
import it.polimi.ingsw.cg23.server.model.action.BuyPermitTile;
import it.polimi.ingsw.cg23.server.model.action.ChangeBusinessPermit;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillor;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillorAssistant;
import it.polimi.ingsw.cg23.server.model.action.HireAssistant;
import it.polimi.ingsw.cg23.server.model.action.MarketBuy;
import it.polimi.ingsw.cg23.server.model.action.MarketSell;
import it.polimi.ingsw.cg23.server.model.components.AssistantsPool;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;
import it.polimi.ingsw.cg23.utility.Print;

/**
 * This class elaborates the command line input.
 * @author Andrea
 *
 */
public class CommandController {

	private ControllerCLI controller;
	private Print cli;
	private ClientModel clientModel;
	private Logger logger;

	/**
	 * The constructor of CommandController
	 * @param controller The main controller of the client.
	 * @param cli The Print class that prints to video.
	 */
	public CommandController(ControllerCLI controller,Print cli) {
		this.controller=controller;
		this.cli=cli;
		logger = Logger.getLogger(CommandController.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	/**
	 * Notifies the controller with a string.
	 * @param string The string to be communicated
	 * @throws IOException If the connection has problems.
	 */
	public void updateCommand(String string) throws IOException{
		clientModel=controller.getModel();
		try{
			commandControl(string);
		}catch(NoSuchElementException | NullPointerException e){
			logger.error("Wrong command.");
		}
	}
	
	/**
	 * Manages the command string.
	 * @param string The string that contains the commands
	 * @throws IOException If the connection has problems
	 * @throws NoSuchElementException if the string doesn't contain many parameters..
	 */
	private void commandControl(String string) throws IOException,NoSuchElementException{
		StringTokenizer tokenizer = new StringTokenizer(string, " ");
		String inputLine = tokenizer.nextToken();
		switch (inputLine) {
		case "CREATION":
			controller.updateController(new CreationGame(tokenizer.nextToken(),tokenizer.nextToken()));
			break;
		case "SHOW":
			showCommand(tokenizer);
			break;
		case "MARKET":
			marketCommand(tokenizer);
			break;
		default:
			mainCommand(string);
			break;
		}
	}

	/**
	 * Manages the string if contains a market buy command.
	 * @param string The string that contains the commands
	 * @throws IOException If the connection has problems.
	 * @throws NoSuchElementException if the string doesn't contain many parameters.
	 */
	private void createActionSell(StringTokenizer tokenizer) throws IOException{
		Action action;
		switch(tokenizer.nextToken()){
		case "TILE":
			action = new MarketSell(clientModel.findPlayerTile(tokenizer.nextToken()),
					Integer.parseInt(tokenizer.nextToken()));
			controller.updateController(action);
			break;
		case "CARD":
			action = new MarketSell(clientModel.findPoliticCard(tokenizer.nextToken()),
					Integer.parseInt(tokenizer.nextToken()));
			break;
		case "ASSISTANTS":
			AssistantsPool pool=new AssistantsPool();
			try {
				pool.setAssistants(Integer.parseInt(tokenizer.nextToken()));
			} catch (NumberFormatException | NegativeNumberException e){
				return;
			}
			action = new MarketSell(pool,
					Integer.parseInt(tokenizer.nextToken()));
			controller.updateController(action);
			break;
		default:
			cli.print("", "Command not found.");
			break;
		}
	}

	/**
	 * Manages the string if contains a market command.
	 * @param string The string that contains the commands
	 * @throws IOException If the connection has problems.
	 * @throws NoSuchElementException if the string doesn't contain many parameters.
	 */
	private void marketCommand(StringTokenizer tokenizer) throws IOException{
		Board model=clientModel.getModel();
		if(model==null){
			cli.print("", "Command refused.");
			return;
		}
		switch(tokenizer.nextToken()){
		case "BUY":
			Action action = new MarketBuy(clientModel.findItem(tokenizer.nextToken()));
			controller.updateController(action);
			break;
		case "SELL":
			createActionSell(tokenizer);
			break;
		case "VIEW":
			cli.print("","Items for sale:");
			cli.printList(model.getMarket().getItems());
			break;
		default:
			cli.print("", "Command not found.");
			break;
		}
	}

	/**
	 * Manages the string if contains a show command.
	 * @param tokizer The string that contains the commands.
	 * @throws NoSuchElementException if the string doesn't contain many parameters.
	 */
	private void showCommand(StringTokenizer tokenizer){
		Board model=clientModel.getModel();
		if(model==null){
			cli.print("", "Command refused.");
			return;
		}
		switch(tokenizer.nextToken()){
		case "BOARD":
			cli.createMap(model);
			break;
		case "HAND":
			cli.printList(clientModel.getPlayer().getHand());
			break;
		case "TILES":
			cli.print("","Available tiles:");
			cli.printList(clientModel.getPlayer().getAvailableBusinessPermits());
			cli.print("","Used tiles:");
			cli.printList(clientModel.getPlayer().getUsedBusinessPermit());
			break;
		case "NEIGHBORS":
			cli.print("","City's neighbors:");
			cli.printList(clientModel.findCity(tokenizer.nextToken()).getNeighbors());
			break;
		default:
			cli.print("", "Command not found.");
			break;
		}
	}
	
	/**
	 * Manages the string if contains a main command.
	 * @param string The string that contains the commands
	 * @throws IOException If the connection has problems. 
	 * @throws NoSuchElementException if the string doesn't contain many parameters.
	 */
	private void mainCommand(String string) throws IOException{
		String tok=null;
		StringTokenizer tokenizer = new StringTokenizer(string, " ");
		Action action;
		switch(tokenizer.nextToken()){
		case "ADDITIONAL":
			action = new AdditionalAction();
			controller.updateController(action);
			break;
		case "BUILDKING":
			action = new BuildEmporiumKing(clientModel.getPlayer().getHand(),
					clientModel.findCity(tokenizer.nextToken()));
			controller.updateController(action);
			break;
		case "BUILDTILE":
			action = new BuildEmporiumTile(clientModel.findPlayerTile(tokenizer.nextToken()),
					clientModel.findCity(tokenizer.nextToken()));
			controller.updateController(action);
			break;
		case "BUYTILE":
			Region region=clientModel.findRegion(tokenizer.nextToken());
			action = new BuyPermitTile(clientModel.getPlayer().getHand(),
					region,
					clientModel.findRegionTile(tokenizer.nextToken(), region));
			controller.updateController(action);
			break;
		case "CHANGE":
			action = new ChangeBusinessPermit(clientModel.findRegion(tokenizer.nextToken()));
			controller.updateController(action);
			break;
		case "ELECT":
			tok=tokenizer.nextToken();
			if("KING".equals(tok))
				action = new ElectCouncillor(clientModel.findColor(tokenizer.nextToken()),null, true);
			else
				action = new ElectCouncillorAssistant(clientModel.findColor(tokenizer.nextToken()),
						clientModel.findRegion(tok), false);
			controller.updateController(action);
			break;
		case "ELECTASSISTANT":
			tok=tokenizer.nextToken();
			if("KING".equals(tok))
				action = new ElectCouncillorAssistant(clientModel.findColor(tokenizer.nextToken()),
						null, true);
			else
				action = new ElectCouncillorAssistant(clientModel.findColor(tokenizer.nextToken()),
						clientModel.findRegion(tok), false);
			controller.updateController(action);
			break;
		case "HIRE":
			action = new HireAssistant();
			controller.updateController(action);
			break;
		case "ENDTURN":
			action = new EndTurn();
			controller.updateController(action);
			break;
		default:
			controller.updateController(new SendMessage(string, clientModel.getPlayer()));
			break;
		}
	}

}