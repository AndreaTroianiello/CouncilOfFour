package it.polimi.ingsw.cg23.client;

import java.io.IOException;

import it.polimi.ingsw.cg23.server.controller.action.Action;

@FunctionalInterface
public interface ClientViewOut {
	public void update(Action action) throws IOException;
}
