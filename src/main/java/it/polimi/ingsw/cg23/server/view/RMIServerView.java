package it.polimi.ingsw.cg23.server.view;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;

/**
 * The personal server's view of the player whom use RMI connection.
 * @author Andrea
 */
public class RMIServerView extends View implements RMIViewRemote {

	private ClientViewRemote clientStub;
	private String nameView;
	
	/**
	 * The constructor of the server view.
	 * @param clientStub the stub of the player.
	 * @param model the model of the game.
	 * @param nameView the name for lookup the view.
	 */
	public RMIServerView(ClientViewRemote clientStub,Board model,String nameView) {
		this.clientStub=clientStub;
		this.nameView=nameView;
	}

	/**
	 * Updates the client stub with a change.
	 * @param change the change for the client.
	 */
	@Override
	public void update(Change change) {
		getLogger().info("Sending to the client " + change);
		try {
			this.clientStub.updateClient(change);
		} catch (RemoteException e) {
			getLogger().info(e);
		}
	}
	
	/**
	 * Notify the controller with the incoming action.
	 * @param action the action to run.
	 * @throws RemoteException if RMI connection has problems.
	 */
	@Override
	public void performAction(Action action) throws RemoteException{
		if(action==null)
			return;
		action.setLogger(Logger.getLogger(Action.class));
		action.setPlayer(this);
		getLogger().info("VIEW: received the action " + action);
		action.registerObserver(this);
		this.notifyObserver(action);
	}
	
	/**
	 * Returns the name for lookup the view.
	 * @param clientStub the stub of the client.
	 * @throws RemoteException if RMI connection has problems.
	 */
	@Override
	public String registerClient(ClientViewRemote clientStub) throws RemoteException {
		return nameView;
	}
}
