package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.ClientModel;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.CreationPlayer;
import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumKing;
import it.polimi.ingsw.cg23.server.model.action.BuildEmporiumTile;
import it.polimi.ingsw.cg23.server.model.action.BuyPermitTile;
import it.polimi.ingsw.cg23.server.model.action.ChangeBusinessPermit;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillor;
import it.polimi.ingsw.cg23.server.model.action.ElectCouncillorAssistant;
import it.polimi.ingsw.cg23.server.model.action.HireAssistant;


public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private final ClientModel clientModel;
	private static Logger logger;

	public ClientOutHandler(ClientModel clientModel,ObjectOutputStream socketOut) {
		logger = Logger.getLogger(ClientOutHandler.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.socketOut = socketOut;
		this.clientModel=clientModel;
	}

	public void update(Action action) throws IOException{
		socketOut.writeObject(action);
		socketOut.flush();
		socketOut.reset();
	}
	
	@SuppressWarnings("resource")
	@Override
	public void run() {
		boolean run=true;

		logger.info("RUNNING");
		Scanner stdIn = new Scanner(System.in);

		while (run) {

			StringTokenizer tokenizer = new StringTokenizer(stdIn.nextLine(), " ");
			String inputLine = tokenizer.nextToken();

			Action action;
			try{
				//DA COMPLETARE GLI ARGOMENTI PASSATI ALLE AZIONI!!
				switch (inputLine) {
				case "CREATION":
					action = new CreationPlayer(tokenizer.nextToken());
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "ADDITIONAL":
					action = new AdditionalAction();
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "BUILDKING":
					action = new BuildEmporiumKing(new ArrayList<>(), clientModel.findCity(tokenizer.nextToken()));
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "BUILDTILE":
					action = new BuildEmporiumTile(null, null);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "BUYTILE":
					action = new BuyPermitTile(null, clientModel.findRegion(tokenizer.nextToken()), null);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "CHANGE":
					action = new ChangeBusinessPermit(null);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "ELECT":
					action = new ElectCouncillor(null, clientModel.findRegion(tokenizer.nextToken()), true);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "ELECTASSISTANT":
					String tok=tokenizer.nextToken();
					if("KING".equals(tok))
						action = new ElectCouncillorAssistant(null, null, true);
					else
						action = new ElectCouncillorAssistant(null, clientModel.findRegion(tok), false);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "HIRE":
					action = new HireAssistant();
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "ENDTURN":
					action = new EndTurn();
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				default:
					break;
				}
			} catch (IOException e) {
				logger.error(e);
				run=false;
			}

		}
	}
}

