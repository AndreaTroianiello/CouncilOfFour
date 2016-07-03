package it.polimi.ingsw.cg23.server.controller.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.Avvio;
import it.polimi.ingsw.cg23.server.controller.Controller;
import it.polimi.ingsw.cg23.server.controller.Timer;
import it.polimi.ingsw.cg23.server.model.Board;
import it.polimi.ingsw.cg23.server.model.Player;
import it.polimi.ingsw.cg23.server.model.components.NobilityTrack;
import it.polimi.ingsw.cg23.server.view.View;

public class EndTurnTest {

	private Board board;
	private Controller controller;
	
	@Before
	public void setUp() throws Exception {
		this.board=new Board(null,null,null,null,null,null);
		Avvio start=new Avvio("map1.xml", board);
	 	start.startPartita();
		this.controller=new Controller(board);
	}

	@Test
	public void testRunAction() {
		EndTurn endturn = new EndTurn();
		class TestView extends View{
		};
		Player player1 = new Player("user1", new NobilityTrack(3));
		Player player2 = new Player("user2", new NobilityTrack(3));
		TestView view1 = new TestView();
		TestView view2 = new TestView();
		this.controller.putSocketPlayer(view1, player1);
		this.controller.putSocketPlayer(view2, player2);
		this.controller.startGame();
		this.controller.getTurn().setTimer(new Timer(view1, controller));
		endturn.runAction(controller);
		assertEquals(player2, this.controller.getTurn().getCurrentPlayer());
	}

}
