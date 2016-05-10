package it.polimi.ingsw.cg23.model.action;

import it.polimi.ingsw.cg23.model.Board;
import it.polimi.ingsw.cg23.model.Player;

@FunctionalInterface
public interface Action {
	public void runAction(Player player, Board board);
}
