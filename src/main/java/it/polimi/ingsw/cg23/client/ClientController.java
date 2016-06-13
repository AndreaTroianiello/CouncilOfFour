package it.polimi.ingsw.cg23.client;


import it.polimi.ingsw.cg23.server.controller.change.Change;

public interface ClientController {
	public void updateController(Change change);
	public void setOutView(ClientViewOut out);
}
