package it.polimi.ingsw.cg23.client.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import it.polimi.ingsw.cg23.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.model.action.BuildEmporiumKing;
import it.polimi.ingsw.cg23.model.action.BuildEmporiumTile;
import it.polimi.ingsw.cg23.model.action.BuyPermitTile;
import it.polimi.ingsw.cg23.model.action.ChangeBusinessPermit;
import it.polimi.ingsw.cg23.model.action.ElectCouncillor;
import it.polimi.ingsw.cg23.model.action.ElectCouncillorAssistant;
import it.polimi.ingsw.cg23.model.action.GameAction;
import it.polimi.ingsw.cg23.model.action.HireAssistant;


public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;

	public ClientOutHandler(ObjectOutputStream socketOut) {
		this.socketOut = socketOut;
	}

	@Override
	public void run() {

		System.out.println("RUNNING");
		Scanner stdIn = new Scanner(System.in);

		while (true) {

			String inputLine = stdIn.nextLine();

			GameAction action;
			try{
				//DA COMPLETARE GLI ARGOMENTI PASSATI ALLE AZIONI
				switch (inputLine) {
				case "ADDITIONAL":
					action = new AdditionalAction();
					socketOut.writeObject(action);
					socketOut.flush();
					break;
				case "BUILDKING":
					action = new BuildEmporiumKing(null, null);
					socketOut.writeObject(action);
					socketOut.flush();
					break;
				case "BUILDTILE":
					action = new BuildEmporiumTile(null, 0);
					socketOut.writeObject(action);
					socketOut.flush();
					break;
				case "BUYTILE":
					action = new BuyPermitTile(null, null, null);
					socketOut.writeObject(action);
					socketOut.flush();
					break;
				case "CHANGE":
					action = new ChangeBusinessPermit(0);
					socketOut.writeObject(action);
					socketOut.flush();
					break;
				case "ELECT":
					action = new ElectCouncillor(null, null, true);
					socketOut.writeObject(action);
					socketOut.flush();
					break;
				case "ELECTASSISTANT":
					action = new ElectCouncillorAssistant(null, null, true);
					socketOut.writeObject(action);
					socketOut.flush();
					break;
				case "HIRE":
					action = new HireAssistant();
					socketOut.writeObject(action);
					socketOut.flush();
					break;
				default:
					break;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
}

