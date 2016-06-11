package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.util.ArrayList;
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
		Action action;
			//DA COMPLETARE GLI ARGOMENTI PASSATI ALLE AZIONI!!
			switch (inputLine) {
			case "CREATION":
				action = new CreationPlayer(tokenizer.nextToken());
				out.update(action);
				break;
			case "ADDITIONAL":
				action = new AdditionalAction();
				out.update(action);
				break;
			case "BUILDKING":
				action = new BuildEmporiumKing(new ArrayList<>(), clientModel.findCity(tokenizer.nextToken()));
				out.update(action);
				break;
			case "BUILDTILE":
				action = new BuildEmporiumTile(null, null);
				out.update(action);
				break;
			case "BUYTILE":
				action = new BuyPermitTile(null, clientModel.findRegion(tokenizer.nextToken()), null);
				out.update(action);
				break;
			case "CHANGE":
				action = new ChangeBusinessPermit(null);
				out.update(action);
				break;
			case "ELECT":
				action = new ElectCouncillor(null, clientModel.findRegion(tokenizer.nextToken()), true);
				out.update(action);
				break;
			case "ELECTASSISTANT":
				String tok=tokenizer.nextToken();
				if("KING".equals(tok))
					action = new ElectCouncillorAssistant(null, null, true);
				else
					action = new ElectCouncillorAssistant(null, clientModel.findRegion(tok), false);
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
				logger.info("Control not found.");
				break;
		}
	}

	@Override
	public void updateController(Change change) {
		if(change instanceof BoardChange)
			clientModel.setModel(((BoardChange)change).getBoard());
		else
			logger.info(change);
	}
}
