package it.polimi.ingsw.cg23.server.view.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.Chat;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.SendMessage;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.controller.change.RankChange;
import it.polimi.ingsw.cg23.server.view.View;

/**
 * The personal server's view of the player whom use RMI connection.
 * 
 * @author Andrea
 */
public class RMIServerView extends View implements RMIViewRemote {

	private ClientViewRemote clientStub;
	private String nameView;
	private Chat chat;
	private Registry registry;

	/**
	 * The constructor of the server view.
	 * 
	 * @param clientStub
	 *            the stub of the player.
	 * @param nameView
	 *            the name for lookup the view.
	 * @param registry
	 * 				the registry
	 * @param chat
	 * 			the game chat
	 */
	public RMIServerView(ClientViewRemote clientStub, String nameView, Registry registry, Chat chat) {
		this.chat = chat;
		this.chat.addView(this);
		this.clientStub = clientStub;
		this.nameView = nameView;
		this.registry = registry;
	}

	/**
	 * Updates the client stub with a change.
	 * 
	 * @param change
	 *            the change for the client.
	 */
	@Override
	public void update(Change change) {
		getLogger().info("Sending to the client " + change);
		try {
			this.clientStub.updateClient(change);
			if (change instanceof RankChange) {
				this.close();
			}
		} catch (RemoteException e) {
			getLogger().info(e);
			setSuspended(true);
		}
	}

	/**
	 * Notify the controller with the incoming action.
	 * 
	 * @param action
	 *            the action to run.
	 * @throws RemoteException
	 *             if RMI connection has problems.
	 */
	@Override
	public void performAction(Action action) throws RemoteException {
		if (action == null)
			return;
		setSuspended(false);
		action.setLogger(Logger.getLogger(Action.class));
		action.setPlayer(this);
		if (action instanceof SendMessage) {
			chat.update((SendMessage) action);
		} else {
			getLogger().info("VIEW: received the action " + action);
			action.registerObserver(this);
			this.notifyObserver(action);
		}
	}

	/**
	 * Returns the name for lookup the view.
	 * 
	 * @param clientStub
	 *            the stub of the client.
	 * @throws RemoteException
	 *             if RMI connection has problems.
	 */
	@Override
	public String registerClient(ClientViewRemote clientStub) throws RemoteException {
		return nameView;
	}

	/**
	 * Close the RMI connection on the server.
	 */
	@Override
	public void close() {
		try {
			registry.unbind(nameView);
			clientStub.close();
			clientStub = null;
			chat.resetViews();
			chat = null;
		} catch (RemoteException | NotBoundException e) {
			getLogger().error(e);
		}
	}
}
