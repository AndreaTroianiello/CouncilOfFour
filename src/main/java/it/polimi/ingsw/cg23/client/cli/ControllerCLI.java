package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.CreationPlayer;
import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumKing;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumTile;
import it.polimi.ingsw.cg23.server.model.action.BuyPermitTile;
import it.polimi.ingsw.cg23.server.model.action.ChangeBusinessPermit;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillor;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillorAssistant;
import it.polimi.ingsw.cg23.server.model.action.HireAssistant;

public class ControllerCLI implements ClientController{

	private static Logger logger;
	private ClientModel clientModel;
	private ClientViewOut out;

	public ControllerCLI(){
		this.out=null;
		logger= Logger.getLogger(ControllerCLI.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}
	
	@Override
	public void setOutView(ClientViewOut out){
		this.out=out;
	}
	@Override
	public void updateController(String string) throws IOException{
		StringTokenizer tokenizer = new StringTokenizer(string, " ");
		String inputLine = tokenizer.nextToken();
			switch (inputLine) {
			case "CREATION":
				out.update(new CreationPlayer(tokenizer.nextToken()));
				break;
			default:
				mainCommand(tokenizer);
				break;
		}
	}
	
	private void mainCommand(StringTokenizer tokenizer) throws IOException{
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
			action = new ElectCouncillor(clientModel.findColor(tokenizer.nextToken()),
										 clientModel.findRegion(tokenizer.nextToken()), true);
			out.update(action);
			break;
		case "ELECTASSISTANT":
			String tok=tokenizer.nextToken();
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
			logger.info("Command not found.");
			break;
		}
	}

	@Override
	public void updateController(Change change) {
		if(change instanceof PlayerChange && clientModel.getModel()==null){
			clientModel.setPlayer(((PlayerChange)change).getPlayer());
			return;
		}
		if(change instanceof BoardChange)
			clientModel.setModel(((BoardChange)change).getBoard());
		else
			logger.info(change);
	}
}
