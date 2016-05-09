package it.polimi.ingsw.cg23.model.components;

import java.awt.Color;

public class PoliticCard {
	private final Color color;											//The color of the card.
	private final boolean jolly;										//It indicates whether the card is multicolored. Yes is true, no is false.

	public PoliticCard(Color color, boolean jolly) {
		this.color = color;
		this.jolly=jolly;
	}

	/**
	 * Returns the color of the card.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns the status of the jolly card.
	 * 
	 * @return If the card is multicolor returns true.
	 */
	public boolean isJolly() {
		return jolly;
	}
}
