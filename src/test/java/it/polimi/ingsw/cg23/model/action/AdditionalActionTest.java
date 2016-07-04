package it.polimi.ingsw.cg23.model.action;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.action.AdditionalAction;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;

public class AdditionalActionTest {

	private Player player;
	private Board board;

	@Before
	public void setUp() {
		player = new Player("player1", new NobilityTrack(3));
		board = new Board(null, null, null, null, null, null);
	}

	@Test
	public void ifAdditionalActionIsFalseItShouldBeChangedToTrue() {
		if (this.player.isAdditionalAction())
			this.player.switchAdditionalAction();
		AdditionalAction action = new AdditionalAction();
		action.runAction(player, board);
		assertEquals(true, player.isAdditionalAction());
	}

	@Test
	public void ifAdditionalActionIsTrueItShouldntBeChanged() {
		if (!this.player.isAdditionalAction())
			this.player.switchAdditionalAction();
		AdditionalAction action = new AdditionalAction();
		action.runAction(player, board);
		assertEquals(true, player.isAdditionalAction());
	}

	@Test
	public void toStringShouldReturnTheNameOfTheClass() {
		AdditionalAction action = new AdditionalAction();
		String name = action.toString();
		assertEquals(name, "AdditionalAction []");
	}

}
