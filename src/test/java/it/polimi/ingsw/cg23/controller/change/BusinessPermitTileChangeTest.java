package it.polimi.ingsw.cg23.controller.change;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg23.server.controller.change.BusinessPermitTileChange;
import it.polimi.ingsw.cg23.server.model.bonus.BonusVictoryPoints;
import it.polimi.ingsw.cg23.server.model.components.BusinessPermitTile;
public class BusinessPermitTileChangeTest {
	private BusinessPermitTile tile;
	@Before
	public void setUp() throws Exception {
		List<Character> ids=Arrays.asList('A','B','C');
		tile=new BusinessPermitTile(ids, "region");
		tile.addBonus(new BonusVictoryPoints(1));
	}

	@Test
	public void testBoardChange() {
		BusinessPermitTileChange change=new BusinessPermitTileChange(tile);
		assertEquals(change.toString(),"BusinessPermitTileChange [Tile= [Cities=[A, B, C], Bonuses=[BonusVictoryPoints[points=1]]]");
	}

}
