package it.polimi.ingsw.cg23.server.view;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.polimi.ingsw.cg23.client.rmi.ClientViewRemote;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.controller.change.Change;
import it.polimi.ingsw.cg23.server.model.Board;

public class RMIServerView extends View implements RMIViewRemote {

	private ClientViewRemote clientStub;
	private static Logger logger;
	
	public RMIServerView(ClientViewRemote clientStub,Board model) {
		this.clientStub=clientStub;
		logger = Logger.getLogger(RMIServerView.class);
		PropertyConfigurator.configure("src/main/resources/logger.properties");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Change change) {
		logger.error("Sending to the client " + change);
		try {
			this.clientStub.updateClient(change);
		} catch (RemoteException e) {
			logger.error(e);
		}
	}
	
	public void eseguiAzione(Action action) throws RemoteException{
		action.setLogger(Logger.getLogger(Action.class));
		action.setPlayer(this);
		logger.error("VIEW: received the action " + action);
		action.registerObserver(this);
		this.notifyObserver(action);
	}

	@Override
	public String registerClient(ClientViewRemote clientStub) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
