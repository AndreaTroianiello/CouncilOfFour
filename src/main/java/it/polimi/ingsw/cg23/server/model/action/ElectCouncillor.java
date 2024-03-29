package it.polimi.ingsw.cg23.server.model.action;

import java.awt.Color;

import it.polimi.ingsw.cg23.server.controller.change.BoardChange;
import it.polimi.ingsw.cg23.server.controller.change.PlayerChange;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.Region;
import it.polimi.ingsw.cg23.server.model.action.utilities.Elector;
import it.polimi.ingsw.cg23.server.model.components.Councillor;
import it.polimi.ingsw.cg23.server.model.exception.NegativeNumberException;

/**
 * the class of the action that allows to elect a new councillor in a region. It
 * contains the color of the chosen councillor, the chosen region, a boolean
 * that shows if it is a main action, and a boolean that shows if the player
 * chooses the king's council
 *
 * @author Vincenzo
 */
public class ElectCouncillor extends GameAction implements StandardAction {

	private static final long serialVersionUID = -2461481103395662345L;
	private final Color councillor;
	private final Region region; // wich region the player choose
	private final boolean king;
	private final ControlAction controlAction;
	private final Elector elector;

	/**
	 * the constructor set the variables of the class: it set the main to true,
	 * and the other variables as the parameter given to the method
	 * 
	 * @param councillor
	 *            the color of the councillor to be elected
	 * @param region
	 *            the region of the election
	 * @param king
	 *            if the election is for the king's council
	 * @throws NullPointerException
	 *             if the councillor is null, and if the region is null and the
	 *             king is false.
	 */
	public ElectCouncillor(Color councillor, Region region, boolean king) {
		super(true);
		if (councillor != null && (region != null || king)) {
			this.councillor = councillor;
			this.region = region;
			this.king = king;
		} else
			throw new NullPointerException();
		this.controlAction = new ControlAction();
		this.elector = new Elector(controlAction);
	}

	/**
	 * @return the king
	 */
	public boolean isKing() {
		return king;
	}

	/**
	 * @return the councillor
	 */
	public Color getCouncillor() {
		return councillor;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * elects a new councillor and update the player's richness
	 * 
	 * @param player
	 *            who runs the action
	 * @param board
	 *            the model of the game
	 * 
	 * @return true if the action is successful, false otherwise
	 */
	@Override
	public boolean runAction(Player player, Board board) {
		Councillor newCouncillor = board.getCouncillor(councillor);
		if (newCouncillor != null) {
			this.elector.election(newCouncillor, board, this.region, this.king);
			int coins = player.getRichness().getCoins();
			coins = coins + 4;
			try {
				player.getRichness().setCoins(coins);
				this.notifyObserver(new PlayerChange(player));
				board.notifyObserver(new BoardChange(board));
			} catch (NegativeNumberException e) {
				getLogger().error(e);
			}
			return true;
		}
		return false;
	}

	/**
	 * @return the name and the variables of the class in string
	 */
	@Override
	public String toString() {
		return "ElectCouncillor [councillor=" + councillor + ", region=" + region + ", king=" + king + "]";
	}

}
