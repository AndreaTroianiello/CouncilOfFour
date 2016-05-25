package it.polimi.ingsw.cg23.model.components;

import java.awt.Color;

/**
 * The councillor can be used to build a emporium and can be matched a councillor of the same color.
 * @author Andrea
 *
 */
public class PoliticCard {

	private final Color color;											//The color of the card.
	private final boolean jolly;										//It indicates whether the card is multicolored. Yes is true, no is false.

	/**
	 * The constructor of politic card.
	 * @param color The color of the politic card. It can be null if this card is a jolly.
	 * @param jolly If is true, the card is a jolly.
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		/*String string= "PoliticCard [color=";
		if(color==null)
				string+=" ";
		else
			string+=color.getClass().getEnumConstants().toString();
		string+=", jolly=" + jolly + "]";
		return string;*/
		return "PoliticCard [color="+ color +", jolly="+ jolly+"]";
	}
}
