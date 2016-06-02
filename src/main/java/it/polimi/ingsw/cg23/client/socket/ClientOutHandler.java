package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.controller.action.Action;
import it.polimi.ingsw.cg23.controller.action.EndTurn;
import it.polimi.ingsw.cg23.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.model.action.BuildEmporiumKing;
import it.polimi.ingsw.cg23.model.action.BuildEmporiumTile;
import it.polimi.ingsw.cg23.model.action.BuyPermitTile;
import it.polimi.ingsw.cg23.model.action.ChangeBusinessPermit;
import it.polimi.ingsw.cg23.model.action.ElectCouncillor;
import it.polimi.ingsw.cg23.model.action.ElectCouncillorAssistant;
import it.polimi.ingsw.cg23.model.action.HireAssistant;


public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	
	private static Logger logger;

	public ClientOutHandler(ObjectOutputStream socketOut) {
		logger = Logger.getLogger(ClientOutHandler.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
		this.socketOut = socketOut;
	}

	@Override
	public void run() {
		boolean run=true;
		
		System.out.println("RUNNING");
		Scanner stdIn = new Scanner(System.in);
		
		while (run) {

			String inputLine = stdIn.nextLine();

			Action action;
			try{
				//DA COMPLETARE GLI ARGOMENTI PASSATI ALLE AZIONI!!
				switch (inputLine) {
				case "ADDITIONAL":
					action = new AdditionalAction();
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "BUILDKING":
					action = new BuildEmporiumKing(null, null);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "BUILDTILE":
					action = new BuildEmporiumTile(null, 0);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "BUYTILE":
					action = new BuyPermitTile(null, null, null);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "CHANGE":
					action = new ChangeBusinessPermit(0);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "ELECT":
					action = new ElectCouncillor(null, null, true);
					socketOut.writeObject(action);
					socketOut.flush();
					socketOut.reset();
					break;
				case "ELECTASSISTANT":
					action = new ElectCouncillorAssistant(null, null, true);
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

