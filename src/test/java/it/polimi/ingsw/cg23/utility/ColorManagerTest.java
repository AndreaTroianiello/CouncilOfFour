package it.polimi.ingsw.cg23.utility;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class ColorManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * the test control if getColorName return null when the color is not in the list, and if it return the 
	 * name of the color when it is
	 */
	@Test
	public void testGetColorName() {
		ColorManager colorManager = new ColorManager();
		assertNull(colorManager.getColorName(Color.CYAN));
		assertEquals("Orange", colorManager.getColorName(Color.ORANGE));
	}

	/**
	 * it tests if getColor returns the right color
	 */
	@Test
	public void testGetColor() {
		ColorManager colorManager = new ColorManager();
		assertEquals(Color.RED, colorManager.getColor("Red"));
	}

}
