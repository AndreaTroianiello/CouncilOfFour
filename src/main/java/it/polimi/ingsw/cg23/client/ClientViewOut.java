package it.polimi.ingsw.cg23.client;

import java.io.IOException;

import it.polimi.ingsw.cg23.server.controller.action.Action;

/**
 * The ClientViewOut manages the objects  to send at the server.
 * @author Andrea
 *
 */
@FunctionalInterface
public interface ClientViewOut {
	
	/**
	 * Sends the action to the server.
	 * @param action The action to send.
	 * @throws IOException if the connection has problems.	
	 */
	public void update(Action action) throws IOException;
}
