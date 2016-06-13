package it.polimi.ingsw.cg23.client;


import it.polimi.ingsw.cg23.server.controller.change.Change;

/**
 * The ClientController manages the flow of client-server information.
 * @author Andrea
 *
 */
public interface ClientController {
	public void updateController(Change change);
	public void setOutView(ClientViewOut out);
}
