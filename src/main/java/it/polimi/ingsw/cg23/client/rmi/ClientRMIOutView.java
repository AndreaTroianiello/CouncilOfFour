package it.polimi.ingsw.cg23.client.rmi;

import java.io.IOException;

import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.view.RMIViewRemote;

/**
 * The ClientOutHandler manages the objects  to send at the server, that uses RMI connection.
 * @author Andrea
 *
 */
public class ClientRMIOutView implements ClientViewOut {

	private RMIViewRemote rmiServerView;

	/**
	 * The constructor of ClientRMIOutView.
	 * @param rmiServerView The server' stub.
	 */
	public ClientRMIOutView(RMIViewRemote rmiServerView) {
		this.rmiServerView=rmiServerView;
	}

	/**
	 * Sends the action to the server.
	 * @param action The action to send.
	 * @throws IOException if the RMI connection has problems.	
	 */
	@Override
	public void update(Action action) throws IOException{
		rmiServerView.performAction(action);
	}

}
