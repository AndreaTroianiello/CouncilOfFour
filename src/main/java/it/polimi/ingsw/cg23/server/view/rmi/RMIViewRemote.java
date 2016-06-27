package it.polimi.ingsw.cg23.server.view.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.controller.action.Action;

/**
 * The RMI view interface of the server.
 * @author Andrea
 *
 */
public interface RMIViewRemote extends Remote {
	/**
	 * Returns the name for lookup the view.
	 * @param clientStub the stub of the client.
	 * @throws RemoteException if RMI connection has problems.
	 */
	public String registerClient(ClientViewRemote clientStub) throws RemoteException;
	
	/**
	 * Notify the controller with the incoming action.
	 * @param action the action to run.
	 * @throws RemoteException if RMI connection has problems.
	 */
	public void performAction(Action action) throws RemoteException;
}
