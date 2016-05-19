package it.polimi.ingsw.cg23.model.components;

import java.awt.Color;

public class Councillor {
	private final Color color;
	
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
