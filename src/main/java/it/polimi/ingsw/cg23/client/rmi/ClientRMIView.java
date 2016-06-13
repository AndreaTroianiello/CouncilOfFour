package it.polimi.ingsw.cg23.client.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.cg23.client.ClientController;
import it.polimi.ingsw.cg23.server.controller.change.Change;

/**
 * The ClientRMIView manages the objects received by the client,whom uses a RMI connection.
 * @author Andrea
 *
 */
public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote {

	private static final long serialVersionUID = 1191452922375955484L;
	private transient ClientController controller;
	
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
	 * Updates the client's controller with the incoming change.
	 * @throws RemoteException if the RMI connection has problems.	
	 */
	@Override
	public void  updateClient(Change change) throws RemoteException{
		if(change!=null)
			controller.updateController(change);
	}
}

