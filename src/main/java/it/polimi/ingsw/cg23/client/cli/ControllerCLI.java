package it.polimi.ingsw.cg23.client.cli;

import java.io.IOException;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.CreationPlayer;
import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
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
import it.polimi.ingsw.cg23.server.view.Print;

/**
 * controller cli
 */
public class ControllerCLI implements ClientController{

	private ClientModel clientModel;
	private ClientViewOut out;
	private Print cli;

	public ControllerCLI(){
		this.out=null;
		this.clientModel=new ClientModel();
		this.cli=new Print();
	}
	
	@Override
	public void setOutView(ClientViewOut out){
		this.out=out;
	}
	
	public void updateController(String string) throws IOException{
		StringTokenizer tokenizer = new StringTokenizer(string, " ");
		String inputLine = tokenizer.nextToken();
			switch (inputLine) {
			case "CREATION":
				out.update(new CreationPlayer(tokenizer.nextToken()));
				break;
			case "SHOW":
				showCommand(tokenizer);
				break;
			default:
				mainCommand(string);
				break;
		}
	}
	private void showCommand(StringTokenizer tokenizer){
		Board model=clientModel.getModel();
		if(model==null){
			cli.print("", "Command refused.");
			return;
		}
		switch(tokenizer.nextToken()){
		case "BOARD":
			cli.createMap(model);
			cli.print(model.getNobilityTrack(),"");
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
		default:
			cli.print("", "Command not found.");
			break;
		}
	}
	private void mainCommand(String string) throws IOException{
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
			cli.print("", "Command not found.");
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
			cli.print(change,"");
	}
}
