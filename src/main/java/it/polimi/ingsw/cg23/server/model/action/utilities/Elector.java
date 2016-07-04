package it.polimi.ingsw.cg23.server.model.action.utilities;

import java.io.Serializable;

import it.polimi.ingsw.cg23.server.controller.change.CouncilChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.ControlAction;
import it.polimi.ingsw.cg23.server.model.components.Councillor;

/**
 * The class used for the election of the councillors. It contains only the
 * controlAction.
 * 
 * @author Vincenzo
 *
 */
public class Elector implements Serializable {

	private static final long serialVersionUID = -8795937435876632345L;
	private ControlAction controlAction;

	/**
	 * the constructor set the variable controlAction
	 * 
	 * @param controlAction
	 */
	public Elector(ControlAction controlAction) {
		this.controlAction = controlAction;
	}

	/**
	 * remove the first councillor from the chosen council and append the new
	 * one
	 * 
	 * @param councillor
	 * @param board
	 * @param region
	 * @param king
	 */
	public void election(Councillor councillor, Board board, Region region, boolean king) {
		if (!king) {
			Region realRegion = controlAction.controlRegion(region, board);
			if (realRegion != null) {
				Councillor oldCouncillor = realRegion.getCouncil().getCouncillors().remove(0); // remove
																								// the
																								// first
																								// councillor
																								// in
																								// the
																								// chosen
																								// council
				board.setCouncillor(oldCouncillor);
				realRegion.getCouncil().getCouncillors().add(councillor); // append
																			// the
																			// chosen
																			// councillor
																			// in
																			// the
																			// same
																			// council
				board.notifyObserver(new CouncilChange(realRegion.getCouncil()));
			}
		} else {
			Councillor oldCouncillor = board.getKing().getCouncil().getCouncillors().remove(0); // remove
																								// the
																								// first
																								// councillor
																								// in
																								// the
																								// chosen
																								// council
			board.setCouncillor(oldCouncillor);
			board.getKing().getCouncil().getCouncillors().add(councillor); // append
																			// the
																			// chosen
																			// councillor
																			// in
																			// the
																			// same
																			// council
			board.notifyObserver(new CouncilChange(board.getKing().getCouncil()));
		}
	}
}
