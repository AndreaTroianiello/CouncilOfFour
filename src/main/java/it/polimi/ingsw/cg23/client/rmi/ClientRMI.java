package it.polimi.ingsw.cg23.client.rmi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.CreationPlayer;
import it.polimi.ingsw.cg23.server.controller.action.EndTurn;
import it.polimi.ingsw.cg23.server.view.RMIServerView;
import it.polimi.ingsw.cg23.server.view.RMIViewRemote;

public class ClientRMI {

	private final static int RMI_PORT=52365;
	private final static String HOST="127.0.0.1";
	private static final String NAME="council";
	
	public void startClient() throws RemoteException, NotBoundException {
		Registry registry=LocateRegistry.getRegistry(HOST,RMI_PORT);
		RMIViewRemote serverStub=(RMIViewRemote) registry.lookup(NAME);
		ClientRMIView rmiView=new ClientRMIView();
		RMIViewRemote rmiServerView=(RMIViewRemote) registry.lookup(serverStub.registerClient(rmiView));	
		Scanner stdIn=new Scanner(System.in);	
		while (true) {

			StringTokenizer tokenizer = new StringTokenizer(stdIn.nextLine(), " ");
			String inputLine = tokenizer.nextToken();

			Action action;
			try {

				switch (inputLine) {
				case "CREATION":
					action = new CreationPlayer(tokenizer.nextToken());
					rmiServerView.eseguiAzione(action);
					break;
				case "ENDTURN":
					action = new EndTurn();
					rmiServerView.eseguiAzione(action);
					break;
				default:
					break;
				}
			} catch (IOException e1) {

			}
		}
	}

}
