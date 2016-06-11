package it.polimi.ingsw.cg23.client.rmi;

import java.io.IOException;

import it.polimi.ingsw.cg23.client.ClientViewOut;
import it.polimi.ingsw.cg23.server.controller.action.Action;
import it.polimi.ingsw.cg23.server.view.RMIViewRemote;

public class ClientRMIOutView implements ClientViewOut {

	private RMIViewRemote rmiServerView;

	public ClientRMIOutView(RMIViewRemote rmiServerView) {
		this.rmiServerView=rmiServerView;
	}

	@Override
	public void update(Action action) throws IOException{
		rmiServerView.performAction(action);
	}

}
