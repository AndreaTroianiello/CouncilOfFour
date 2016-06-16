package it.polimi.ingsw.cg23.server.view;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.Chat;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.action.SendMessage;
import it.polimi.ingsw.cg23.server.controller.change.Change;

/**
 * The personal server's view of the player whom use RMI connection.
 * @author Andrea
 */
public class RMIServerView extends View implements RMIViewRemote {

	private ClientViewRemote clientStub;
	private String nameView;
	private Chat chat;
	
	/**
	 * The constructor of the server view.
	 * @param clientStub the stub of the player.
	 * @param nameView the name for lookup the view.
	 */
	public RMIServerView(ClientViewRemote clientStub,String nameView, Chat chat) {
		this.chat=chat;
		this.chat.addView(this);
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
		setSuspended(false);
		action.setLogger(Logger.getLogger(Action.class));
		action.setPlayer(this);
		if(action instanceof SendMessage){
			chat.update((SendMessage)action);
		}else{
			getLogger().info("VIEW: received the action " + action);
			action.registerObserver(this);
			this.notifyObserver(action);
		}
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
