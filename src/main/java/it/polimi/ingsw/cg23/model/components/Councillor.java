package it.polimi.ingsw.cg23.model.components;

import java.awt.Color;

/**
 * The councillor can be used in a council and can be matched a politic card of the same color.
 * @author Andrea
 *
 */
public class Councillor {
	private final Color color;
	
	/**
	 * The constructor of councillor.
	 * 
	 * @param color The color of councillor.
	 */
	public Councillor(Color color){
		this.color=color;
	}
	
	/**
	 * Returns the color of the coucillor.
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Councillor [color=" + color + "]";
	}
	
	
}
