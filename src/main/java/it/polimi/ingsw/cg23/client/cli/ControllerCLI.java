package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.CreationPlayer;
import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.controller.action.SendMessage;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.controller.change.RankChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
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
import it.polimi.ingsw.cg23.utility.CreateMap;
import it.polimi.ingsw.cg23.utility.Print;

/**
 * The client Command Line Interface's controller.
 * @author Andrea
 *
 */
public class ControllerCLI implements ClientController{

	private ClientModel clientModel;
	private ClientViewOut out;
	private Print cli;
	private Logger logger;

	/**
	 * The constructor of ControllerCLI.
	 * @param cli The Print class that prints to video.
	 */
	public ControllerCLI(Print cli){
		this.out=null;
		this.clientModel=new ClientModel();
		this.cli=cli;
		logger = Logger.getLogger(ControllerCLI.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	/**
	 * Sets the out view of the client.
	 * @param out The class that manages the objects to send at the server.
	 */
	@Override
	public void setOutView(ClientViewOut out){
		this.out=out;
	}

	/**
	 * Notifies the controller with a string.
	 * @param string The string to be communicated
	 * @throws IOException If the connection has problems.
	 */
	public void updateController(String string)throws IOException{
		try{
			commandControl(string);
		}catch(NoSuchElementException | NullPointerException e){
			logger.error("Wrong comand.");
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
			out.update(new CreationPlayer(tokenizer.nextToken()));
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
	private void createActionSell(StringTokenizer tokenizer) throws IOException,NoSuchElementException{
		Action action;
		switch(tokenizer.nextToken()){
		case "TILE":
			action = new MarketSell(clientModel.findPlayerTile(tokenizer.nextToken()),
					Integer.parseInt(tokenizer.nextToken()));
			out.update(action);
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
			out.update(action);
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
	private void marketCommand(StringTokenizer tokenizer) throws IOException,NoSuchElementException{
		Board model=clientModel.getModel();
		if(model==null){
			cli.print("", "Command refused.");
			return;
		}
		switch(tokenizer.nextToken()){
		case "BUY":
			Action action = new MarketBuy(clientModel.findItem(tokenizer.nextToken()));
			out.update(action);
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
	private void showCommand(StringTokenizer tokenizer) throws NoSuchElementException{
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
	private void mainCommand(String string) throws IOException,NoSuchElementException{
		String tok=null;
		StringTokenizer tokenizer = new StringTokenizer(string, " ");
		Action action;
		switch(tokenizer.nextToken()){
		case "ADDITIONAL":
			action = new AdditionalAction();
			out.update(action);
			break;
		case "BUILDKING":
			action = new BuildEmporiumKing(clientModel.getPlayer().getHand(),
					clientModel.findCity(tokenizer.nextToken()));
			out.update(action);
			break;
		case "BUILDTILE":
			action = new BuildEmporiumTile(clientModel.findPlayerTile(tokenizer.nextToken()),
					clientModel.findCity(tokenizer.nextToken()));
			out.update(action);
			break;
		case "BUYTILE":
			Region region=clientModel.findRegion(tokenizer.nextToken());
			action = new BuyPermitTile(clientModel.getPlayer().getHand(),
					region,
					clientModel.findRegionTile(tokenizer.nextToken(), region));
			out.update(action);
			break;
		case "CHANGE":
			action = new ChangeBusinessPermit(clientModel.findRegion(tokenizer.nextToken()));
			out.update(action);
			break;
		case "ELECT":
			tok=tokenizer.nextToken();
			if("KING".equals(tok))
				action = new ElectCouncillor(clientModel.findColor(tokenizer.nextToken()),null, true);
			else
				action = new ElectCouncillorAssistant(clientModel.findColor(tokenizer.nextToken()),
						clientModel.findRegion(tok), false);
			out.update(action);
			break;
		case "ELECTASSISTANT":
			tok=tokenizer.nextToken();
			if("KING".equals(tok))
				action = new ElectCouncillorAssistant(clientModel.findColor(tokenizer.nextToken()),
						null, true);
			else
				action = new ElectCouncillorAssistant(clientModel.findColor(tokenizer.nextToken()),
						clientModel.findRegion(tok), false);
			out.update(action);
			break;
		case "HIRE":
			action = new HireAssistant();
			out.update(action);
			break;
		case "ENDTURN":
			action = new EndTurn();
			out.update(action);
			break;
		default:
			out.update(new SendMessage(string, clientModel.getPlayer()));
			break;
		}
	}

	/**
	 * Notifies the controller with a change object.
	 * @param change The object to control.
	 */
	@Override
	public void updateController(Change change) {
		if(change instanceof PlayerChange && clientModel.getModel()==null){
			clientModel.setPlayer(((PlayerChange)change).getPlayer());
			return;
		}
		if(change instanceof RankChange){
			cli.print(new CreateMap().createPlayerInfo(((RankChange)change).getRank()),"");
			return;
		}
		if(change instanceof BoardChange){
			Board model=((BoardChange)change).getBoard();
			List<Player> players=model.getPlayers();
			for(Player player: players)
				if(clientModel.getPlayer().getUser().equals(player.getUser()))
					clientModel.setPlayer(player);
			clientModel.setModel(model);
		}
		else
			cli.print(change,"");
	}
	
	@Override
	public ClientModel getModel() {
		return clientModel;
	}
}
