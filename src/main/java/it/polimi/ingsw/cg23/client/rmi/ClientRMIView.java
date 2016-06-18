package it.polimi.ingsw.cg23.client.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.view.RMIViewRemote;

/**
 * The ClientRMIView manages the objects received by the client,whom uses a RMI connection.
 * @author Andrea
 *
 */
public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote,ClientViewOut{

	private static final long serialVersionUID = 1191452922375955484L;
	private transient ClientController controller;
	private RMIViewRemote rmiServerView;
	
	/**
	 * The constructor of ClientRMIView.
	 * @param controller The game's controller.
	 * @throws RemoteException if the RMI connection has problems.	
	 */
	public ClientRMIView(ClientController controller) throws RemoteException {
		super();
		this.controller=controller;
	}
	
	/**
	 * Sets the Client's RMI view on the server.
	 * @param rmiServerView The server' stub.
	 */
	public void setRMIServerView(RMIViewRemote rmiServerView) {
		this.rmiServerView=rmiServerView;
		
	}
	
	/**
	 * Updates the client's controller with the incoming change.
	 * @throws RemoteException if the RMI connection has problems.	
	 */
	@Override
	public void  updateClient(Change change) throws RemoteException{
		if(change!=null)
			controller.updateController(change);
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

	/**
	 * Close RMI connection on the client.
	 */
	@Override
	public void close() throws RemoteException {
		rmiServerView=null;		
	}
}

